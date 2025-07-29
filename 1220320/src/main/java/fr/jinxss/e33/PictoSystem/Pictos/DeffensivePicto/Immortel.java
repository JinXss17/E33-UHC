package fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class Immortel extends Picto {

	
	private UUID uuid;
	
	private int BasicCout = 12;
	
	private double HealthBoostLvl1 = 4;
	private double HealthBoostLvl2 = 4;
	private double HealthBoostLvl3 = 2;
	
	private double HealthBoost = 0;
	
	public Immortel(UUID uuid) {
		PictoItem = new ItemStack(Material.TOTEM_OF_UNDYING);
		PictoItemName = "Immortel";
		
		basicCout = BasicCout;
		Cout = basicCout;
		
		HealthBoost += HealthBoostLvl1;
		this.uuid = uuid;
		
	}
	
	public Player getLinkedPlayer() {
		
		return Bukkit.getPlayer(uuid);
		
	}
	
	public void Activate(Player p) {
		
		double Health = p.getAttribute(Attribute.MAX_HEALTH).getValue();
		p.getAttribute(Attribute.MAX_HEALTH).setBaseValue(Health + HealthBoost);
		
	}
	
	public void Desactivate(Player p ) {
		double Health = p.getAttribute(Attribute.MAX_HEALTH).getValue();
		p.getAttribute(Attribute.MAX_HEALTH).setBaseValue(Health - HealthBoost);
	}
	
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		Player p = Bukkit.getPlayer(uuid);
		double Health = p.getAttribute(Attribute.MAX_HEALTH).getValue();
		Health -= HealthBoost;
		if(Level == ENiveau.Maitrise)HealthBoost += HealthBoostLvl2;
		if(Level == ENiveau.Instinctif)HealthBoost += HealthBoostLvl3;
		Health += HealthBoost;
		p.getAttribute(Attribute.MAX_HEALTH).setBaseValue(Health);
		
	}
	
}
