package fr.jinxss.e33.PictoSystem.Pictos.OffesivePicto;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class TirMarquant extends Picto {

	private int BasicCout = 12;
	private double ArrowHealthSteal = 2.0d;
	private int HealthStealTimeLvl1 = 0;
	private int HealthStealTimeLvl2 = 10; //Sec
	private int HealthStealTimeLvl3 = 10; //Sec
	
	private int HealthStealTime;

	public TirMarquant() {
		PictoItem = new ItemStack(Material.TARGET);
		PictoName = "Tir Marquant";
		basicCout = BasicCout;
		Cout= basicCout;
		
		HealthStealTime += HealthStealTimeLvl1;
	}
	
	public void StealHealth(Player cible, E33UHC plugin) {
		
		double BasicHealth = cible.getAttribute(Attribute.MAX_HEALTH).getValue();
		if(BasicHealth - ArrowHealthSteal < 1)return;
		cible.getAttribute(Attribute.MAX_HEALTH).setBaseValue(BasicHealth - ArrowHealthSteal);
		Bukkit.getScheduler().runTaskLater(plugin, () -> {
			cible.getAttribute(Attribute.MAX_HEALTH).setBaseValue(BasicHealth);
		}, 20 * HealthStealTime);
		
	}
	
	@Override
	public List<String> Describe() {
		
		List<String> describe = List.of("Lorsque vous touchez un joueur a l'arc",
				"vous lui appliquer une marque");
		
		if(Level != ENiveau.Apprentissage)describe.addAll(List.of("et lui",
				"retirez " + ArrowHealthSteal + " coeurs pendant " + HealthStealTime + "sec") );
		
		return describe;
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		
		if(Level == ENiveau.Maitrise)HealthStealTime += HealthStealTimeLvl2;
		if(Level == ENiveau.Instinctif)HealthStealTime += HealthStealTimeLvl3;
		
	}
	
}
