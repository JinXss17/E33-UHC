package fr.jinxss.e33.uhcsystem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class ReviveTabCompleter implements TabCompleter {

	UHCSystem system;
	
	public ReviveTabCompleter(UHCSystem system) {
		
		this.system = system;
		
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String arg, String[] args) {
		
		
		if(args.length == 1) {
			
			ArrayList<String> list = new ArrayList<String>();
			list.add("All");
			for(Player p : system.getDeadPlayers()) {
				list.add(p.getName());
			}
			
			return list;
			
		}
		
		return null;
	}

}
