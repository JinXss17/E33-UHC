package fr.jinxss.e33.PictoSystem;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.jinxss.e33.E33UHC;

public class PictoSystem {
	
	private HashMap<UUID, PlayerPictos> playerPicto = new HashMap<UUID, PlayerPictos>();

	private E33UHC plugin;
	
	public PictoSystem(E33UHC plugin) {
		
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(new PictoListener(this.plugin, this), this.plugin);
		
	}
	
	public PlayerPictos getPlayerPictos(Player player) {
		
		return playerPicto.getOrDefault(player.getUniqueId(), new PlayerPictos());
		
	}
	
}
