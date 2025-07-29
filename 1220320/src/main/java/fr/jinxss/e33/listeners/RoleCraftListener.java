package fr.jinxss.e33.listeners;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.RoleManager;
import fr.jinxss.e33.e33system.roles.Sciel;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.RoleManager;
import fr.jinxss.e33.e33system.roles.Gustave;
import fr.jinxss.e33.e33system.roles.Lune;
import fr.jinxss.e33.e33system.roles.Maelle;

public class RoleCraftListener implements Listener {
	
	/*
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
		if (name.equalsIgnoreCase("§dRôle - Sciel")) {
            if (!RoleManager.isRoleAvailable("Sciel")) {
                player.sendMessage("§cCe rôle est déjà pris.");
                event.setCancelled(true);
                return;
            }

            if (RoleManager.hasRole(uuid)) {
                player.sendMessage("§cTu as déjà un rôle !");
                event.setCancelled(true);
                return;
            }

            Sciel sciel = new Sciel(uuid, "Sciel");
            RoleManager.assignRole(uuid, "Sciel", sciel);
            sciel.onAssign();
            player.sendMessage("§aTu as obtenu le rôle §dROLES§a !");
        }
    }*/

        if (name.equalsIgnoreCase("§dRôle - Gustave")) {
            if (!RoleManager.isRoleAvailable("Gustave")) {
                player.sendMessage("§cCe rôle est déjà pris.");
                event.setCancelled(true);
                return;
            }

            if (RoleManager.hasRole(uuid)) {
                player.sendMessage("§cTu as déjà un rôle !");
                event.setCancelled(true);
                return;
            }

            Gustave gustave = new Gustave(uuid, "Gustave");
            RoleManager.assignRole(uuid, "Gustave", gustave);
            gustave.onAssign();
            player.sendMessage("§aTu as obtenu le rôle §dGustave§a !");
        }
        if (name.equalsIgnoreCase("§dRôle - Lune")) {
            if (!RoleManager.isRoleAvailable("Lune")) {
                player.sendMessage("§cCe rôle est déjà pris.");
                event.setCancelled(true);
                return;
            }

            if (RoleManager.hasRole(uuid)) {
                player.sendMessage("§cTu as déjà un rôle !");
                event.setCancelled(true);
                return;
            }

            Lune lune = new Lune(uuid, "Lune");
            RoleManager.assignRole(uuid, "Lune", lune);
            lune.onAssign();
            player.sendMessage("§aTu as obtenu le rôle §dLune§a !");
        }
        
        if (name.equalsIgnoreCase("§dRôle - Maelle")) {
            if (!RoleManager.isRoleAvailable("Maelle")) {
                player.sendMessage("§cCe rôle est déjà pris.");
                event.setCancelled(true);
                return;
            }

            if (RoleManager.hasRole(uuid)) {
                player.sendMessage("§cTu as déjà un rôle !");
                event.setCancelled(true);
                return;
            }

            Maelle maelle = new Maelle(uuid, "Maelle");
            RoleManager.assignRole(uuid, "Maelle", maelle);
            maelle.onAssign();
            player.sendMessage("§aTu as obtenu le rôle §dMaelle§a !");
        }
        
    }
}
