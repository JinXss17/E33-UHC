package fr.jinxss.e33;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UpdateChecker {

    private final E33UHC plugin;
    private static final String API_URL = "https://api.github.com/repos/JinXss17/E33-UHC/releases/latest";

    public UpdateChecker(E33UHC plugin) {
        this.plugin = plugin;
    }

    public void checkUpdate() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                // Appel API GitHub
                HttpURLConnection conn = (HttpURLConnection) new URL(API_URL).openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                conn.setRequestProperty("Accept", "application/vnd.github.v3+json");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) response.append(line);
                reader.close();

                JSONObject json = (JSONObject) new JSONParser().parse(response.toString());
                String latestVersion = ((String) json.get("tag_name")).replace("v", "");
                String currentVersion = plugin.getDescription().getVersion();

                if (currentVersion.equalsIgnoreCase(latestVersion)) {
                    plugin.getLogger().info("Ton plugin est déjà à jour ! (" + currentVersion + ")");
                    return;
                }

                plugin.getLogger().info("Nouvelle version trouvée: " + latestVersion + " (actuelle: " + currentVersion + ")");

                // Récupérer le .jar depuis les assets
                JSONArray assets = (JSONArray) json.get("assets");
                if (assets.isEmpty()) {
                    plugin.getLogger().warning("Aucun asset trouvé pour la release " + latestVersion);
                    return;
                }

                // On prend le premier .jar trouvé
                JSONObject asset = null;
                for (Object obj : assets) {
                    JSONObject a = (JSONObject) obj;
                    if (a.get("name").toString().endsWith(".jar")) {
                        asset = a;
                        break;
                    }
                }
                if (asset == null) {
                    plugin.getLogger().warning("Aucun fichier .jar trouvé dans les assets de la release " + latestVersion);
                    return;
                }

                String downloadUrl = (String) asset.get("browser_download_url");
                String fileName = (String) asset.get("name");

                // Création du dossier update
                File updateFolder = new File(plugin.getDataFolder().getParentFile(), "update");
                if (!updateFolder.exists()) updateFolder.mkdirs();

                File newJar = new File(updateFolder, fileName);

                // Téléchargement du fichier
                try (InputStream in = new URL(downloadUrl).openStream()) {
                    Files.copy(in, newJar.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }

                plugin.getLogger().info("Mise à jour téléchargée dans /plugins/update/ (" + fileName + "). Redémarre le serveur pour l’appliquer !");

            } catch (Exception e) {
                plugin.getLogger().warning("Impossible de vérifier les mises à jour : " + e.getMessage());
            }
        });
    }
}
