package fr.jinxss.e33.PictoSystem;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.jinxss.e33.PictoSystem.Pictos.Picto;
import fr.jinxss.e33.PictoSystem.Pictos.DeffensivePicto.Immortel;

public class PlayerPictos {

	@SuppressWarnings("unused")
	private static int MaxHealthPlayer = 50;
	
	private int Lumina = 0;
	private ArrayList<Picto> Picto = new ArrayList<Picto>();
	private ArrayList<Picto> ActivatedPicto = new ArrayList<Picto>();
	
	public ArrayList<Picto> getPictoList() {
		return Picto;
	}
	
	public void addToPictoList(Picto picto) {
		if(!Picto.contains(picto))Picto.add(picto);
	}
	
	public void removeToPictoList(Picto picto) {
		
		if(Picto.contains(picto)) Picto.remove(picto);
	}
	
	public void addToPictoActivated(Picto picto) {
		if(!ActivatedPicto.contains(picto))ActivatedPicto.add(picto);
		if(picto instanceof Immortel e) e.Activate(e.getLinkedPlayer());
	}
	
	public void removeToPictoActivated(Picto picto) {
		if(ActivatedPicto.contains(picto)) ActivatedPicto.remove(picto);
		if(picto instanceof Immortel e) e.Desactivate(e.getLinkedPlayer());
	}
	
	public ArrayList<Picto> getActivatedPicto(){
		return ActivatedPicto;
	}
	
	public boolean HasPictoActivated(Class<? extends Picto> Class) {
		
		for(Picto picto : getActivatedPicto()) {
			if(picto.getClass() == Class) {
				return true;
			}
		}
		return false;
	}
	
	public Inventory getPictoMenu() {
		
		Inventory PictoInv = Bukkit.createInventory(null, 18, "Pictos");
		
		for(Picto picto : getPictoList()) {
			
			ItemStack pictoItem = picto.getPictoItem();
			ItemMeta PictoMeta = pictoItem.getItemMeta();
			
			if(getActivatedPicto().contains(picto) ) {
				PictoMeta.setDisplayName("§a"+ PictoMeta.getDisplayName());
			}else {
				PictoMeta.setDisplayName("§c"+ PictoMeta.getDisplayName());
			}
			pictoItem.setItemMeta(PictoMeta);
			PictoInv.addItem(pictoItem);
		}
		return PictoInv;
	}
	
	public Picto GetPictoActivated(Class<? extends Picto> Class) {
		
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
	
	public float getTotalDamageBoost(Player p) {
		
		float lTotalDamageBoost = 0;
		for(Picto picto : getActivatedPicto()) {
			if(picto.IsToggleDamageBoost(p))lTotalDamageBoost += picto.DamageBoost;
		}
		return 1 + (lTotalDamageBoost/100);
	}
	public float getTotalResistanceBoost() {
		
		float lTotalResistanceBoost = 0;
		for(Picto picto : getActivatedPicto()) {
			lTotalResistanceBoost += picto.ResistanceBoost;
		}
		return 1 - (lTotalResistanceBoost/100);
	}
	
	
}
