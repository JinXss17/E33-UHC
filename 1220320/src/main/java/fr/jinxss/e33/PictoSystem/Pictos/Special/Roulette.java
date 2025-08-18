package fr.jinxss.e33.PictoSystem.Pictos.Special;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class Roulette extends Picto {
	
	private float BonusDamage = 2f;
	private float BonusRate = 30;
	
	
	private float MalusDamage = 0.5f;
	private float MalusRate = 20;
	
	private int BasicCout = 12;
	
	public Roulette() {
		super();
		basicCout = BasicCout;
		Cout = basicCout;
		
		PictoName = "Roulette";
		PictoItem = new ItemStack(Material.MAP);
		
	}
	
	public double damageRoll(double damage) {
		Random r = new Random();
		double FinalDamage = damage;
		float purcentage = r.nextFloat() *100;
		
		if(purcentage <= MalusRate) {
			FinalDamage *= MalusDamage;
		}else if (purcentage <= BonusRate+MalusRate) {
			FinalDamage *= BonusDamage;
		}
		
		return FinalDamage;
	}
	

}
