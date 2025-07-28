package fr.jinxss.e33.PictoSystem;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PictoSystem {
	
	private HashMap<UUID, PlayerPictos> playerPicto = new HashMap<UUID, PlayerPictos>();

	
	public PlayerPictos getPlayerPictos(Player player) {
		
		return playerPicto.getOrDefault(player.getUniqueId(), new PlayerPictos());
		
	}
	
}
