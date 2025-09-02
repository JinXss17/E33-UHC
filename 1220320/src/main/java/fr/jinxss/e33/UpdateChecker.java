package fr.jinxss.e33;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class UpdateChecker {
	
	public UpdateChecker(E33UHC plugin) {
		
		this.plugin = plugin;
		
	}
	
	private E33UHC plugin;
	private static final String API_URL = "https://github.com/JinXss17/E33-UHC/releaseslatest";
	
	public void CheckUpdate() {
		

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                // Appel à l’API GitHub
                HttpURLConnection conn = (HttpURLConnection) new URL(API_URL).openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) response.append(line);
                reader.close();

                // Parse JSON
                JSONObject json = (JSONObject) new JSONParser().parse(response.toString());
                String latestVersion = (String) json.get("tag_name");

                String currentVersion = plugin.getDescription().getVersion();

                if (!currentVersion.equalsIgnoreCase(latestVersion)) {
                    plugin.getLogger().info("Nouvelle version trouvée: " + latestVersion + " (actuelle: " + currentVersion + ")");
                    
                    // Récupérer l’URL du jar
                    JSONArray assets = (JSONArray) json.get("assets");
                    if (assets.isEmpty()) return;

                    JSONObject asset = (JSONObject) assets.get(0);
                    String downloadUrl = (String) asset.get("browser_download_url");

                    // Téléchargement
                    File updateFolder = new File(plugin.getDataFolder().getParentFile(), "update");
                    if (!updateFolder.exists()) updateFolder.mkdirs();

                    File currentJar = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
                    File newJar = new File(updateFolder, currentJar.getName());
                    try (InputStream in = new URL(downloadUrl).openStream()) {
                        Files.copy(in, newJar.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    }

                    plugin.getLogger().info("Mise à jour téléchargée dans /plugins/update/. Redémarre le serveur pour l’appliquer !");
                } else {
                	plugin.getLogger().info("Ton plugin est déjà à jour !");
                }
            } catch (Exception e) {
            	plugin.getLogger().warning("Impossible de vérifier les mises à jour : " + e.getMessage());
            }
        });
		
	}
	

}
