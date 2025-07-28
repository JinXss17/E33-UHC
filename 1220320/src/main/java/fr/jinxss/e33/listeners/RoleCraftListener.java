package fr.jinxss.e33.listeners;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.RoleManager;
import fr.jinxss.e33.e33system.roles.Renoir;

public class RoleCraftListener implements Listener {
	

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        ItemStack item = event.getCurrentItem();
        if (item == null || !item.hasItemMeta()) return;

        String name = item.getItemMeta().getDisplayName();
        UUID uuid = player.getUniqueId();

        if (name.equalsIgnoreCase("§dRôle - Renoir")) {
            if (!RoleManager.isRoleAvailable("Renoir")) {
                player.sendMessage("§cCe rôle est déjà pris.");
                event.setCancelled(true);
                return;
            }

            if (RoleManager.hasRole(uuid)) {
                player.sendMessage("§cTu as déjà un rôle !");
                event.setCancelled(true);
                return;
            }

            Renoir renoir = new Renoir(uuid, "Renoir");
            RoleManager.assignRole(uuid, "Renoir", renoir);
            renoir.onAssign();
            player.sendMessage("§aTu as obtenu le rôle §dRenoir§a !");
        }
    }

}
