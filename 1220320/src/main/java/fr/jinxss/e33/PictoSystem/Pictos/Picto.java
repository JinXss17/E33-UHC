package fr.jinxss.e33.PictoSystem.Pictos;

import org.bukkit.entity.Player;

import fr.jinxss.e33.PictoSystem.ENiveau;

public class Picto{
	
	protected int basicCout = 0;
	public int Cout = 0;
	
	private float Coutlvl2Divisor = 1.5f;
	private float Coutlvl3Divisor = 2;
	
	public float DamageBoost = 0;
	public float ResistanceBoost = 0;

	protected ENiveau Level;
	
	public Picto() {
		Cout = basicCout;
	}
	
	public ENiveau GetLevel() {
		
		return Level;
		
	}
	
	public boolean IsToggleDamageBoost(Player p) {
		return false;
	}
	
	public void LevelUp() {
		if(Level == ENiveau.Instinctif)return;
		if(Level == ENiveau.Apprentissage) {
			Level = ENiveau.Maitrise;
			Cout = (int) (basicCout/Coutlvl2Divisor);
		}
		if(Level == ENiveau.Maitrise) {
			Level = ENiveau.Instinctif;
			Cout = (int) (basicCout/Coutlvl3Divisor);
		}
	}
}
