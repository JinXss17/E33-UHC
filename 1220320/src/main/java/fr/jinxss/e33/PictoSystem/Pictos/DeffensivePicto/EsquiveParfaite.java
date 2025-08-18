package fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class EsquiveParfaite extends Picto {
	
	private int BasicCout = 10;
	
	private float EscapeRateLvl1 = 10;
	private float EscapeRateLvl2 = 10;
	private float EscapeRateLvl3 = 5;

	private float EscapeRate = 0;
	
	public EsquiveParfaite() {
		
		PictoItem = new ItemStack(Material.FEATHER);
		PictoName = "Esquive Parfaite";
		
		basicCout = BasicCout;
		Cout = basicCout;
		EscapeRate += EscapeRateLvl1;
		
	}
	
	public float getEscapeRate() {
		return EscapeRate;
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		if(Level == ENiveau.Maitrise)EscapeRate += EscapeRateLvl2;
		if(Level == ENiveau.Instinctif)EscapeRate += EscapeRateLvl3;
	}
	
}
