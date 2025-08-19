package fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class TirPrecis extends Picto {
	
	private int BasicCout = 12;
	private float ArrowDamageBonusLvl1 = 10f;
	private float ArrowDamageBonusLvl2 = 10f;
	private float ArrowDamageBonusLvl3 = 5f;

	public TirPrecis() {
		PictoItem = new ItemStack(Material.ARROW);
		PictoName = "Tir Précis";
		basicCout = BasicCout;
		Cout= basicCout;
		DamageBoost += ArrowDamageBonusLvl1;
	}
	
	@Override
	public String Describe() {
		return "Augmente les dégats de l'arc de " + DamageBoost + "%";
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		
		if(Level == ENiveau.Maitrise)DamageBoost += ArrowDamageBonusLvl2;
		if(Level == ENiveau.Instinctif)DamageBoost += ArrowDamageBonusLvl3;
		
	}
	
}
