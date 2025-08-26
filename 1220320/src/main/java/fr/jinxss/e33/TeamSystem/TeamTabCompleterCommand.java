package fr.jinxss.e33.TeamSystem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.jinxss.e33.TeamSystem.List.TeamList;

public class TeamTabCompleterCommand implements TabCompleter {
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String arg, String[] args) {
		
		
		if(sender instanceof Player p && p.isOp()) {
			if(args.length == 3) {
				ArrayList<String> list = new ArrayList<String>();
				
				for(TeamList team : TeamList.values()) {
					list.add(team.toString());
				}
				
				return list;
			}
			
			if(args.length == 1) {
				List<String> list = List.of("Create");
				return list;
			}
		}
		return null;
	}

}
