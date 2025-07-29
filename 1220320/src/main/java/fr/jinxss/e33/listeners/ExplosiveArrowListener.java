package fr.jinxss.e33.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ExplosiveArrowListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if (!(projectile instanceof Arrow)) return;
        if (!"ExplosiveArrow".equals(projectile.getCustomName())) return;

        Location loc = projectile.getLocation();

        // Explosion sans endommager les blocs (false = pas de "grief")
        loc.getWorld().createExplosion(loc, 3.0f, false, false);

        // Supprimer la flèche si toujours présente
        projectile.remove();
    }
}