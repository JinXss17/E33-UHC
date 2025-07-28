package fr.jinxss.e33.PictoSystem.Pictos;

import java.util.List;

import org.bukkit.Material;

import fr.jinxss.e33.PictoSystem.Picto;

public class MineurChanceux extends Picto {
	
	public static List<Material> MineraisBoost = List.of(Material.DIAMOND_ORE, Material.GOLD_ORE, Material.IRON_ORE, Material.LAPIS_ORE);
			
	
	private float DropRate = 10; // en %
	private int DropMultiplicate = 2;


	public float getDropRate() {
		return DropRate;
	}


	public int getDropMultiplicate() {
		return DropMultiplicate;
	}
	

}
