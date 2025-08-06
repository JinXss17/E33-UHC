package fr.jinxss.e33.e33system.nevrons;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.jinxss.e33.e33system.Roles;

public class Obscur extends Roles {

    public Obscur(UUID uuid, String name) {
        super(uuid, name);
        this.force = 1;
    }

    @Override
    public void onAssign() {
        Player player = getPlayer();
        if (player == null) return;

        // Ajoute Force niveau 1 (durée max)
        player.addPotionEffect(new PotionEffect(
            PotionEffectType.STRENGTH,
            Integer.MAX_VALUE, // Durée infinie tant que le joueur garde le rôle
            0, // Niveau 0 = Force I
            false, // Pas d’icône dans l’interface
            false  // Pas de particules
        ));

        player.sendMessage("§bTu es Clair. Tu ressens une force destructrice autour de toi.");
    }

    @Override
    public void onPowerUse() {
        // Aucun pouvoir actif
    }
}