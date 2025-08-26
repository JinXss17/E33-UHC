package fr.jinxss.e33.mobsystem;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class InvokeMobsTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player p && p.isOp()) {
			
			if(args.length == 1) {
				
				return List.of("Axon", "Boss");
			}
			
		}
		
		
		return null;
	}

}
