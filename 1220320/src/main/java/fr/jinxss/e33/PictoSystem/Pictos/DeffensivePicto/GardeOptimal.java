package fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class GardeOptimal extends Picto {

	private int BasicCout = 10;
	
	private double HealthBonusLvl1 = 0.5;
	private double HealthBonusLvl2 = 0.5;
	private double HealthBonusLvl3 = 1;
	private double HealthBonus = 0;
	
	public GardeOptimal() {
		PictoName = "Garde Optimal";
		PictoItem = new ItemStack(Material.SHIELD);
		
		basicCout = BasicCout;
		Cout = basicCout;
		HealthBonus += HealthBonusLvl1;
		
	}
	
	public double getHealBonus() {
		return HealthBonus * 2;
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		
		if(Level == ENiveau.Maitrise)HealthBonus += HealthBonusLvl2;
		if(Level == ENiveau.Instinctif)HealthBonus += HealthBonusLvl3;
		
	}
	
	@Override
	public String Describe() {
		return "Chaque fois que vous parez un coup\n"
				+ "vous régénérez " + HealthBonus + " Coeur(s) ";
	}
	
}
