package fr.jinxss.e33.uhcsystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReviveCommand implements CommandExecutor {

	UHCSystem system;
	
	private String ReviveAllCommand = "All";
	
	public ReviveCommand(UHCSystem system) {
		this.system = system;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player p && p.isOp()) {
			
			if(args.length == 1) {
				if(Bukkit.getPlayer(args[0])  != null ) {
					Player cible = Bukkit.getPlayer(args[0]);
					system.revive(cible);
				}
				if(args[0].equalsIgnoreCase(ReviveAllCommand)) {
					for(Player cible : system.getDeadPlayers()) {
						system.revive(cible);
					}
				}
			}
			else return false;
			
		}
		return false;
	}
}
