package fr.jinxss.e33.mobsystem;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Zombie;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.jinxss.e33.E33UHC;

public class AxonSpawner extends MobSystem {

	
	private E33UHC plugin;
	private int TimeToSpawnBoss = 30;
	private int MaxDistanceToSpawn = 200;
	
	private List<Integer> AxonOrder = List.of( 1, 2, 3, 4);
	private int PickAxon = 0;
	
	public AxonSpawner(E33UHC plugin){
		this.plugin = plugin;
		//Collections.shuffle(AxonOrder);
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
				200,
				Math.cos(random.nextFloat()) * MaxDistanceToSpawn);
		boolean blockIsGround = false;
		int Y = SpawnLoc.getBlockY();
		int count = 0;
		while (false || count < 150) {
			SpawnLoc = new Location(Bukkit.getWorld("world"),
					Math.sin(random.nextFloat()) * MaxDistanceToSpawn,
					100,
					Math.cos(random.nextFloat()) * MaxDistanceToSpawn);
			if(SpawnLoc.getBlock().isEmpty() || SpawnLoc.getBlock().isLiquid()) {
				SpawnLoc.setY(--Y);
			}else {
				blockIsGround = true;
			}
			count++;
			if(count == 150 && blockIsGround) {
				SpawnLoc = null;
			}
		}
		
		return SpawnLoc;
	}
	
	@SuppressWarnings("deprecation")
	public void spawnAxon(Location loc) {
		
		PickAxon++;
		
		if(AxonOrder.get(PickAxon) == 1) {
			Zombie z = loc.getWorld().spawn(loc, Zombie.class);
	        z.setCustomName("§5Visage");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(30.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(500.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(4.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Visages");
			
		}
		if(AxonOrder.get(PickAxon) == 2) {
			Zombie z = loc.getWorld().spawn(loc, Zombie.class);
	        z.setCustomName("§6Sirene");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(30.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(500.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(4.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Sirene");
			
		}
		if(AxonOrder.get(PickAxon) == 3) {
			Zombie z = loc.getWorld().spawn(loc, Zombie.class);
	        z.setCustomName("§4Faucheuse");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(30.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(500.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(3.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Faucheuse");
			
		}
		if(AxonOrder.get(PickAxon) == 4) {
			Zombie z = loc.getWorld().spawn(loc, Zombie.class);
	        z.setCustomName("§2Transporteuse");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(30.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(500.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(10.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Transporteuse");
			
		}
    }
}
