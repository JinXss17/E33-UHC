package fr.jinxss.e33.PictoSystem.Pictos.Special;

import java.util.List;

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
		
		PictoItem = new ItemStack(Material.FLINT_AND_STEEL);
		PictoName = "Incendie";
		
	}
	
	public int getFireRay() {
		return FireRay;
	}
	
	@Override
	public List<String> Describe() {
		
		List<String> describe = List.of("Enflamme tout les joueurs dans un",
				"rayon de " + FireRay + " a chaque coup");
		return describe;
	}
	
	public int getFireTick() {
		
		return FireSec * 20;
		
	}

}
