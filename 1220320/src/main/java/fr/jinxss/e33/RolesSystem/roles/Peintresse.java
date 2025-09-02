package fr.jinxss.e33.RolesSystem.roles;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import fr.jinxss.e33.RolesSystem.Roles;

public class Peintresse extends Roles {

	public Peintresse(UUID uuid, String name) {
		super(uuid, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAssign() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPowerUse() {
		// TODO Auto-generated method stub

	}
	
	public void RemoveHealth() {
		
		for(Player p : Bukkit.getOnlinePlayers() ) {
		
			if(p.getGameMode() == GameMode.SURVIVAL) {
				
				double Health = p.getAttribute(Attribute.MAX_HEALTH).getValue();
				p.getAttribute(Attribute.MAX_HEALTH).setBaseValue(Health - 10);
				
			}
			
		}
		
	}
}
