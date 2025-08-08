package fr.jinxss.e33.mobsystem;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;

import fr.jinxss.e33.E33UHC;

public class CustomMobsListener implements Listener {

	private E33UHC plugin;
	private float PictoDropRate = 2;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onKillMobs(EntityDeathEvent e) {
		
		if(e.getDamageSource().getCausingEntity() instanceof Player p) {
			Entity entity = e.getEntity();
			if(entity.getPersistentDataContainer().get(plugin.getCustomKey(), PersistentDataType.STRING).equalsIgnoreCase("Boss")) {
				plugin.getPictoSystem().GiveRandomPictoToPlayer(p);
			}
			if(entity.getPersistentDataContainer().get(plugin.getCustomKey(), PersistentDataType.STRING).equalsIgnoreCase("Custom")) {
				Random r = new Random();
				if(r.nextFloat() * 100 <= PictoDropRate)plugin.getPictoSystem().GiveRandomPictoToPlayer(p);
			}
			
		}
		
	}
	
}
