package fr.jinxss.e33.uhcsystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class UHCScoreBoard {
	
	
	public Scoreboard board;
	
	public static void UpdateScoreBoard() {
		
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
		Objective obj = scoreboard.registerNewObjective("Base", "Dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("E33 UHC");
		//Score time = obj.getScore("§eTime : §r" + Timer.Hours + " §e:§r " + Timer.Minutes + " §e:§r " + Timer.Seconds);
		//time.setScore(1);
		

		for(Player p : Bukkit.getOnlinePlayers()) {
			Score kills = obj.getScore("§ePlayers : §r");
			kills.setScore(0);
			p.setScoreboard(scoreboard);
			
		}
		
	}

}
