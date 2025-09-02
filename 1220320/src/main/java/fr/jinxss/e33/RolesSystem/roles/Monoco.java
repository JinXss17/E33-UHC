package fr.jinxss.e33.RolesSystem.roles;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.RolesSystem.Roles;

public class Monoco extends Roles {

	private ArrayList<Player> NocoKiller = new ArrayList<Player>();
	
	private float NocoKillerDamageBonus = 1.30f;
	
	public Monoco(UUID uuid, String name) {
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
	
	public void getClairPower() {
		resi -= 10;
	}
	
	public void getObscurPower() {
		force += 10;
	}
	
	public void getPetankPower() {
		speed += 10;
		Player p = Bukkit.getPlayer(uuid);
		double Speed = p.getAttribute(Attribute.MOVEMENT_SPEED).getBaseValue();
		p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(Speed *  (1 +getSpeed()));
	}
	
	public void getNocoPower(Player p) {
		
		if(p == Bukkit.getPlayer(uuid)) {
			force -= 20;
			return;
		}
		
		if(!NocoKiller.contains(p)) {
			NocoKiller.add(p);
		}
	}
	
	public void getSakapattatePower() {
		Player p = Bukkit.getPlayer(uuid);
		double Health = p.getAttribute(Attribute.MAX_HEALTH).getBaseValue();
		if(Health + 6 > E33UHC.getMaxHealthToPlayer()) {
			Health = E33UHC.getMaxHealthToPlayer();
		}else Health += 6;
		p.getAttribute(Attribute.MAX_HEALTH).setBaseValue(Health);
	}
	
	public float getNocoKillerBonus() {
		return NocoKillerDamageBonus;
	}

}
