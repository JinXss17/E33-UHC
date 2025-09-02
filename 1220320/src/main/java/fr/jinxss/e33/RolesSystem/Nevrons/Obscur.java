package fr.jinxss.e33.RolesSystem.Nevrons;

import java.util.UUID;

import org.bukkit.entity.Player;

import fr.jinxss.e33.RolesSystem.Nevron;
import fr.jinxss.e33.RolesSystem.RoleManager;
import fr.jinxss.e33.RolesSystem.roles.Monoco;

public class Obscur extends Nevron {

    public Obscur(UUID uuid, String name) {
        super(uuid, name);
        this.force += 20;
    }

    @Override
    public void onAssign() {
        Player player = getPlayer();
        if (player == null) return;
        player.sendMessage("§bTu es Clair. Tu ressens une force destructrice autour de toi.");
    }

    @Override
    public void onPowerUse() {
        // Aucun pouvoir actif
    }
    
    @Override
	public void getPowerToMonoco(Player p) {
		if(RoleManager.getRole(p.getUniqueId()) instanceof Monoco role) {
			role.getObscurPower();
		}
	}
}