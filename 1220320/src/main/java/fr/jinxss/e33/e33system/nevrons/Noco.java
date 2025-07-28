package fr.jinxss.e33.e33system.nevrons;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.jinxss.e33.Roles;

public class Noco extends Roles {
	
    public Noco(UUID uuid, String name) {
        super(uuid, name);
        this.speed =21;
    }

	@Override
	public void onAssign() {
        Player player = getPlayer();
        if (player == null) return;

        // Ajoute SPEED niveau 2 (durée max)
        player.addPotionEffect(new PotionEffect(
            PotionEffectType.SPEED,
            Integer.MAX_VALUE, // Durée infinie tant que le joueur garde le rôle
            1, // Niveau 0 = SPEED II
            false, // Pas d’icône dans l’interface
            false  // Pas de particules
        ));

        player.sendMessage("§bTu es Clair. Tu ressens une force protectrice autour de toi.");
    }

	@Override
	public void onPowerUse() {
		// TODO Auto-generated method stub

	}

}
