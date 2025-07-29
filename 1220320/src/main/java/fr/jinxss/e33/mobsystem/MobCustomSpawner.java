package fr.jinxss.e33.mobsystem;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class MobCustomSpawner extends BukkitRunnable {

    private final Plugin plugin;
    private final Random random = new Random();

    public MobCustomSpawner(Plugin plugin) {
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

    private Location findSafeLocationNear(Location origin) {
        for (int i = 0; i < 10; i++) {
            int dx = random.nextInt(16) - 8;
            int dz = random.nextInt(16) - 8;
            Location loc = origin.clone().add(dx, 0, dz);
            loc.setY(loc.getWorld().getHighestBlockYAt(loc));
            if (loc.getBlock().getType().isSolid()) return loc.add(0, 1, 0);
        }
        return null;
    }

    private void spawnLancier(Location loc) {
        Zombie z = loc.getWorld().spawn(loc, Zombie.class);
        z.setCustomName("§3Lancier");
        z.setCustomNameVisible(true);
        z.getEquipment().setItemInMainHand(new ItemStack(Material.TRIDENT));
        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(6.0);
        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
        z.setVisualFire(false);
        z.getPersistentDataContainer().set(new NamespacedKey(plugin, "custom_mob"), PersistentDataType.STRING, "lancier");
    }

    private void spawnMobClair(Location loc) {
        Zombie z = loc.getWorld().spawn(loc, Zombie.class);
        z.setCustomName("§fClair");
        z.setCustomNameVisible(true);
        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(3.0);
        z.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.322);
        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
        z.setVisualFire(true);
        z.getPersistentDataContainer().set(new NamespacedKey(plugin, "custom_mob"), PersistentDataType.STRING, "mob_clair");
    }

    private void spawnMobObscur(Location loc) {
        Zombie z = loc.getWorld().spawn(loc, Zombie.class);
        z.setCustomName("§8Obscur");
        z.setCustomNameVisible(true);
        z.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(8.0);
        z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
        z.setVisualFire(false);
        z.getPersistentDataContainer().set(new NamespacedKey(plugin, "custom_mob"), PersistentDataType.STRING, "mob_obscur");
    }
}
