package fr.jinxss.e33.Grades;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GradeCommandExecutor implements CommandExecutor {

	private GradeManager manager;
	
	public GradeCommandExecutor(GradeManager manager) {
		this.manager = manager;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player p && p.isOp()) {
			
			if(args.length == 2 && Bukkit.getPlayer(args[0]) != null) {
				
				manager.setGradeToPlayer(Bukkit.getPlayer(args[0]), args[1]);
				return true;
			}
			
		}
		
		return false;
	}

}
