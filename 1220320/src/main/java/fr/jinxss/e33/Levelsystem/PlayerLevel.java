package fr.jinxss.e33.Levelsystem;

public class PlayerLevel {

	private int Level = 1;
	private int Exp = 0;
	private int baseExpToLevelUp = 100;
	private int ExpToLevelUp;
	private float ExpToLevelUpMultiplier = 1.2f;
	
	private int Lumina = 2;
	private int LuminaOnLevelUp = 2;
	private int LuminaRest;
	
	public PlayerLevel() {
		ExpToLevelUp = baseExpToLevelUp;
		LuminaRest = Lumina;
	}
	
	public int getLumina() {
		return Lumina;
	}
	
	public int getRestLumina() {
		return LuminaRest;
	}
	
	public int getExp() {
		return Exp;
	}
	
	public int getExpToLevelUp() {
		return ExpToLevelUp;
	}
	
	public int getLevel() {
		return Level;
	}
	
	public void addExp(float xp) {
		
		Exp += xp;
		if(Exp >= ExpToLevelUp) {
			LevelUp();
		}
	}
	
	private void LevelUp() {
		Level++;
		Lumina += LuminaOnLevelUp;
		Exp -= ExpToLevelUp;
		ExpToLevelUp *= ExpToLevelUpMultiplier;
	}
	
}
