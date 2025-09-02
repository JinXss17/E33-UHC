package fr.jinxss.e33.RolesSystem.Nevrons;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.RolesSystem.Nevron;
import fr.jinxss.e33.RolesSystem.RoleManager;
import fr.jinxss.e33.RolesSystem.roles.Monoco;

public class Sakapattate extends Nevron {

	public Sakapattate(UUID uuid, String name) {
		super(uuid, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAssign() {
		Player p = Bukkit.getPlayer(uuid);
		double Health = p.getAttribute(Attribute.MAX_HEALTH).getBaseValue();
		if(Health + 10 > E33UHC.getMaxHealthToPlayer()) {
			Health = E33UHC.getMaxHealthToPlayer();
		}else Health += 10;
		p.getAttribute(Attribute.MAX_HEALTH).setBaseValue(Health);

	}

	@Override
	public void onPowerUse() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void getPowerToMonoco(Player p) {
		if(RoleManager.getRole(p.getUniqueId()) instanceof Monoco role) {
			role.getSakapattatePower();
		}
	}

}
