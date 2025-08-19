package fr.jinxss.e33.PictoSystem.Pictos.PictoMixtes;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class AgilliteFeline extends Picto {
	
	private int BasicCout = 10;
	
	private float FallDamageReduceLvl1 = 25;
	private float FallDamageReduceLvl2 = 15;
	private float FallDamageReduceLvl3 = 10;
	
	private float FallDamageReduce;
	
	public AgilliteFeline() {
		PictoItem = new ItemStack(Material.RABBIT_FOOT);
		PictoName = "Agilité Féline";
		
		basicCout = BasicCout;
		Cout = basicCout;
		FallDamageReduce = FallDamageReduceLvl1;
	}

	public float getFallDamageReduce() {
		return 1 - FallDamageReduce/100f;
	}
	
	@Override
	public List<String> Describe() {
		
		List<String> describe = List.of("Réduit les dégâts de chute de " + FallDamageReduce + "%");
		return describe;
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		
		if(Level == ENiveau.Maitrise)FallDamageReduce += FallDamageReduceLvl2;
		if(Level == ENiveau.Instinctif)FallDamageReduce += FallDamageReduceLvl3;
		
	}
	
}
