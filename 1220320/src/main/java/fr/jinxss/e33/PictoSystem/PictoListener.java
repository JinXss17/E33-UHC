package fr.jinxss.e33.PictoSystem;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.RoleManager;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;
import fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto.TirPrecis;
import fr.jinxss.e33.PictoSystem.Pictos.RessourcePicto.MineurChanceux;
import fr.jinxss.e33.PictoSystem.Pictos.RessourcePicto.MineurDor;
import fr.jinxss.e33.PictoSystem.Pictos.Special.DrawerPower;

public class PictoListener implements Listener {

	private PictoSystem system;
	private E33UHC plugin;
	
	public PictoListener(E33UHC plugin, PictoSystem system) {
		this.plugin = plugin;
		this.system = system;
		
	}
	
	@EventHandler
	public void OnDamageEntity(EntityDamageEvent e) {
		
		DamageCause cause = e.getCause();
		double Damage = e.getDamage();
		
		if(cause == DamageCause.FALL && e.getEntity() instanceof Player p) {
			
			PlayerPictos pictos = system.getPlayerPictos(p);
			
			if(pictos.HasPictoActivated(Picto.class)) {
				
				e.setDamage(Damage * pictos.getTotalResistanceBoost() );
			}
			
		}
		
		if(e instanceof EntityDamageByEntityEvent E) {
			
			if(E.getDamager() instanceof Player damager && E.getEntity() instanceof Player victim) {
				PlayerPictos damagerPicto = system.getPlayerPictos(damager);
				Damage *= system.getPlayerPictos(damager).getTotalDamageBoost();
				Damage *= RoleManager.getRole(damager.getUniqueId()).getForce();
				Damage *= system.getPlayerPictos(victim).getTotalResistanceBoost();
				Damage *= RoleManager.getRole(victim.getUniqueId()).getResi();
				
				if(damagerPicto.HasPictoActivated(DrawerPower.class) && E.getFinalDamage() > 6) {
					e.setDamage(6);
				}
			}
			
			if(E.getDamager() instanceof Arrow arrow) {
				if(arrow.getShooter() instanceof Player damager && E.getEntity() instanceof Player victim) {
					
					if(system.getPlayerPictos(damager).HasPictoActivated(TirPrecis.class)) {
						Damage *= system.getPlayerPictos(damager).GetPictoActivated(TirPrecis.class).DamageBoost;
						Damage *= system.getPlayerPictos(victim).getTotalResistanceBoost();
						Damage *= RoleManager.getRole(victim.getUniqueId()).getResi();
					}
					
				}
			}
		}
		
	}
	
	@EventHandler
	public void OnBlockBreak(BlockBreakEvent e) {
		
		Player p = e.getPlayer();
		Block block = e.getBlock();
		int Exp = e.getExpToDrop(); 
		Collection<ItemStack> drops = block.getDrops();
		Random r = new Random();
		
		for(Picto picto : system.getPlayerPictos(p).getActivatedPicto()) {
			if(picto instanceof MineurChanceux) {
				
				MineurChanceux mineur = (MineurChanceux)picto;
				if(MineurChanceux.MineraisBoost.contains(block.getType()) && r.nextFloat() * 100 < mineur.getDropRate()) {
					for(ItemStack it : drops) {
						it.setAmount( it.getAmount() * mineur.getDropMultiplicate() );
						Exp *= mineur.getDropMultiplicate();
						e.setExpToDrop(Exp);
						block.getWorld().dropItemNaturally(block.getLocation(), it);
					}
				}
				
			}
			if(picto instanceof MineurDor) {
				
				MineurDor mineur = (MineurDor)picto;
				if(MineurChanceux.MineraisBoost.contains(block.getType()) && r.nextFloat() * 100 < mineur.getDropRate()) {
					Bukkit.getScheduler().runTaskLater(plugin, () -> { mineur.DropBonus(block.getLocation()); }, 1);
					
				}
				
			}
			
		}
	}
	
}
