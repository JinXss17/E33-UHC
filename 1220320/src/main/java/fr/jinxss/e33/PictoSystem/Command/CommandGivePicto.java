package fr.jinxss.e33.PictoSystem.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.jinxss.e33.PictoSystem.PictoSystem;

public class CommandGivePicto implements CommandExecutor {

	private PictoSystem system;
	
	public CommandGivePicto(PictoSystem system) {
		this.system = system;
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(args.length < 1)return false;
		
		if(Bukkit.getPlayer(args[0]) != null) {
			Player p = Bukkit.getPlayer(args[0]);
			
			system.GiveRandomPictoToPlayer(p);
			
			return true;
			
		}
		
		return false;
	}

}
