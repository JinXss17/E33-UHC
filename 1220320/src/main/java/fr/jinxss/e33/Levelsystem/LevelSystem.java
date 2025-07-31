package fr.jinxss.e33.Levelsystem;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.jinxss.e33.E33UHC;

public class LevelSystem {

	
	private static HashMap<UUID, PlayerLevel> Levels = new HashMap<UUID, PlayerLevel>();
	private E33UHC system;
	
	public LevelSystem(E33UHC system) {
		this.system = system;
		RegisterAllPlayerLevel();
		this.system.getServer().getPluginManager().registerEvents(new LevelListener(this), system);
	}
	
	public PlayerLevel getPlayerLevel(Player p ) {
		PlayerLevel level = Levels.getOrDefault(p.getUniqueId(), new PlayerLevel());
		return level;
	}
	
	public void RegisterAllPlayerLevel() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			Levels.put(p.getUniqueId(), new PlayerLevel());
		}
	}
}
