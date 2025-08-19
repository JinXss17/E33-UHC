package fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import fr.jinxss.e33.PictoSystem.PlayerPictos;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class CompressionDeLumina extends Picto {

	
	private int BasicCout = 12;

	private float BonusDamageLvl = 0.5f;
	
	private float BonusDamage;
	private PlayerPictos pictos;
	
	public CompressionDeLumina(PlayerPictos pictos) {
		PictoItem = new ItemStack(Material.ARROW);
		PictoName = "Compression de Lumina";
		basicCout = BasicCout;
		Cout = basicCout;
		
		this.pictos = pictos;
		
		BonusDamage = BonusDamageLvl;
	}
	
	public float getBonusDamage() {
		
		return 1 + (BonusDamage* (pictos.getLumina() - pictos.getLuminaUsed())/100) ;
		
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		
		BonusDamage += BonusDamageLvl;
		
	}
	
	@Override
	public String Describe() {
		return "Augmente de " + BonusDamage + "% les dégats par \n"
				+ "point de Lumina restant";
	}
	
	
}
