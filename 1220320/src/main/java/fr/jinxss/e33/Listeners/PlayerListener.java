package fr.jinxss.e33.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.uhcsystem.UHCSystem;
import fr.jinxss.e33.uhcsystem.list.EGameStates;

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
	@EventHandler (priority = EventPriority.MONITOR)
	public void PlayerHit(EntityDamageByEntityEvent e) {
		
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
				e.setCancelled( !plugin.getUHCSystem().isPvpEnabled() );
		}
		
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void OnDamage(EntityDamageEvent e) {
		
		if(plugin.getUHCSystem().getGameState() == EGameStates.Waiting) {
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if(plugin.getUHCSystem().getGameState() == EGameStates.Waiting) {
			UHCSystem uhcSystem = plugin.getUHCSystem();
			
			p.getInventory().clear();
			p.getAttribute(Attribute.MAX_HEALTH).setBaseValue(20.0d);
			p.setHealth(20.0d);
			p.setLevel(0);
			p.setExp(0);
			p.setFoodLevel(20);
			p.setGameMode(GameMode.SURVIVAL);
			p.teleport(new Location(Bukkit.getWorld("world"), 0, 180, 0) );
			for(PotionEffect effect : p.getActivePotionEffects()) {
				p.removePotionEffect(effect.getType());
			}
			
			if(!uhcSystem.getPlayers().contains(p)) {
				uhcSystem.addPlayerToGame(p);
			}
			if(p.isOp()) {
				p.getInventory().setItem(4, LaunchGameItem);
			}
		}
		
		
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onDisconect(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		UHCSystem uhcSystem = plugin.getUHCSystem();
		if(uhcSystem.getPlayers().contains(p)) {
			uhcSystem.RemovePlayerToGame(p);
		}
		
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onClickItem(PlayerInteractEvent e) {
		
		ItemStack it = e.getItem();
		if(it == null) {
			return;
		}
		if(it.hasItemMeta() && it.getItemMeta().getDisplayName().equalsIgnoreCase(LaunchGameItem.getItemMeta().getDisplayName())) {
			plugin.getUHCSystem().getConfigMenu().open(e.getPlayer());
		}
		
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onDead(PlayerDeathEvent e) {
		
		Player p = e.getEntity();
		String deathMSG = E33UHC.GetPréffix() + " §c"+ p.getName() + " est mort !";
		e.setDeathMessage(deathMSG);
		e.setKeepInventory(true);
		Location DropLocation = p.getLocation();
		World world = p.getWorld();
		for(ItemStack drop : p.getInventory()) {
			world.dropItem(DropLocation, drop);
		}
		
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		Bukkit.getScheduler().runTaskLater(plugin, () -> {p.setGameMode(GameMode.SPECTATOR);} , 1);
	}
}
