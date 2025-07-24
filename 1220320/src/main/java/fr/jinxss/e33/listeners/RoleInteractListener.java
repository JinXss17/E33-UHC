package fr.jinxss.e33.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.RoleManager;
import fr.jinxss.e33.Roles;
import fr.jinxss.e33.e33system.roles.Lune;

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
        if (role instanceof Lune lune && lune.isLuneItem(item)) {
            event.setCancelled(true);
            lune.onPowerUse();
        }


        // plus tard : autres rôles et objets spéciaux…
    }
}