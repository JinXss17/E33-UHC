package fr.jinxss.e33.TeamSystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.jinxss.e33.TeamSystem.List.TeamList;
import net.md_5.bungee.api.ChatColor;

public class TeamCommand implements CommandExecutor {

	private TeamSystem system;
	private String CreateTeamCommand = "Create";
	
	public TeamCommand(TeamSystem system) {
		this.system = system;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player p) {
			
			if(args.length == 0) {
				system.OpenTeamInventory(p);
			}
			
			if(args.length == 3) {
				
				if(args[0].equalsIgnoreCase(CreateTeamCommand) && p.isOp()) {
					
					TeamList teamList = TeamList.RED;
					
					for(TeamList teamL : TeamList.values()) {
						if(teamL.toString().equalsIgnoreCase(args[2])) {
							teamList = teamL;
						}
					}
					
					system.registerNewTeam(teamList, args[1]);
					
					p.sendMessage("Team " + teamList.getChatColor() + args[1] + ChatColor.RESET +  " Created");
					
				}
			}
			
			return true;
		}
		return false;
	}
}
