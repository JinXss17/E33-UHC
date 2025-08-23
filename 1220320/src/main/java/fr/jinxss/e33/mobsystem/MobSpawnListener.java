package fr.jinxss.e33.mobsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.jinxss.e33.E33UHC;

// Ne sert a rien

public class MobSpawnListener implements Listener {
	
	private E33UHC plugin;
	private static LivingEntity getNearestPlayer(Zombie zombie) {
	    double closest = Double.MAX_VALUE;
	    LivingEntity nearest = null;

	    for (Player player : zombie.getWorld().getPlayers()) {
	        double dist = zombie.getLocation().distanceSquared(player.getLocation());
	        if (dist < closest && dist <= 400) { // max 20 blocs
	            closest = dist;
	            nearest = player;
	        }
	    }
	    return nearest;
	}

    private final Random random = new Random();
    
    public MobSpawnListener(E33UHC systeme) {
        // Tâche répétitive qui gère l'attaque à distance des Lanciers
    	
    	plugin = systeme;
        new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        if (!(entity instanceof Zombie zombie)) continue;
                        if (!"§3Lancier".equals(zombie.getCustomName())) continue;

                        LivingEntity target = getNearestPlayer(zombie);
                        if (target == null) continue;

                        double distance = zombie.getLocation().distance(target.getLocation());
                        if (distance >= 6 && distance <= 20) {
                            // Le lancier est trop loin, il lance un trident (cooldown simulé avec metadata ou un effet)
                            launchTrident(zombie, target);
                        }
                    }
                }
            }
        }.runTaskTimer(systeme, 0L, 60L); // Toutes les 3 secondes (60 ticks)
    }

    private final Map<UUID, Long> cooldowns = new HashMap<>();

    private void launchTrident(Zombie zombie, LivingEntity target) {
        UUID id = zombie.getUniqueId();
        long now = System.currentTimeMillis();

        // Cooldown de 5 secondes par mob
        if (cooldowns.containsKey(id) && now - cooldowns.get(id) < 5000) return;
        cooldowns.put(id, now);

        // Direction correcte vers la cible
        Vector direction = target.getLocation().toVector().subtract(zombie.getLocation().toVector()).normalize().multiply(1.2);

        Trident trident = zombie.launchProjectile(Trident.class);
        trident.setShooter(zombie);
        trident.setDamage(6.0);
        trident.setVelocity(direction);
        trident.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

        zombie.getWorld().playSound(zombie.getLocation(), "entity.trident.throw", 1.0f, 1.0f);
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onMobSpawn(CreatureSpawnEvent event) {
        if (event.getEntity().getWorld().getName().equalsIgnoreCase("world_nether")) return;

        LivingEntity entity = event.getEntity();

        // Chance : 10% de transformer en mob custom
        if (random.nextDouble() > 1.0) return;

        if (entity instanceof Zombie) {
            double roll = random.nextDouble();

            if (roll < 0.33) {
                makeLancier((Zombie) entity);
            } else if (roll < 0.66) {
                makeMobClair((Zombie) entity);
            } else {
                makeMobObscur((Zombie) entity);
            }
        }
    }

    // 🔱 MAGE LANCIER
    private void makeLancier(Zombie zombie) {
    	zombie.setCustomName("§3Lancier");
    	zombie.setCustomNameVisible(true);
    	zombie.getEquipment().setItemInMainHand(new ItemStack(Material.TRIDENT));
    	zombie.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(6.0);
    	zombie.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
    	zombie.setVisualFire(false);
    	zombie.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Custom");
        // Tu peux l'améliorer pour qu'il jette vraiment le trident avec une AI custom plus tard (voir note en bas)
    }

    // ☀️ MOB CLAIR
    private void makeMobClair(Zombie zombie) {
    	zombie.setCustomName("§fClair");
    	zombie.setCustomNameVisible(true);
    	zombie.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(3.0);
    	zombie.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.322);
    	zombie.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
    	zombie.setVisualFire(false);
    	zombie.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Custom");
        
    }

    // 🌑 MOB OBSCUR
    private void makeMobObscur(Zombie zombie) {
    	zombie.setCustomName("§8Obscur");
    	zombie.setCustomNameVisible(true);
    	zombie.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(8.0);
    	zombie.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
    	zombie.setVisualFire(false);
    	zombie.getPersistentDataContainer().set(plugin.getCustomKey(), PersistentDataType.STRING, "Custom");
    }
    
    @EventHandler (priority = EventPriority.LOWEST)
    //Modif pour les pictos
    public void onMobDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof Zombie)) return;
        String name = entity.getCustomName();
        if (name == null) return;

        switch (name) {
            case "§3Lancier" -> {
                event.getDrops().clear();
            }
            case "§fClair" -> {
                event.getDrops().clear();
            }
            case "§8Obscur" -> {
                event.getDrops().clear();
            }
        }
    }
}
