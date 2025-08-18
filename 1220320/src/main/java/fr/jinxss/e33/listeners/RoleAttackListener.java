package fr.jinxss.e33.Listeners;

import fr.jinxss.e33.RoleManager;
import fr.jinxss.e33.e33system.roles.Lune;
import fr.jinxss.e33.e33system.roles.Lune.Element;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RoleAttackListener implements Listener {

    @EventHandler (priority = EventPriority.HIGH)
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player damager)) return;
        if (!(event.getEntity() instanceof Player target)) return;

        if (!RoleManager.hasRole(damager.getUniqueId())) return;
        if ((RoleManager.getRole(damager.getUniqueId()) instanceof Lune lune)) {
	
	        Element current = lune.getCurrentElement();
	
	        switch (current) {
	            case FEU:
	                target.setFireTicks(60); // 3 secondes de feu
	                break;
	            case GLACE:
	                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 1)); // Slowness 2 pour 3s
	                break;
	            default:
	                break;
	        }
        }
    }
}