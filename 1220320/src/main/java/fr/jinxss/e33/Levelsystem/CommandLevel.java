package fr.jinxss.e33.Levelsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLevel implements CommandExecutor {

	private LevelSystem system;
	
	public CommandLevel(LevelSystem system) {
		
		this.system = system;
		
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		// TODO Auto-generated method stub
		
		if(sender instanceof Player p) {
			
			int Exp = system.getPlayerLevel(p).getExp();
			int ExpLevel = system.getPlayerLevel(p).getExpToLevelUp();
			int lvl = system.getPlayerLevel(p).getLevel();
			p.sendMessage("Exp : " + Exp + "/" + ExpLevel + ", Lvl : " + lvl);
		}
		
		return false;
	}

}
