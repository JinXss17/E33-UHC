package fr.jinxss.e33.PictoSystem.Pictos.Special;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class Incendie extends Picto {
	
	private int BasicCout = 20;
	
	private int FireRay = 10;
	private int FireSec = 10;
	
	public Incendie() {
		super();
		basicCout = BasicCout;
		Cout = basicCout;
		
		PictoItem = new ItemStack(Material.FIRE);
		PictoName = "Incendie";
		
	}
	
	public int getFireRay() {
		return (int) Math.pow(FireRay, 2);
	}
	
	public int getFireTick() {
		
		return FireSec * 20;
		
	}

}
