package fr.jinxss.e33.PictoSystem.Pictos.PictoMixtes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.ENiveau;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;

public class ContreParfait extends Picto {

	
	private int BasicCout = 20;
	
	private float DamageMultiplierLvl1 = 0.5f;
	private float DamageMultiplierLvl2 = 1f;
	private float DamageMultiplierLvl3 = 1.5f;
	
	private float DamageMultiplier;
	
	private boolean CoolDown = false;
	private int CoolDownTimer = 30; // en Secondes
	
	public ContreParfait() {
		PictoItem = new ItemStack(Material.SHIELD);
		PictoName = "Contre Parfait";
		
		basicCout = BasicCout;
		Cout = basicCout;
		
		DamageMultiplier = DamageMultiplierLvl1;
		
	}
	
	public void Counter(Player Damager, double Damage, EntityDamageByEntityEvent e) {
		
		if(!CoolDown) {
			e.setCancelled(true);
			Damager.damage(Damage);
			CoolDown = true;
			Bukkit.getScheduler().runTaskLater(null, () -> { CoolDown = false; }, CoolDownTimer * 20);
		}
	}
	
	public float GetDamageMultiplier() {
		
		return DamageMultiplier;
		
	}
	
	@Override
	public void LevelUp() {
		super.LevelUp();
		
		if(Level == ENiveau.Maitrise)DamageMultiplier = DamageMultiplierLvl2;
		if(Level == ENiveau.Instinctif)DamageMultiplier = DamageMultiplierLvl3;
		
	}
}
