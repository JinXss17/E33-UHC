package fr.jinxss.e33.uhcsystem;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.jinxss.e33.E33UHC;

public class UHCScoreBoard {

    private E33UHC plugin;
    private final Map<Player, Scoreboard> scoreboards = new HashMap<>();
    private long startTime;
    
    public long PVP_ENABLE_TIME = 10; // en minutes
    private final UHCSystem uhcSystem;
    private final UHCBorder uhcBorder;
    
    private boolean meetupStarted = false;
    
    public UHCBorder getBorder() {
    	return uhcBorder;
    }

    public UHCScoreBoard(E33UHC plugin, UHCSystem uhcSystem, UHCBorder uhcBorder
    		) {
        this.plugin = plugin;
        this.uhcBorder = uhcBorder;
		this.uhcSystem = uhcSystem;
    }

    public void createScoreboard(Player player) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        if (scoreboardManager == null) return;

        Scoreboard board = scoreboardManager.getNewScoreboard();
        @SuppressWarnings("deprecation")
		Objective obj = board.registerNewObjective("uhc", "dummy", ChatColor.GOLD + "UHC");
        obj.setDisplayName(ChatColor.GOLD + "UHC");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        scoreboards.put(player, board);
        player.setScoreboard(board);
    }

    public void startUpdating() {
    	this.startTime = System.currentTimeMillis();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    updateScoreboard(player);
                }
            }
        }.runTaskTimer(plugin, 0, 20); // toutes les secondes
    }

    private void updateScoreboard(Player player) {
	    if (!scoreboards.containsKey(player)) createScoreboard(player);
	
	    Scoreboard board = scoreboards.get(player);
	    Objective obj = board.getObjective("uhc");
	    if (obj == null) return;
	
	    for (String entry : board.getEntries()) {
	        board.resetScores(entry);
	    }
	
	    long elapsed = (System.currentTimeMillis() - startTime) / 1000;
	    long minutes = elapsed / 60;
	    long seconds = elapsed % 60;
	
	    Location loc = player.getLocation();
	    int alivePlayers = (int) Bukkit.getOnlinePlayers().stream().filter(p -> !p.isDead()).count();
	
	    obj.getScore(ChatColor.YELLOW + "Temps: " + minutes + "m" + seconds + "s").setScore(5);
	    obj.getScore(ChatColor.GREEN + "Joueurs: " + alivePlayers).setScore(4);
	    obj.getScore(ChatColor.AQUA + "X: " + loc.getBlockX()).setScore(3);
	    obj.getScore(ChatColor.AQUA + "Y: " + loc.getBlockY()).setScore(2);
	    obj.getScore(ChatColor.AQUA + "Z: " + loc.getBlockZ()).setScore(1);
	    
	    
	    
	    double borderSize = uhcBorder.getCurrentSize();
	    obj.getScore(ChatColor.DARK_GREEN + "Bordure: " + (int) borderSize).setScore(-1);
	
	     // Activer le PvP si le temps est dépassé et qu'il ne l'est pas encore
	     if (!uhcSystem.isPvpEnabled() && elapsed >= PVP_ENABLE_TIME * 60) {
	         uhcSystem.TogglePvP();
	     }
	     if (!uhcSystem.isPvpEnabled()) {
		    long remaining = PVP_ENABLE_TIME * 60 - elapsed;
		    long lminutes = remaining / 60;
		    long lseconds = remaining % 60;
	
		    String formatted = String.format("%02d:%02d", lminutes, lseconds);
		    obj.getScore(ChatColor.LIGHT_PURPLE + "PvP dans: " + formatted).setScore(0);
	     } else {
	    	    obj.getScore(ChatColor.LIGHT_PURPLE + "PvP: Activé").setScore(0);
	     }
	     if (!meetupStarted && elapsed >= uhcBorder.MinuteToMeetUp * 60) {
		    meetupStarted = true;
		    uhcBorder.ReduceToMeetUpSize();
	
		    // Message de broadcast (optionnel)
		    Bukkit.broadcastMessage(ChatColor.GOLD + "⚔️ MeetUp ! La bordure se réduit !");
	     }
	     if (!meetupStarted) {
			long remaining = uhcBorder.MinuteToMeetUp * 60 - elapsed;
			long lminutes = remaining / 60;
			long lseconds = remaining % 60;
			
			String formatted = String.format("%02d:%02d", lminutes, lseconds);
			obj.getScore(ChatColor.GOLD + "MeetUp dans: " + formatted).setScore(-2);
	     } else {
	    	 obj.getScore(ChatColor.GOLD + "MeetUp: En cours").setScore(-2);
	     }
    }
}
