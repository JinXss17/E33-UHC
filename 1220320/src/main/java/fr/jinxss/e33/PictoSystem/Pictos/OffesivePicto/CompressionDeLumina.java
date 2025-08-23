package fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.Levelsystem.PlayerLevel;
import fr.jinxss.e33.PictoSystem.PlayerPictos;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class CompressionDeLumina extends Picto {

	
	private int BasicCout = 12;

	private float BonusDamageLvl = 0.5f;
	
	private float BonusDamage;
	private PlayerLevel level;
	
	public CompressionDeLumina(PlayerLevel level) {
		PictoItem = new ItemStack(Material.ARROW);
		PictoName = "Compression de Lumina";
		basicCout = BasicCout;
		Cout = basicCout;
		
		this.level = level;
		
		BonusDamage = BonusDamageLvl;
	}
	
	public float getBonusDamage() {
		
		return 1 + (BonusDamage* (level.getLumina() - level.getUsedLumina())/100) ;
		
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		
		BonusDamage += BonusDamageLvl;
		
	}
	
	@Override
	public List<String> Describe() {
		
		List<String> describe = List.of("Augmente de " + BonusDamage + "% les dégats par",
				"point de Lumina restant");
		return describe;
	}
	
	
}
