package fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto;

import java.util.List;

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
	public List<String> Describe() {
		
		List<String> describe = List.of("Donne " + AbsoRemove + " coeurs d'absorption",
				"au moment de manger une pomme d'or",
				"mais reduit les dégats de " + ResistanceBoost + "%")  ;
		
		return describe;
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
