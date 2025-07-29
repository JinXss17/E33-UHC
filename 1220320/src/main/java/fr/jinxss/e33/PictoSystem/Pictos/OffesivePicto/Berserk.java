package fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Picto;

public class Berserk extends Picto {
	
	private int BasicCout = 12;
	private double HealthDivideToBoost = 4;
	private float DamageBonusLvl1 = 10;
	private float DamageBonusLvl2 = 10;
	private float HealthDivideToBoostLvl2= 2;
	private float DamageBonusLvl3 = 5;
	
	public Berserk() {
		DamageBoost = DamageBonusLvl1;
		basicCout = BasicCout;
		Cout = BasicCout;
	}
	
	public double getHealthToBoost(Player p) {
		AttributeInstance Health = p.getAttribute(Attribute.MAX_HEALTH);
		double HealthToBoost = Health.getValue()/HealthDivideToBoost;
		return HealthToBoost;
	}
	
	@Override
	public boolean IsToggleDamageBoost(Player p) {
		if(p.getHealth() <= getHealthToBoost(p))return true;
		else return false;
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		
		if(Level == ENiveau.Maitrise) {
			DamageBoost += DamageBonusLvl2;
			HealthDivideToBoost = HealthDivideToBoostLvl2;
		}
		if(Level == ENiveau.Instinctif)DamageBoost += DamageBonusLvl3;
	}

}
