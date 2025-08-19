package fr.jinxss.e33.PictoSystem.Pictos;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.jinxss.e33.PictoSystem.ENiveau;

public class Picto{
	
	public int Exp = 0;
	public int ExpToLevelUp = 750;
	public int ExpPerMobs = 20;
	public float ExpUpMultiplier = 1;
	
	protected ItemStack PictoItem;
	protected String PictoName = "Picto";
	
	protected int basicCout = 0;
	public int Cout = 0;
	
	private float Coutlvl2Divisor = 1.5f;
	private float Coutlvl3Divisor = 2;
	
	public float DamageBoost = 0;
	public float ResistanceBoost = 0;

	protected ENiveau Level = ENiveau.Apprentissage;
	
	public Picto() {
		Cout = basicCout;
	}
	
	public String Describe() {
		return "Ne fais Rien";
	}
	
	public ItemStack getPictoItem() {
		
		ItemStack it = PictoItem.clone();
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(PictoName);
		it.setItemMeta(meta);
		
		return it;
	}
	
	public ENiveau GetLevel() {
		
		return Level;
		
	}
	
	public String getPictoName() {
		
		return PictoName;
		
	}
	
	public boolean IsToggleDamageBoost(Player p) {
		return false;
	}
	
	public void AddExp() {
		
		if(Level == ENiveau.Instinctif)return;
		
		Exp += ExpPerMobs;
		if(Exp >= ExpToLevelUp) {
			
			Exp -= ExpToLevelUp ;
			LevelUp();
		}
	}
	
	public void LevelUp() {
		if(Level == ENiveau.Instinctif)return;
		if(Level == ENiveau.Maitrise) {
			Level = ENiveau.Instinctif;
			Cout = (int) (basicCout/Coutlvl3Divisor);
		}
		if(Level == ENiveau.Apprentissage) {
			Level = ENiveau.Maitrise;
			Cout = (int) (basicCout/Coutlvl2Divisor);
		}
		ExpUpMultiplier += 1f;
		ExpToLevelUp *= ExpUpMultiplier;
	}
}
