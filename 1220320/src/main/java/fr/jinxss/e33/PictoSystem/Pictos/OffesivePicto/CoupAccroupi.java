package fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.PictoSystem;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class CoupAccroupi extends Picto {
	private int BasicCout = 12;
	
	private float DamageBonus;
	private float DamageBoostLvl2 = 5;
	private float DamageBoostLvl3 = 2;
	
	

	public CoupAccroupi() {
		PictoItem = new ItemStack(Material.STICK);
		PictoName = "Coup Accroupi";
		basicCout = BasicCout;
		Cout= basicCout;
		
		DamageBonus = 0;
		
	}
	
	public double SneakHit(Entity e , double Damage) {
		double finalDamage = Damage * ( 1 + (DamageBonus / 100 ) );
		if(e instanceof Player p) {
			PictoSystem.getPlayerPictos(p).applyMark();
		}
		return finalDamage;
		
	}
	
	@Override
	public List<String> Describe() {
		
		List<String> describe = List.of("Lorsque vous frappez un joueur", "vous lui appliquez une Marque");
		
		if(Level != ENiveau.Apprentissage)describe.addAll(List.of("Et lui infligez " + DamageBonus + "% de dégats suplémentaire") );
		
		return describe;
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		
		if(Level == ENiveau.Maitrise)DamageBonus += DamageBoostLvl2;
		if(Level == ENiveau.Instinctif)DamageBonus += DamageBoostLvl3;
		
	}
}
