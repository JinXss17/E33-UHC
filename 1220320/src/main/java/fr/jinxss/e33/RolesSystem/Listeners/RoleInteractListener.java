package fr.jinxss.e33.RolesSystem.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.RolesSystem.RoleManager;
import fr.jinxss.e33.RolesSystem.Roles;
import fr.jinxss.e33.RolesSystem.Nevrons.Demineur;
import fr.jinxss.e33.RolesSystem.roles.Gustave;
import fr.jinxss.e33.RolesSystem.roles.Lune;
import fr.jinxss.e33.RolesSystem.roles.Maelle;
import fr.jinxss.e33.RolesSystem.roles.Renoir;
import fr.jinxss.e33.RolesSystem.roles.Sciel;

public class RoleInteractListener implements Listener {

    @EventHandler (priority = EventPriority.HIGH)
    public void onInteract(PlayerInteractEvent event) {
        // ne traiter que la main principale
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        Roles role = RoleManager.getRole(player.getUniqueId());
        if (role == null) return;

        ItemStack item = event.getItem();

		// --- Sciel ---------------------------------------------------------
        if (role instanceof Sciel sciel && sciel.isDeck(item)) {
            event.setCancelled(true);
            sciel.onPowerUse(); //
        }
        // --- Gustave ---------------------------------------------------------
        if (role instanceof Gustave gustave && gustave.isGustaveItem(item)) {
            event.setCancelled(true);
            gustave.onPowerUse(); // ou gustave.onPowerUse() si tu n’as pas besoin du joueur
        }
        // --- Role ---------------------------------------------------------
        if (role instanceof Lune lune && lune.isLuneItem(item)) {
            event.setCancelled(true);
            lune.onPowerUse();
        }
        
        // --- Maelle ---------------------------------------------------------
        if (role instanceof Maelle maelle && maelle.isPostureItem(item)) {
            event.setCancelled(true);          // on empêche l’action par défaut
            maelle.onPowerUse();               // change la posture
        }
        
        if (role instanceof Renoir renoir && renoir.isInvocationItem(item)) {
            event.setCancelled(true);
            renoir.onPowerUse(); // ou gustave.onPowerUse() si tu n’as pas besoin du joueur
        }
        
        // --- Demineur ---------------------------------------------------------
        if (role instanceof Demineur demineur && demineur.isDemineurItem(item)) {
            event.setCancelled(true);
            demineur.onPowerUse();
        }


        // plus tard : autres rôles et objets spéciaux…
    }
}
