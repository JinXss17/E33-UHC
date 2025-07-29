package fr.jinxss.e33.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.uhcsystem.UHCSystem;

public class PlayerListener implements Listener {
	
	private E33UHC plugin;
	
	private ItemStack LaunchGameItem = new ItemStack(Material.MAGMA_CREAM, 1);
	
	
	
	public PlayerListener(E33UHC plugin) {
		
		loadStartGameItem();
		this.plugin = plugin;
		
	}
	
	public void loadStartGameItem() {
		ItemMeta LaunchGameItemMeta = LaunchGameItem.getItemMeta();
		LaunchGameItemMeta.setDisplayName(ChatColor.DARK_GREEN + "Start Game");
		LaunchGameItem.setItemMeta(LaunchGameItemMeta);
	}
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		UHCSystem uhcSystem = plugin.getUHCSystem();
		if(!uhcSystem.getPlayers().contains(p)) {
			uhcSystem.addPlayerToGame(p);
		}
		
		if(p.isOp()) {
			p.getInventory().addItem(LaunchGameItem);
		}
	}
	
	@EventHandler
	public void onDisconect(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		UHCSystem uhcSystem = plugin.getUHCSystem();
		if(uhcSystem.getPlayers().contains(p)) {
			uhcSystem.RemovePlayerToGame(p);
		}
		
	}
	
	@EventHandler
	public void onClickItem(PlayerInteractEvent e) {
		
		ItemStack it = e.getItem();
		if(it == null) {
			return;
		}
		if(it.hasItemMeta() && it.getItemMeta().getDisplayName().equalsIgnoreCase(LaunchGameItem.getItemMeta().getDisplayName())) {
			plugin.getUHCSystem().getConfigMenu().open(e.getPlayer());
		}
		
	}
	
	@EventHandler
	public void onDead(PlayerDeathEvent e) {
		
		Player p = e.getEntity();
		String deathMSG = E33UHC.GetPréffix() + " §c"+ p.getName() + " est mort !";
		e.setDeathMessage(deathMSG);
		if(p.getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY) != true) {
			p.getWorld().setGameRule(GameRule.KEEP_INVENTORY, true);
		}
		Location DropLocation = p.getLocation();
		World world = p.getWorld();
		for(ItemStack drop : p.getInventory()) {
			world.dropItem(DropLocation, drop);
		}
		
	}
	
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		Bukkit.getScheduler().runTaskLater(plugin, () -> {p.setGameMode(GameMode.SPECTATOR);} , 1);
	}
}
