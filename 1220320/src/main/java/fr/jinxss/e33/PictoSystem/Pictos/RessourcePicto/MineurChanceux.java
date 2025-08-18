package fr.jinxss.e33.PictoSystem.Pictos.RessourcePicto;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class MineurChanceux extends Picto {
	
	public static List<Material> MineraisBoost = List.of(Material.DIAMOND_ORE, Material.GOLD_ORE, Material.IRON_ORE, Material.LAPIS_ORE);
			
	private int BasicCout = 16;
	
	private float DropRate = 10; // en %
	private float BonusRateMaitrise = 10;
	private float BonusRateInstinct = 5;
	private int DropMultiplicate = 2;
	private int DropBonusInstinct = 1;

	public MineurChanceux() {
		PictoItem = new ItemStack(Material.DIAMOND_PICKAXE);
		PictoName = "Mineur Chanceux";
		basicCout = BasicCout;
		Cout = BasicCout;
	}
	
	public float getDropRate() {
		return DropRate;
	}


	public int getDropMultiplicate() {
		return DropMultiplicate;
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		
		if(Level == ENiveau.Maitrise )DropRate += BonusRateMaitrise;
		else {
			DropRate += BonusRateInstinct;
			DropMultiplicate += DropBonusInstinct;
		}
		
	}
	

}
