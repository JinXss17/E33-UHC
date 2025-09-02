package fr.jinxss.e33.RolesSystem.Nevrons;

import java.util.UUID;

import org.bukkit.entity.Player;

import fr.jinxss.e33.RolesSystem.Nevron;
import fr.jinxss.e33.RolesSystem.RoleManager;
import fr.jinxss.e33.RolesSystem.roles.Monoco;

public class Clair extends Nevron {
	
	public Clair(UUID uuid, String name) {
        super(uuid, name);
        this.resi += 20;
    }

    @Override
    public void onAssign() {
        Player player = getPlayer();
        if (player == null) return;

        player.sendMessage("§bTu es Clair. Tu ressens une force protectrice autour de toi.");
    }


    @Override
    public void onPowerUse() {
        // Aucun pouvoir actif
    }

	@Override
	public void getPowerToMonoco(Player p) {
		if(RoleManager.getRole(p.getUniqueId()) instanceof Monoco role) {
			role.getClairPower();
		}
	}
}