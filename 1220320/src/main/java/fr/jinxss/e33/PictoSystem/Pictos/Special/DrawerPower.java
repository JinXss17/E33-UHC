package fr.jinxss.e33.PictoSystem.Pictos.Special;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class DrawerPower extends Picto {
	
	public DrawerPower() {
		
		PictoItem = new ItemStack(Material.PAINTING);
		PictoName = "Puissance Surpeinte";
		
	}
	
	@Override
	public String Describe() {
		return "Permet d'outrepassé la limite de dégats";
	}

}
