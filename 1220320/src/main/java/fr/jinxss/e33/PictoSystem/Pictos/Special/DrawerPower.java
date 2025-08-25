package fr.jinxss.e33.PictoSystem.Pictos.Special;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class DrawerPower extends Picto {
	
	private int BasicCout = 30;
	
	public DrawerPower() {
		super();
		basicCout = BasicCout;
		Cout = basicCout;
		
		PictoItem = new ItemStack(Material.PAINTING);
		PictoName = "Puissance Surpeinte";
		
	}
	
	@Override
	public List<String> Describe() {
		
		List<String> describe = List.of( "Permet d'outrepassé la limite de dégats");
		return describe;
	}

}
