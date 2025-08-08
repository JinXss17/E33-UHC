package fr.jinxss.e33.mobsystem;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class MobSystem extends BukkitRunnable {


    protected final Random random = new Random();
	
	@Override
	public void run() {
		
	}
	
	public void StartSummonning() {
		return;
	}
	
	
	protected Location findSafeLocationNear(Location origin) {
        for (int i = 0; i < 10; i++) {
            int dx = random.nextInt(16) - 8;
            int dz = random.nextInt(16) - 8;
            Location loc = origin.clone().add(dx, 0, dz);
            loc.setY(loc.getWorld().getHighestBlockYAt(loc));
            if (loc.getBlock().getType().isSolid()) return loc.add(0, 1, 0);
        }
        return null;
    }

}
