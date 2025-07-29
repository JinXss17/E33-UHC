package fr.jinxss.e33.PictoSystem.Pictos.RessourcePicto;

import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class MineurDor extends Picto {

	public static List<Material> MineraisBoost = List.of(Material.DIAMOND_ORE, Material.IRON_ORE);
	
	private int BasicCout = 10;
	
	private ItemStack ItemDropLvl1 =new ItemStack(Material.GOLD_NUGGET, 1);
	private ItemStack ItemDropLvl2 = new ItemStack(Material.GOLD_NUGGET, 1);
	private int MidDropLvl2 = 3;
	private int RayDropLvl2 = 2;
	private ItemStack ItemDropLvl3 = new ItemStack(Material.GOLD_INGOT);
	
	private float DropRate = 30; // en %
	private float BonusRateMaitrise = 20;

	public MineurDor() {
		basicCout = BasicCout;
		Cout = BasicCout;
	}
	
	public float getDropRate() {
		return DropRate;
	}
	
	public void DropBonus(Location dropPosition) {
		
		if(Level == ENiveau.Apprentissage) {
			dropPosition.getWorld().dropItem(dropPosition, ItemDropLvl1);
			return;
		}
		if(Level == ENiveau.Maitrise) {
			Random r = new Random();
			int DropAmount = (int)(MidDropLvl2 + (Math.sin(r.nextLong()) * RayDropLvl2) );
			ItemDropLvl2.setAmount(DropAmount);
			dropPosition.getWorld().dropItem(dropPosition, ItemDropLvl2);
			return;
		}
		if(Level == ENiveau.Instinctif) {
			dropPosition.getWorld().dropItem(dropPosition, ItemDropLvl3);
			return;
		}
		
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		
		if(Level == ENiveau.Maitrise )DropRate += BonusRateMaitrise;
		
	}
	
}
