package fr.jinxss.e33.mobsystem;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.PictoSystem.Pictos.Special.DrawerPower;
import fr.jinxss.e33.PictoSystem.Pictos.Special.Incendie;
import fr.jinxss.e33.PictoSystem.Pictos.Special.NouvellePeinture;
import fr.jinxss.e33.PictoSystem.Pictos.Special.Roulette;

public class CustomMobsListener implements Listener {

	private E33UHC plugin;
	private float PictoDropRate = 5;
	
	private float BossExp = 500;
	private float AxonExp = 1000;
	
	public CustomMobsListener(E33UHC plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onKillMobs(EntityDeathEvent e) {
		
		if(e.getDamageSource().getCausingEntity() instanceof Player p) {
			Entity entity = e.getEntity();
			
			if(entity.getPersistentDataContainer().get(plugin.getCustomKey(), PersistentDataType.STRING) == null) return;
			
			if(entity.getPersistentDataContainer().get(plugin.getCustomKey(), PersistentDataType.STRING).equalsIgnoreCase("Boss")) {
				plugin.getLevelSystem().getPlayerLevel(p).addExp(BossExp);
				plugin.getPictoSystem().GiveRandomPictoToPlayer(p);
				p.sendMessage("§aVous avez tuer un Boss !");
			}
			if(entity.getPersistentDataContainer().get(plugin.getCustomKey(), PersistentDataType.STRING).equalsIgnoreCase("Custom")) {
				Random r = new Random();
				if(r.nextFloat() * 100 <= PictoDropRate)plugin.getPictoSystem().GiveRandomPictoToPlayer(p);
			}
			if(entity.getPersistentDataContainer().get(plugin.getCustomKey(), PersistentDataType.STRING).equalsIgnoreCase("Visages") ) {
				p.sendMessage("§dVous avez tuer Visages !");
				Bukkit.broadcastMessage("§dVisages a été éliminé !");
				plugin.getPictoSystem().getPlayerPictos(p).addToPictoList(new Incendie());
				plugin.getLevelSystem().getPlayerLevel(p).addExp(AxonExp);
			}
			if(entity.getPersistentDataContainer().get(plugin.getCustomKey(), PersistentDataType.STRING).equalsIgnoreCase("Sirene") ) {
				p.sendMessage("§dVous avez tuer Sirène !");
				Bukkit.broadcastMessage("§dLa Sirene a été éliminée !");
				plugin.getPictoSystem().getPlayerPictos(p).addToPictoList(new Roulette());
				plugin.getLevelSystem().getPlayerLevel(p).addExp(AxonExp);
			}
			if(entity.getPersistentDataContainer().get(plugin.getCustomKey(), PersistentDataType.STRING).equalsIgnoreCase("Faucheuse") ) {
				p.sendMessage("§dVous avez tuer Faucheuse !");
				Bukkit.broadcastMessage("§dLa Faucheuse a été éliminée !");
				plugin.getPictoSystem().getPlayerPictos(p).addToPictoList(new NouvellePeinture(p,
						plugin.getPictoSystem().getPlayerPictos(p),
						plugin.getUHCSystem().getBorder()));
				plugin.getLevelSystem().getPlayerLevel(p).addExp(AxonExp);
			}
			if(entity.getPersistentDataContainer().get(plugin.getCustomKey(), PersistentDataType.STRING).equalsIgnoreCase("Transporteuse") ) {
				p.sendMessage("§dVous avez tuer la Transporteuse !");
				Bukkit.broadcastMessage("§dLa Transporteuse a été éliminée !");
				plugin.getPictoSystem().getPlayerPictos(p).addToPictoList(new DrawerPower());
				plugin.getLevelSystem().getPlayerLevel(p).addExp(AxonExp);
			}
			
		}
		
	}
	
}
