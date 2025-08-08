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
	private int MaxDistanceToSpawn = 200;
	
	public BossSpawner(E33UHC plugin){
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		
		Location SpawnLoc = getRandomLocation();
		while(SpawnLoc == null) {
			SpawnLoc = getRandomLocation();
		}
		spawnBoss(SpawnLoc);
		Bukkit.broadcastMessage("Un Boss est apparu en :§a" + SpawnLoc.getBlockX() + ", " + SpawnLoc.getBlockY() + ", " + SpawnLoc.getBlockZ());
		
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
	
	@SuppressWarnings("unused")
	private void spawnBoss(Location loc) {
		
		int max = 4;
		int i = random.nextInt(1);
		
		switch (i) {
		case 1: {
			Zombie z = loc.getWorld().spawn(loc, Zombie.class);
	        z.setCustomName("§5Eveque");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(15.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(50.0);
	        z.setHealth(z.getAttribute(Attribute.MAX_HEALTH).getValue());
	        z.getAttribute(Attribute.SCALE).setBaseValue(2.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Boss");
		}
		case 2: {
			Zombie z = loc.getWorld().spawn(loc, Zombie.class);
	        z.setCustomName("§eLampMaster");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(15.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(50.0);
	        z.setHealth(z.getAttribute(Attribute.MAX_HEALTH).getValue());
	        z.getAttribute(Attribute.SCALE).setBaseValue(2.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Boss");
		}
		case 3: {
			ZombieHorse z = loc.getWorld().spawn(loc, ZombieHorse.class);
	        z.setCustomName("§8Création");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(15.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(50.0);
	        z.setHealth(z.getAttribute(Attribute.MAX_HEALTH).getValue());
	        z.getAttribute(Attribute.SCALE).setBaseValue(4.0);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Boss");
		}
		case 4: {
			ZombieHorse z = loc.getWorld().spawn(loc, ZombieHorse.class);
	        z.setCustomName("§bStalact");
	        z.setCustomNameVisible(true);
	        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(15.0);
	        z.getAttribute(Attribute.MAX_HEALTH).setBaseValue(50.0);
	        z.setHealth(z.getAttribute(Attribute.MAX_HEALTH).getValue());
	        z.getAttribute(Attribute.SCALE).setBaseValue(3.5);
	        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
	        z.setVisualFire(false);
	        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Boss");
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + i);
		}
        
    }

}
