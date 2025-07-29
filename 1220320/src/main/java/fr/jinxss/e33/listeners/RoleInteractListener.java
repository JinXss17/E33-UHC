package fr.jinxss.e33.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.RoleManager;
import fr.jinxss.e33.Roles;
import fr.jinxss.e33.e33system.nevrons.Demineur;

public class RoleInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        // ne traiter que la main principale
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        Roles role = RoleManager.getRole(player.getUniqueId());
        if (role == null) return;

        ItemStack item = event.getItem();

        // --- Role ---------------------------------------------------------
        if (role instanceof Demineur demineur && demineur.isDemineurItem(item)) {
            event.setCancelled(true);
            demineur.onPowerUse(); // ou gustave.onPowerUse() si tu n’as pas besoin du joueur
        }


        // plus tard : autres rôles et objets spéciaux…
    }
}