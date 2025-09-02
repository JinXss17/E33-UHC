package fr.jinxss.e33.RolesSystem;

import java.util.UUID;

import org.bukkit.entity.Player;

public abstract class Nevron extends Roles {

	public Nevron(UUID uuid, String name) {
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
	
	public abstract void getPowerToMonoco(Player p);

}
