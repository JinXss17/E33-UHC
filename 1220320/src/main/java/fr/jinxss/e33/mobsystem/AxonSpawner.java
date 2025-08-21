package fr.jinxss.e33.mobsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Bogged;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.jinxss.e33.E33UHC;

public class AxonSpawner extends MobSystem {

	
	private E33UHC plugin;
	private int TimeToSpawnBoss = 30;
	private int MaxDistanceToSpawn = 200;
	
	private ArrayList<Integer> AxonOrder = new ArrayList<Integer>();
	private int PickAxon = 0;
	
	public AxonSpawner(E33UHC plugin){
		this.plugin = plugin;
		AxonOrder.addAll( List.of(1,2,3,4) );
		Collections.shuffle(AxonOrder);
	}
	
	@Override
	public void run() {
		
		Location SpawnLoc = getRandomLocation();
		while(SpawnLoc == null) {
			SpawnLoc = getRandomLocation();
		}
		spawnAxon(SpawnLoc);
		Bukkit.broadcastMessage("Un Axon est apparu en :§4" + SpawnLoc.getBlockX() + ", " + SpawnLoc.getBlockY() + ", " + SpawnLoc.getBlockZ());
		
	}
	
	@Override
    public void StartSummonning() {
    	runTaskTimer(plugin, 0, 20 * 60 *TimeToSpawnBoss);
    }
	
	private Location getRandomLocation() {
		Location SpawnLoc = new Location(Bukkit.getWorld("world"),
				Math.sin(random.nextFloat()) * MaxDistanceToSpawn,
				300,
				Math.cos(random.nextFloat()) * MaxDistanceToSpawn);
		findSafeLocationNear(SpawnLoc);
		
		return SpawnLoc;
	}
	
	@SuppressWarnings("deprecation")
	public void spawnAxon(Location loc) {
		
		PickAxon++;
		if(PickAxon > 3) {
			PickAxon = 0;
			Collections.shuffle(AxonOrder);
		}
		
		if(AxonOrder.get(PickAxon) == 1) {
			Evoker z = loc.getWorld().spawn(loc, Evoker.class);
	        z.setCustomName("§5Visage");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(30.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(100.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(4.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Visages");
			
		}
		if(AxonOrder.get(PickAxon) == 2) {
			Vindicator z = loc.getWorld().spawn(loc, Vindicator.class);
	        z.setCustomName("§6Sirene");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(30.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(100.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(4.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Sirene");
			
		}
		if(AxonOrder.get(PickAxon) == 3) {
			WitherSkeleton z = loc.getWorld().spawn(loc, WitherSkeleton.class);
	        z.setCustomName("§4Faucheuse");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(30.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(100.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(3.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Faucheuse");
			
		}
		if(AxonOrder.get(PickAxon) == 4) {
			Bogged z = loc.getWorld().spawn(loc, Bogged.class);
	        z.setCustomName("§2Transporteuse");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(30.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(100.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(10.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Transporteuse");
			
		}
    }
}
