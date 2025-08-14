package fr.jinxss.e33.mobsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvokeMobsCommand implements CommandExecutor  {

	private String SummonBoss = "Boss";
	private AxonSpawner Axon;
	
	private String SummonAxon = "Axon";
	private BossSpawner Boss;
	
	public InvokeMobsCommand(AxonSpawner Axon, BossSpawner Boss) {
		this.Axon = Axon;
		this.Boss = Boss;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		
		
		if(args.length >= 1 && sender instanceof Player p) {
			
			if(args[0].equalsIgnoreCase(SummonAxon)) {
				
				Axon.spawnAxon(p.getLocation());
				
			}
			if(args[0].equalsIgnoreCase(SummonBoss)) {
				
				
				Boss.spawnBoss(p.getLocation());
				
			}
			
		}
		
		return false;
	}

}
