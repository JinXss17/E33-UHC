package fr.jinxss.e33.RolesSystem.Nevrons;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import fr.jinxss.e33.RolesSystem.Roles;

public class Noco extends Roles {
	
    public Noco(UUID uuid, String name) {
        super(uuid, name);
        this.speed = 0.145;
    }

	@Override
	public void onAssign() {
        Player player = getPlayer();
        if (player == null) return;

        player.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(this.speed);

        player.sendMessage("§bTu es Clair. Tu ressens une force protectrice autour de toi.");
    }

	@Override
	public void onPowerUse() {
		// TODO Auto-generated method stub

	}

}
