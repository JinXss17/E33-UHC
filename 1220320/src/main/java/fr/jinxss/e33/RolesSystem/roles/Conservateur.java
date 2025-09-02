package fr.jinxss.e33.RolesSystem.roles;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.RolesSystem.Roles;

public class Conservateur extends Roles {

    private int axonsKilled = 0;

    public Conservateur(UUID uuid, String name) {
        super(uuid, name);
    }

    @Override
    public void onAssign() {
        // Rien à faire à l'attribution
        // On pourrait ici démarrer un timer / scheduler pour gérer la prémonition axonique
    }

    @Override
    public void onPowerUse() {
        // Pas de pouvoir actif, donc rien à faire ici
    }

    /**
     * Appelé quand ce joueur tue un Axon (à intégrer dans le listener de kill)
     */
    public void onAxonKill() {
        axonsKilled++;

        if (axonsKilled == 2) {
            giveReward();
        }
    }

    private void giveReward() {
        Player player = getPlayer();
        if (player == null) return;

        ItemStack sharpSword = new ItemStack(Material.DIAMOND_SWORD);
        sharpSword.addEnchantment(Enchantment.SHARPNESS, 7); // Sharpness VII
        player.getInventory().addItem(sharpSword);

        player.sendMessage("§aFélicitations ! Vous avez tué 2 Axons, voici votre récompense.");
    }

    /**
     * Méthode à appeler pour prévenir le joueur de la position d'un Axon 3 minutes avant le spawn
     * @param x, y, z coordonnées
     */
    public void sendAxonPremonition(Location loc) {
        Player player = getPlayer();
        if (player == null) return;

        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        
        player.sendMessage("§6Prémonition Axonique : Un Axon va apparaître bientôt en (" + x + ", " + y + ", " + z + ")");
        // Tu peux ajouter un effet visuel / son ici plus tard
    }
}