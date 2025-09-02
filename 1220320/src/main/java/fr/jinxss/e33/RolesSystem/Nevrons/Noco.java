package fr.jinxss.e33.RolesSystem.Nevrons;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import fr.jinxss.e33.RolesSystem.Nevron;
import fr.jinxss.e33.RolesSystem.RoleManager;
import fr.jinxss.e33.RolesSystem.roles.Monoco;

public class Noco extends Nevron {
	
    public Noco(UUID uuid, String name) {
        super(uuid, name);
        this.speed = 0.145;
    }

	@Override
	public void onAssign() {
        Player player = getPlayer();
        if (player == null) return;

        player.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(this.speed);

        player.sendMessage("§bTu es Noco.");
    }

	@Override
	public void onPowerUse() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void getPowerToMonoco(Player p) {
	}
	
	public void getPowerToMonoco(Player p, Player target) {
		if(RoleManager.getRole(p.getUniqueId()) instanceof Monoco role) {
			role.getNocoPower(target);
		}
	}

}
