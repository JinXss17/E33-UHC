package fr.jinxss.e33.PictoSystem;

public class Picto{

	public float DamageBoost = 0;
	public float ResistanceBoost = 0;

	private ENiveau Level;
	
	public ENiveau GetLevel() {
		
		return Level;
		
	}
	
	public void LevelUp() {
		
		if(Level == ENiveau.Instinctif)return;
		if(Level == ENiveau.Apprentissage)Level = ENiveau.Maitrise;
		if(Level == ENiveau.Maitrise)Level = ENiveau.Instinctif;
	}
	
}
