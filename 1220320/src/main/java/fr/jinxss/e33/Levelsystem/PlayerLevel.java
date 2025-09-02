package fr.jinxss.e33.Levelsystem;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.PictoSystem.PlayerPictos;
import fr.jinxss.e33.RolesSystem.RoleManager;
import fr.jinxss.e33.RolesSystem.roles.Peintresse;

public class PlayerLevel {

	private int Level = 1;
	private int Exp = 0;
	private int baseExpToLevelUp = 50;
	private int ExpToLevelUp;
	private float ExpToLevelUpMultiplier = 1.2f;
	
	private int Lumina = 2;
	private int LuminaOnLevelUp = 2;
	
	private double HealthBonus = 2;
	
	private PlayerPictos playerPicto;
	private UUID playerUUID;
	
	public PlayerLevel(PlayerPictos playerPicto, @NonNull Player p) {
		ExpToLevelUp = baseExpToLevelUp;
		this.playerPicto = playerPicto;
		playerUUID = p.getUniqueId();
	}
	
	public void setPlayerPicto(PlayerPictos playerPicto) {
		this.playerPicto = playerPicto;
	}
	
	public int getLumina() {
		return Lumina;
	}
	
	public int getUsedLumina() {
		
		return playerPicto.getLuminaUsed();
		
	}
	
	public int getExp() {
		return Exp;
	}
	
	public int getExpToLevelUp() {
		if(Level == 25) return 0;
		return ExpToLevelUp;
	}
	
	public int getLevel() {
		return Level;
	}
	
	public void addExp(float xp) {
		if(Level == 25 && !(RoleManager.getRole(playerUUID) instanceof Peintresse)) {
			return;
		}
		if(Level == 33)return;
		Exp += xp;
		while(Exp >= ExpToLevelUp){
			LevelUp();
		}
	}
	
	private void LevelUp() {
		
		Level++;
		Lumina += LuminaOnLevelUp;
		Exp -= ExpToLevelUp;
		ExpToLevelUp *= ExpToLevelUpMultiplier;
		
		if(Level % 5 == 0) {
			AttributeInstance HealthAttribut = Bukkit.getPlayer(playerUUID).getAttribute(Attribute.MAX_HEALTH);
			double Health = HealthAttribut.getBaseValue();
			if(Health + HealthBonus > E33UHC.getMaxHealthToPlayer()) {
				Health = E33UHC.getMaxHealthToPlayer();
			}else Health += HealthBonus;
			Bukkit.getPlayer(playerUUID).getAttribute(Attribute.MAX_HEALTH).setBaseValue(Health);
		}
		
		if(Level == 33 && (RoleManager.getRole(playerUUID) instanceof Peintresse role)) {
			role.RemoveHealth();
			return;
		}
		
	}
	
}
