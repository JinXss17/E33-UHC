package fr.jinxss.e33.uhcsystem;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.jinxss.e33.E33UHC;

public class UHCConfigMenu implements Listener {

    private final UHCSystem uhcSystem;
    private final UHCBorder uhcBorder;
    private final Inventory menu;
    private final E33UHC plugin;

    public UHCConfigMenu(E33UHC plugin, UHCSystem system) {
    	this.plugin = plugin;
        this.uhcSystem = system;
        this.uhcBorder = uhcSystem.getBorder();
        this.menu = Bukkit.createInventory(null, 45, ChatColor.DARK_GREEN + "Configuration UHC");
    }

    private void setupItems() {
        // Temps PvP
        menu.setItem(10, createItem(Material.RED_CONCRETE, "⏱ Temps PvP -", "Prochain : " + (uhcSystem.getBoard().PVP_ENABLE_TIME-1) + "min"));
        menu.setItem(11, createItem(Material.CLOCK, "⏱ Temps PvP", uhcSystem.getBoard().PVP_ENABLE_TIME + " Minutes"));
        menu.setItem(12, createItem(Material.LIME_CONCRETE, "⏱ Temps PvP +", "Prochain : " + (uhcSystem.getBoard().PVP_ENABLE_TIME+1) + "min"));

        // Taille bordure
        menu.setItem(19, createItem(Material.RED_CONCRETE, "⬅ Bordure -", (uhcBorder.InitialBorderSize-100) + ""));
        menu.setItem(20, createItem(Material.MAP, "Taille bordure", uhcBorder.InitialBorderSize + ""));
        menu.setItem(21, createItem(Material.LIME_CONCRETE, "➡ Bordure +", (uhcBorder.InitialBorderSize+100) + ""));

        // Taille MeetUp
        menu.setItem(28, createItem(Material.RED_CONCRETE, "⬅ MeetUp -", (uhcBorder.MeetUpBorderSize-25) + ""));
        menu.setItem(29, createItem(Material.BARRIER, "Taille MeetUp", uhcBorder.MeetUpBorderSize + ""));
        menu.setItem(30, createItem(Material.LIME_CONCRETE, "➡ MeetUp +", (uhcBorder.MeetUpBorderSize+25) + ""));
        
        // Bouton Toggle Roles / Mobs
        if(plugin.isRolesToggled()) 
        	menu.setItem(37, createItem(Material.GREEN_CONCRETE, "§a~ Roles : Activé ~" ) );
        else 
        	menu.setItem(37, createItem(Material.RED_CONCRETE, "§c~ Roles : Désactivé ~") );
        
        if(plugin.isMobsToggled()) 
        	menu.setItem(39, createItem(Material.GREEN_CONCRETE, "§a~ Custom Mobs : Activé ~" ) );
        else 
        	menu.setItem(39, createItem(Material.RED_CONCRETE, "§c~ CustomMobs : Désactivé ~") );
        

        // Bouton de lancement
        menu.setItem(24, createItem(Material.LIME_WOOL, "✅ Lancer la partie"));
    }

    private ItemStack createItem(Material mat, String name, String... loreLines) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + name);
            meta.setLore(Arrays.asList(loreLines));
            item.setItemMeta(meta);
        }
        return item;
    }

    public void open(Player player) {
        setupItems(); // met à jour l'état du menu à l'ouverture
        player.openInventory(menu);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;
        
        if(e.getClickedInventory() == menu) {
        	e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            int slot = e.getRawSlot();

            switch (slot) {
                // Temps PvP
                case 10 -> uhcSystem.getBoard().PVP_ENABLE_TIME -= 1;
                case 12 -> uhcSystem.getBoard().PVP_ENABLE_TIME += 1;

                // Taille bordure
                case 19 -> uhcBorder.InitialBorderSize = Math.max(100, uhcBorder.InitialBorderSize - 100);
                case 21 -> uhcBorder.InitialBorderSize += 100;

                // Taille MeetUp
                case 28 -> uhcBorder.MeetUpBorderSize = Math.max(25, uhcBorder.MeetUpBorderSize - 25);
                case 30 -> uhcBorder.MeetUpBorderSize += 25;
                
                //Toggle Roles / Mobs System
                case 37 -> plugin.ToggleRoles();
                
                case 39 -> plugin.ToggleMobs();
                

                // Lancer la partie
                case 24 -> {
                    player.closeInventory();
                    Bukkit.broadcastMessage(ChatColor.GREEN + "✔ La partie UHC est lancée !");
                    uhcSystem.StartGame();
                    return;
                }
            }

            open(player); // recharge le menu avec les valeurs mises à jour
        }
    }
}
