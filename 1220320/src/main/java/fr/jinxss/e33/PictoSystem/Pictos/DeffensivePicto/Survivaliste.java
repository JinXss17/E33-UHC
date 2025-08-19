package fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class Survivaliste extends Picto {

	public int BasicCout = 10;
	
	private float ResistanceBoostLvl1 = 5;
	private float ResistanceBoostLvl2 = 5;
	private float ResistanceBoostLvl3 = 2;
	
	public Survivaliste() {
		PictoItem = new ItemStack(Material.LEATHER_HELMET);
		PictoName = "Survivaliste";
		basicCout = BasicCout;
		Cout = basicCout;
		ResistanceBoost += ResistanceBoostLvl1;
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		if(Level== ENiveau.Maitrise)ResistanceBoost += ResistanceBoostLvl2;
		if(Level== ENiveau.Instinctif)ResistanceBoost += ResistanceBoostLvl3;
	}
	
	@Override
	public String Describe() {
		return "Réduit de " + ResistanceBoost + "% tout type de dégât";
	}
}
