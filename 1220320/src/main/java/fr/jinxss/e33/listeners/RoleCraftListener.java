package fr.jinxss.e33.listeners;

import fr.jinxss.e33.RoleManager;

import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class RoleCraftListener implements Listener {
	
/*
    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        ItemStack item = event.getCurrentItem();
        if (item == null || !item.hasItemMeta()) return;

        String name = item.getItemMeta().getDisplayName();
        UUID uuid = player.getUniqueId();

        if (name.equalsIgnoreCase("§dRôle - ROLES")) {
            if (!RoleManager.isRoleAvailable("ROLES")) {
                player.sendMessage("§cCe rôle est déjà pris.");
                event.setCancelled(true);
                return;
            }

            if (RoleManager.hasRole(uuid)) {
                player.sendMessage("§cTu as déjà un rôle !");
                event.setCancelled(true);
                return;
            }

            ROLES role = new ROLES(uuid, "ROLES");
            RoleManager.assignRole(uuid, "ROLES", role);
            role.onAssign();
            player.sendMessage("§aTu as obtenu le rôle §dROLES§a !");
        }
    }*/

}
