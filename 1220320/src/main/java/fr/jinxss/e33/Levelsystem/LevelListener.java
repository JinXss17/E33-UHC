package fr.jinxss.e33.Levelsystem;

import java.util.Random;

import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class LevelListener implements Listener {

	private LevelSystem levelSystem;
	
	public LevelListener(LevelSystem system) {
		
		this.levelSystem = system;
		
	}
	
	private int MidExpOnKillEntity = 50;
	private int ExpRange= 25;
	
	@EventHandler (priority = EventPriority.LOW)
	public void OnEntityDeath(EntityDeathEvent e) {
		
		if(e.getEntity() instanceof Player)return;
		DamageSource damage = e.getDamageSource();
		if(damage.getCausingEntity() instanceof Player player) {
			Random r = new Random();
			
			PlayerLevel pLevel = levelSystem.getPlayerLevel(player);
			float AddExp = (float) (MidExpOnKillEntity + ( Math.sin(r.nextFloat())*ExpRange )) ;
			pLevel.addExp(AddExp);
		}
	}
}
