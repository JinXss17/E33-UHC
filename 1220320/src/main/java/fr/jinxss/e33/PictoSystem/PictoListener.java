package fr.jinxss.e33.PictoSystem;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.EsquiveParfaite;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.GardeOptimal;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.Inversion;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.Survivaliste;
import fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto.CompressionDeLumina;
import fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto.TirMarquant;
import fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto.TirPrecis;
import fr.jinxss.e33.PictoSystem.Pictos.PictoMixtes.AgilliteFeline;
import fr.jinxss.e33.PictoSystem.Pictos.PictoMixtes.ContreParfait;
import fr.jinxss.e33.PictoSystem.Pictos.RessourcePicto.MineurChanceux;
import fr.jinxss.e33.PictoSystem.Pictos.RessourcePicto.MineurDor;
import fr.jinxss.e33.PictoSystem.Pictos.Special.DrawerPower;
import fr.jinxss.e33.PictoSystem.Pictos.Special.Incendie;
import fr.jinxss.e33.PictoSystem.Pictos.Special.NouvellePeinture;
import fr.jinxss.e33.PictoSystem.Pictos.Special.Roulette;
import fr.jinxss.e33.RolesSystem.RoleManager;
import fr.jinxss.e33.uhcsystem.list.EGameStates;

public class PictoListener implements Listener {

	private PictoSystem system;
	private E33UHC plugin;
	
	private String PictoNotActivate = "Vous ne pouvez pas activé ce Picto !";
	private String PictoInvName = "Pictos";
	
	public PictoListener(E33UHC plugin, PictoSystem system) {
		this.plugin = plugin;
		this.system = system;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.LOWEST)
	public void OnKillMobs(EntityDeathEvent e) {
		if(e.getEntity() instanceof Monster && e.getDamageSource().getCausingEntity() instanceof Player p) {
			Random r = new Random();
			PlayerPictos pictos = system.getPlayerPictos(p);
			for(Picto picto : pictos.getActivatedPicto()) {
				picto.AddExp();
			}
			if(r.nextFloat() *100 <= plugin.getPictoDropRate() && !plugin.isMobsToggled()) {
				system.GiveRandomPictoToPlayer(p);
			}
			
		}
	}
	
