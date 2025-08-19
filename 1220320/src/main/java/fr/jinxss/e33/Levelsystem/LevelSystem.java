package fr.jinxss.e33.Levelsystem;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.PictoSystem.PlayerPictos;

public class LevelSystem {

	
	private static HashMap<UUID, PlayerLevel> Levels = new HashMap<UUID, PlayerLevel>();
	private E33UHC system;
	
	public LevelSystem(E33UHC system) {
		this.system = system;
		this.system.getServer().getPluginManager().registerEvents(new LevelListener(this), system);
		system.getCommand("level").setExecutor(new CommandLevel(this));
	}
	
	public PlayerLevel getPlayerLevel(Player p ) {
		PlayerPictos pictos = system.getPictoSystem().getPlayerPictos(p);
		PlayerLevel level = Levels.getOrDefault(p.getUniqueId(), new PlayerLevel(pictos, p));
		return level;
	}
	
	public void registerPlayerLevel(Player p, PlayerLevel level) {
		Levels.put(p.getUniqueId(), level);
	}
}
