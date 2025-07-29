package fr.jinxss.e33.PictoSystem;

import java.util.ArrayList;

import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class PlayerPictos {

	@SuppressWarnings("unused")
	private static int MaxHealthPlayer = 50;
	
	private int Lumina = 0;
	private ArrayList<Picto> Picto = new ArrayList<Picto>();
	private ArrayList<Picto> ActivatedPicto = new ArrayList<Picto>();
	
	public ArrayList<Picto> getPictoList() {
		return Picto;
	}
	
	public ArrayList<Picto> getActivatedPicto(){
		return ActivatedPicto;
	}
	
	public boolean HasPictoActivated(Class<Picto> Class) {
		
		for(Picto picto : getActivatedPicto()) {
			if(picto.getClass() == Class) {
				return true;
			}
		}
		return false;
	}
	
	public Picto GetPictoActivated(Class<Picto> Class) {
		
		for(Picto picto : getActivatedPicto()) {
			
			if(picto.getClass() == Class) {
				return picto;
			}
		}
		return null;
	}
	
	public int getLumina() {
		return Lumina;
	}
	
	public float getTotalDamageBoost() {
		
		float lTotalDamageBoost = 0;
		for(Picto picto : getActivatedPicto()) {
			lTotalDamageBoost += picto.DamageBoost;
		}
		return lTotalDamageBoost;
	}
	public float getTotalResistanceBoost() {
		
		float lTotalResistanceBoost = 0;
		for(Picto picto : getActivatedPicto()) {
			lTotalResistanceBoost += picto.ResistanceBoost;
		}
		return lTotalResistanceBoost;
	}
	
	
}
