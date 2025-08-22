package fr.jinxss.e33.mobsystem;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.jinxss.e33.E33UHC;

public class BossSpawner extends MobSystem{

	private E33UHC plugin;
	private int TimeToSpawnBoss = 10;
	
	private int BossSpawned = 0;
	
	public BossSpawner(E33UHC plugin){
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		
		Location SpawnLoc = getRandomLocation();
		while(SpawnLoc == null) {
			SpawnLoc = getRandomLocation();
		}
		BossSpawned++;
		
		if(BossSpawned % 3 == 0) {
			return;
		}
		
		spawnBoss(SpawnLoc);
		Bukkit.broadcastMessage("Un Boss est apparu en :§a" + SpawnLoc.getBlockX() + ", " + SpawnLoc.getBlockY() + ", " + SpawnLoc.getBlockZ());
		
	}
	
	@Override
    public void StartSummonning() {
    	runTaskTimer(plugin, 0, 20 * 60 *TimeToSpawnBoss);
    }
	
		private Location getRandomLocation() {
		
		double X = Math.sin(random.nextFloat()) * (plugin.getUHCSystem().getBorder().getCurrentSize() - 50);
		double Y = 300;
		double Z = Math.cos(random.nextFloat()) * (plugin.getUHCSystem().getBorder().getCurrentSize() - 50);
		Location SpawnLoc = new Location(Bukkit.getWorld("world"),X,Y,Z);
		Y = SpawnLoc.getY();
		SpawnLoc = findSafeLocationNear(SpawnLoc);
		while(SpawnLoc == null || Y <= 50) {
			Y -= 1;
			SpawnLoc = new Location(Bukkit.getWorld("world"),X,Y,Z);
			SpawnLoc = findSafeLocationNear(SpawnLoc);
		}
		
		return SpawnLoc;
	}
	
	@SuppressWarnings("deprecation")
	public void spawnBoss(Location loc) {
		
		int max = 3;
		int i = random.nextInt(max)+1;
		
		if(i == 1) {
			Zombie z = loc.getWorld().spawn(loc, Zombie.class);
	        z.setCustomName("§5Eveque");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(15.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(100.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(2.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Boss");
		}
		if(i == 2) {
			Zombie z = loc.getWorld().spawn(loc, Zombie.class);
	        z.setCustomName("§eLampMaster");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(15.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(100.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(2.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Boss");
					
				}
		if(i == 3) {
			ZombieHorse z = loc.getWorld().spawn(loc, ZombieHorse.class);
	        z.setCustomName("§8Création");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(100.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(4.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Boss");
			
		}
		if(i == 4) {
			ZombieHorse z = loc.getWorld().spawn(loc, ZombieHorse.class);
	        z.setCustomName("§bStalact");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(100.0);
	        z.setHealth(z.getMaxHealth());
	        z.getAttribute(Attribute.SCALE).setBaseValue(3.5);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Boss");
			
		}
	}

}
