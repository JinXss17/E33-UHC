package fr.jinxss.e33.PictoSystem;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.RoleManager;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.EsquiveParfaite;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.GardeOptimal;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.Inversion;
import fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto.TirPrecis;
import fr.jinxss.e33.PictoSystem.Pictos.PictoMixtes.AgilliteFeline;
import fr.jinxss.e33.PictoSystem.Pictos.PictoMixtes.ContreParfait;
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
	public void OnClickInventory(InventoryClickEvent e) {
		
		if(e.getWhoClicked() instanceof Player p ) {
			if(e.getView().getTitle().equalsIgnoreCase("Pictos")) {
				
				e.setCancelled(true);
				PlayerPictos pictos = system.getPlayerPictos(p);
				if(e.getSlot() > pictos.getPictoList().size()) {
					return;
				}else {
					Picto picto = pictos.getPictoList().get(e.getSlot());
					if (pictos.getActivatedPicto().contains( picto ) ) {
						pictos.getActivatedPicto().remove(picto);
					}else {
						pictos.getActivatedPicto().add(picto);
					}
				}
				p.closeInventory();
				Bukkit.getScheduler().runTaskLater(plugin, () -> {p.openInventory(pictos.getPictoMenu()); }, 1);
				
	
			}
		}
		
	}
	
	@EventHandler
	public void OnEat(PlayerItemConsumeEvent e) {
		
		Player p = e.getPlayer();
		
		if(e.getItem().getType()== Material.GOLDEN_APPLE) {
			if(system.getPlayerPictos(p).HasPictoActivated(Inversion.class)) {
				Inversion picto = (Inversion)system.getPlayerPictos(p).GetPictoActivated(Inversion.class);
				p.setAbsorptionAmount(picto.getRemovedAbso());
			}
		}
	}
	
	@EventHandler
	public void OnDamageEntity(EntityDamageEvent e) {
		
		DamageCause cause = e.getCause();
		double Damage = e.getDamage();
		
		if(cause == DamageCause.FALL && e.getEntity() instanceof Player p) {
			
			PlayerPictos pictos = system.getPlayerPictos(p);
			
			if(pictos.HasPictoActivated(AgilliteFeline.class)) {
				AgilliteFeline picto = (AgilliteFeline) system.getPlayerPictos(p).GetPictoActivated(AgilliteFeline.class);
				e.setDamage(Damage * picto.getFallDamageReduce() );
			}
			
		}
		
		if(e instanceof EntityDamageByEntityEvent E) {
			
			if(E.getDamager() instanceof Player damager && E.getEntity() instanceof Player victim) {
				PlayerPictos damagerPicto = system.getPlayerPictos(damager);
				PlayerPictos victimPicto = system.getPlayerPictos(victim);
				
				if(victimPicto.HasPictoActivated(EsquiveParfaite.class)) {
					Random r = new Random();
					EsquiveParfaite dodge = (EsquiveParfaite)victimPicto.GetPictoActivated(EsquiveParfaite.class);
					if(r.nextFloat() *100 < dodge.getEscapeRate()) {
						e.setCancelled(true);
					}
				}
				
				Damage *= system.getPlayerPictos(damager).getTotalDamageBoost(damager);
				Damage *= RoleManager.getRole(damager.getUniqueId()).getForce();
				
				
				if(victim.isBlocking() && victimPicto.HasPictoActivated(ContreParfait.class)) {
					ContreParfait contre = (ContreParfait)victimPicto.GetPictoActivated(ContreParfait.class);
					contre.Counter(damager, Damage, E);
					e.setCancelled(true);
					return;
				}
				
				if(victim.isBlocking() && victimPicto.HasPictoActivated(GardeOptimal.class)) {
					GardeOptimal garde = (GardeOptimal)victimPicto.GetPictoActivated(GardeOptimal.class);
					victim.setHealth(victim.getHealth() + garde.getHealBonus());
				}
				
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
