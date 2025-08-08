package fr.jinxss.e33.mobsystem;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.jinxss.e33.E33UHC;

public class MobCustomSpawner extends MobSystem {

    private final E33UHC plugin;
    
    private int TimeToSpawnCustomMobs = 5;
    

    public MobCustomSpawner(E33UHC plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        List<? extends Player> players = Bukkit.getOnlinePlayers().stream().toList();
        if (players.isEmpty()) return;

        Player target = players.get(random.nextInt(players.size()));

        Location spawnLoc = findSafeLocationNear(target.getLocation());
        if (spawnLoc == null) return;

        int roll = random.nextInt(100);
        if (roll < 33) {
        	spawnMobClair(spawnLoc);
        } else if (roll < 66) {
        	spawnMobObscur(spawnLoc);
        } else if (roll >= 66) {
            spawnLancier(spawnLoc);
        }
    }
    
    @Override
    public void StartSummonning() {
    	runTaskTimer(plugin, 0, 20 * TimeToSpawnCustomMobs);
    }

    private void spawnLancier(Location loc) {
        Zombie z = loc.getWorld().spawn(loc, Zombie.class);
        z.setCustomName("§3Lancier");
        z.setCustomNameVisible(true);
        z.getEquipment().setItemInMainHand(new ItemStack(Material.TRIDENT));
        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(6.0);
        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
        z.setVisualFire(true);
        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Custom");
    }

    private void spawnMobClair(Location loc) {
        Zombie z = loc.getWorld().spawn(loc, Zombie.class);
        z.setCustomName("§fClair");
        z.setCustomNameVisible(true);
        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(3.0);
        z.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.322);
        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
        z.setVisualFire(true);
        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Custom");
    }

    private void spawnMobObscur(Location loc) {
        Zombie z = loc.getWorld().spawn(loc, Zombie.class);
        z.setCustomName("§8Obscur");
        z.setCustomNameVisible(true);
        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(8.0);
        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
        z.setVisualFire(true);
        z.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Custom");
    }
}