	@EventHandler (priority = EventPriority.LOW)
	public void OnClickInventory(InventoryClickEvent e) {
		
		if(e.getWhoClicked() instanceof Player p ) {
			
	//PictoInv		
			if(e.getView().getTitle().equalsIgnoreCase(PictoInvName)) {
				
				e.setCancelled(true);
				PlayerPictos pictos = system.getPlayerPictos(p);
				if(e.getSlot() > pictos.getPictoList().size()) {
					return;
				}else {
					Picto picto = pictos.getPictoList().get(e.getSlot());
					if (pictos.getActivatedPicto().contains( picto ) ) {
						pictos.removeToPictoActivated(picto);
					}else {
						if(!pictos.canActivatePicto(picto)) {
							p.sendMessage(PictoNotActivate);
						}
						pictos.addToPictoActivated(picto);
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
				Bukkit.getScheduler().runTaskLater(plugin, () -> {
					if(p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
						p.setAbsorptionAmount(picto.getRemovedAbso());
					}
				}, 1);
			}
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void OnDamageEntity(EntityDamageEvent e) {
		
		DamageCause cause = e.getCause();
		double Damage = e.getDamage();
		
		if(e.getEntity() instanceof Player p && system.getPlayerPictos(p).HasPictoActivated(Survivaliste.class)) {
			Survivaliste picto = (Survivaliste)system.getPlayerPictos(p).GetPictoActivated(Survivaliste.class);
			Damage *= (1 -  picto.ResistanceBoost/100);
		}
		
		
		if(cause == DamageCause.FALL && e.getEntity() instanceof Player p) {
			
			PlayerPictos pictos = system.getPlayerPictos(p);
			
			if(p.getFallDistance() >= 30) {
				e.setCancelled(true);
			}
			
			if(pictos.HasPictoActivated(AgilliteFeline.class)) {
				AgilliteFeline picto = (AgilliteFeline) system.getPlayerPictos(p).GetPictoActivated(AgilliteFeline.class);
				e.setDamage(Damage * picto.getFallDamageReduce() );
				p.spawnParticle(Particle.POOF , p.getLocation(), 20);
			}
			
		}
		
	//Entity Hit By Entity
		if(e instanceof EntityDamageByEntityEvent E) {
			
			//Entity Hit Player		
			if(E.getEntity() instanceof Player victim) {
				
				PlayerPictos victimPicto = system.getPlayerPictos(victim);
				
				if(victimPicto.IsMarked()) {
					Damage *= victimPicto.getMarkDamageMultiplier();
				}
				
				if(victimPicto.HasPictoActivated(EsquiveParfaite.class)) {
					Random r = new Random();
					EsquiveParfaite dodge = (EsquiveParfaite)victimPicto.GetPictoActivated(EsquiveParfaite.class);
					if(r.nextFloat() *100 < dodge.getEscapeRate()) {
						e.setCancelled(true);
						victim.spawnParticle(Particle.POOF , victim.getLocation(), 20);
					}
				}
				if(victim.isBlocking() && victimPicto.HasPictoActivated(GardeOptimal.class)) {
					GardeOptimal garde = (GardeOptimal)victimPicto.GetPictoActivated(GardeOptimal.class);
					victim.setHealth(victim.getHealth() + garde.getHealBonus());
				}
				Damage *= system.getPlayerPictos(victim).getTotalResistanceBoost();
				if(plugin.isRolesToggled() && RoleManager.getRole(victim.getUniqueId()) != null) {
					Damage *= RoleManager.getRole(victim.getUniqueId()).getResi();
				}
			}
	
			//Player Hit Entity 		
			if(E.getDamager() instanceof Player damager) {
				PlayerPictos damagerPicto = system.getPlayerPictos(damager);
				
				if(damagerPicto.HasPictoActivated(CompressionDeLumina.class)) {
					
					CompressionDeLumina picto = (CompressionDeLumina) damagerPicto.GetPictoActivated(CompressionDeLumina.class);
					Damage *= picto.getBonusDamage();
				}
				
				if(damagerPicto.HasPictoActivated(Roulette.class)) {
					
					Roulette picto = (Roulette) damagerPicto.GetPictoActivated(Roulette.class);
					picto.damageRoll(Damage);
				}
				
				if(system.getPlayerPictos(damager).HasPictoActivated(Incendie.class)) {
					Incendie picto = (Incendie) system.getPlayerPictos(damager).GetPictoActivated(Incendie.class);
					for(Entity entity : damager.getNearbyEntities(picto.getFireRay(), picto.getFireRay(), picto.getFireRay())) {
							entity.setFireTicks(picto.getFireTick());
							Bukkit.getWorld("world").spawnParticle(Particle.CAMPFIRE_COSY_SMOKE , entity.getLocation(), 20);
					}
				}
				
				Damage *= system.getPlayerPictos(damager).getTotalDamageBoost(damager);
				
				if(plugin.isRolesToggled() && RoleManager.getRole(damager.getUniqueId()) != null) {
					Damage *= RoleManager.getRole(damager.getUniqueId()).getForce();
				}
				
				e.setDamage(Damage);
				if(!damagerPicto.HasPictoActivated(DrawerPower.class) && E.getFinalDamage() > 6) {
					Damage = 6;
					e.setDamage(Damage);
				}
				damager.sendMessage("Damage : " + Damage);
			}
			
			//Player Hit Player		
			if(E.getEntity() instanceof Player victim && E.getDamager() instanceof Player damager) {
				
				@SuppressWarnings("unused")
				PlayerPictos damagerPicto = system.getPlayerPictos(damager);
				PlayerPictos victimPicto = system.getPlayerPictos(victim);
				
				if(victim.isBlocking() && victimPicto.HasPictoActivated(ContreParfait.class)) {
					ContreParfait contre = (ContreParfait)victimPicto.GetPictoActivated(ContreParfait.class);
					contre.Counter(damager, Damage, E);
					e.setCancelled(true);
					return;
				}
				victim.sendMessage("Damage : " + Damage);
			}
			
			
			//Player's Arrow Hit Entity
			if(E.getDamager() instanceof Arrow arrow &&
					arrow.getShooter() instanceof Player damager &&
					E.getEntity() instanceof Player victim) {
				if(system.getPlayerPictos(damager).HasPictoActivated(TirPrecis.class)) {
					Damage *= 1 + (system.getPlayerPictos(damager).GetPictoActivated(TirPrecis.class).DamageBoost/100);
					Damage *= system.getPlayerPictos(victim).getTotalResistanceBoost();
					
					if(plugin.isRolesToggled() && RoleManager.getRole(victim.getUniqueId()) != null) {
						Damage *= RoleManager.getRole(victim.getUniqueId()).getResi();
					}
					
				}
				if(system.getPlayerPictos(damager).HasPictoActivated(TirMarquant.class)) {
					system.getPlayerPictos(victim).applyMark();
					((TirMarquant)system.getPlayerPictos(damager).GetPictoActivated(TirMarquant.class)).StealHealth(victim, plugin);
				}
				
				e.setDamage(Damage);
				damager.sendMessage("Damage : " + Damage);
				
			}
		}
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void OnPlayerRespawn(PlayerRespawnEvent e) {
		
		Player p = e.getPlayer();
		
		if(system.getPlayerPictos(p).HasPictoActivated(NouvellePeinture.class)) {
			
			NouvellePeinture picto = (NouvellePeinture) system.getPlayerPictos(p).GetPictoActivated(NouvellePeinture.class);
			Bukkit.getScheduler().runTaskLater(plugin, () ->  picto.Revive(), 1);
			
		}
		
		
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void OnBlockBreak(BlockBreakEvent e) {
		
		Player p = e.getPlayer();
		Block block = e.getBlock();
		int Exp = e.getExpToDrop(); 
		Collection<ItemStack> drops = block.getDrops();
		Random r = new Random();
		
		if(plugin.getUHCSystem().getGameState() == EGameStates.Waiting && p.getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
			return;
		}
		
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
