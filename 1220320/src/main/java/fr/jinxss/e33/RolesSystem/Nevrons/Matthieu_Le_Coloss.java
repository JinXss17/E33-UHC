package fr.jinxss.e33.RolesSystem.Nevrons;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.jinxss.e33.RolesSystem.Roles;

public class Matthieu_Le_Coloss extends Roles {
	
    public Matthieu_Le_Coloss(UUID uuid, String name) {
        super(uuid, name);
        this.force = 1;
        this.resi = 1;
    }

	@Override
	public void onAssign() {
        Player player = getPlayer();
        if (player == null) return;

        // Ajoute SPEED niveau 2 (durée max)
        player.addPotionEffect(new PotionEffect(
            PotionEffectType.RESISTANCE,
            Integer.MAX_VALUE, // Durée infinie tant que le joueur garde le rôle
            0, // Niveau 0 = SPEED II
            false, // Pas d’icône dans l’interface
            false  // Pas de particules
        ));
        player.addPotionEffect(new PotionEffect(
                PotionEffectType.STRENGTH,
                Integer.MAX_VALUE, // Durée infinie tant que le joueur garde le rôle
                0, // Niveau 0 = SPEED II
                false, // Pas d’icône dans l’interface
                false  // Pas de particules
            ));
        
        player.sendMessage("§bTu es Matthieu_Le_Coloss. Tu ressens une force protectrice autour de toi.");
    }

	@Override
	public void onPowerUse() {
		// TODO Auto-generated method stub

	}

}
