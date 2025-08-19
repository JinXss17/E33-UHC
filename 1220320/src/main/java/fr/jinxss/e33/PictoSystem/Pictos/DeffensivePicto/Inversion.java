package fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class Inversion extends Picto {

	private int BasicCout = 10;
	private double AbsoLvl2 = 2;
	
	private float ResistanceBoostLvl1 = 10;
	private float ResistanceBoostLvl2 = 5;
	private float ResistanceBoostLvl3 = 5;
	
	private double AbsoRemove = 0;
	
	public Inversion() {
		PictoItem = new ItemStack(Material.GOLDEN_APPLE);
		PictoName = "Inversion";
		
		basicCout = BasicCout;
		Cout = basicCout;
		ResistanceBoost += ResistanceBoostLvl1;
		
	}
	
	public double getRemovedAbso() {
		return AbsoRemove;
	}
	
	@Override
	public String Describe() {
		return "Enlève " + AbsoRemove + " coeurs d'absorption\n"
				+ "au moment de manger une pomme d'or\n"
				+ "mais reduit les dégats de " + ResistanceBoost + "%" ;
	}
	
	@Override
	public void LevelUp() {
		
		super.LevelUp();
		
		if(Level == ENiveau.Maitrise) {
			AbsoRemove += AbsoLvl2;
			ResistanceBoost += ResistanceBoostLvl2;
		}
		if(Level == ENiveau.Instinctif) {
			ResistanceBoost += ResistanceBoostLvl3;
		}
		
	}
	
}
