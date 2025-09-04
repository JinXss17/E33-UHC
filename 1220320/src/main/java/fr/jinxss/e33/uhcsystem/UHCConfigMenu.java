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
        menu.setItem(1, createItem(Material.RED_CONCRETE, "⏱ Temps PvP -", "Prochain : " + (uhcSystem.PVP_ENABLE_TIME-1) + "min"));
        int PVPHour = (int) (uhcSystem.PVP_ENABLE_TIME/60);
        int PVPMin = (int) (uhcSystem.PVP_ENABLE_TIME%60);
        if(PVPHour == 0) {
        	menu.setItem(2, createItem(Material.IRON_SWORD, "⏱ Temps PvP", "" + PVPMin + "Min"));
        }else {
        	menu.setItem(2, createItem(Material.IRON_SWORD, "⏱ Temps PvP", PVPHour + "H"+ PVPMin + "Min"));
        }
        menu.setItem(3, createItem(Material.LIME_CONCRETE, "⏱ Temps PvP +", "Prochain : " + (uhcSystem.PVP_ENABLE_TIME+1) + "min"));

        // Taille bordure
        menu.setItem(10, createItem(Material.RED_CONCRETE, "⬅ Bordure -", (uhcBorder.InitialBorderSize/2-100) + ""));
        menu.setItem(11, createItem(Material.MAP, "Taille bordure", uhcBorder.InitialBorderSize/2 + ""));
        menu.setItem(12, createItem(Material.LIME_CONCRETE, "➡ Bordure +", (uhcBorder.InitialBorderSize/2+100) + ""));

        // Taille MeetUp
        menu.setItem(19, createItem(Material.RED_CONCRETE, "⬅ MeetUp -", (uhcBorder.MeetUpBorderSize/2-25) + ""));
        menu.setItem(20, createItem(Material.BARRIER, "Taille MeetUp", uhcBorder.MeetUpBorderSize/2 + ""));
        menu.setItem(21, createItem(Material.LIME_CONCRETE, "➡ MeetUp +", (uhcBorder.MeetUpBorderSize/2+25) + ""));
        
        // Temps MeetUp
        menu.setItem(28, createItem(Material.RED_CONCRETE, "⬅ MeetUp -", "Prochain : " + (uhcSystem.MinuteToMeetUp - 1) + " min"));
        int MeetUpHour = uhcSystem.MinuteToMeetUp/60;
        int MeetUpMin = uhcSystem.MinuteToMeetUp%60;
        if(MeetUpHour == 0) {
        	menu.setItem(29, createItem(Material.CLOCK, "⏱ Temps MeetUp", "" + MeetUpMin + "Min"));
        }else {
        	menu.setItem(29, createItem(Material.CLOCK, "⏱ Temps MeetUp", MeetUpHour + "H"+ MeetUpMin + "Min"));
        }
        menu.setItem(30, createItem(Material.LIME_CONCRETE, "➡ MeetUp +", "Prochain : " + (uhcSystem.MinuteToMeetUp + 1) + " min"));
        
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
                case 1 -> uhcSystem.PVP_ENABLE_TIME = Math.max(0, uhcSystem.PVP_ENABLE_TIME - 1);
                case 3 -> uhcSystem.PVP_ENABLE_TIME += 1;

                // Taille bordure
                case 10 -> uhcBorder.InitialBorderSize = Math.max(100, uhcBorder.InitialBorderSize - 100);
                case 12 -> uhcBorder.InitialBorderSize += 100;

                // Taille MeetUp
                case 19 -> uhcBorder.MeetUpBorderSize = Math.max(25, uhcBorder.MeetUpBorderSize - 25);
                case 21 -> uhcBorder.MeetUpBorderSize += 25;
                
                case 28 -> uhcSystem.MinuteToMeetUp = (int) Math.max(20, uhcSystem.MinuteToMeetUp - 1);
                case 30 -> uhcSystem.MinuteToMeetUp += 1;
                
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
