package fr.jinxss.e33.PictoSystem.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.jinxss.e33.PictoSystem.PictoSystem;

public class CommandPicto implements CommandExecutor {

	private PictoSystem system;
	
	public CommandPicto(PictoSystem system) {
		this.system = system;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player p) {
			
			p.openInventory( system.getPlayerPictos(p).getPictoMenu() );
			
		}
		
		return false;
	}

}
