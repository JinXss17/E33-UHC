package fr.jinxss.e33.RolesSystem.Nevrons;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import fr.jinxss.e33.RolesSystem.Nevron;
import fr.jinxss.e33.RolesSystem.RoleManager;
import fr.jinxss.e33.RolesSystem.roles.Monoco;

public class Petank extends Nevron {

    public Petank(UUID uuid, String name) {
        super(uuid, name);
        this.speed += 10;
        this.resi -= 10;
    }

    @Override
    public void onAssign() {
    	Player p = Bukkit.getPlayer(uuid);
		double Speed = p.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue();
		p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(Speed * getSpeed());
    	
    }

    @Override
    public void onPowerUse() {
        // Aucun pouvoir actif
    }
    
    @Override
	public void getPowerToMonoco(Player p) {
		if(RoleManager.getRole(p.getUniqueId()) instanceof Monoco role) {
			role.getPetankPower();
		}
	}
}