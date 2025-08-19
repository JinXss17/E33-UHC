package fr.jinxss.e33.PictoSystem.Pictos.Special;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.jinxss.e33.PictoSystem.PlayerPictos;
import fr.jinxss.e33.PictoSystem.Pictos.Picto;
import fr.jinxss.e33.uhcsystem.UHCBorder;

public class NouvellePeinture extends Picto {

	private int BasicCout = 25;
	
	public boolean isUsed = false;
	
	
	private UUID PlayerUUID;
	private PlayerPictos playerPictos;
	private UHCBorder border;
	
	public NouvellePeinture(Player p, PlayerPictos playerPictos, UHCBorder border) {
		super();
		basicCout = BasicCout;
		Cout = basicCout;
		
		PlayerUUID = p.getUniqueId();
		this.playerPictos = playerPictos;
		this.border = border;
		
		PictoItem = new ItemStack(Material.PAINTING);
		PictoName = "Nouvelle Peinture";
	}
	
	private boolean CanRevive() {
		
		return !isUsed;
		
	}
	
	@Override
	public String Describe() {
		return "Permet de ressucité (1 fois)";
	}
	
	public void Revive() {
		
		Random r = new Random();
		Player p = Bukkit.getPlayer(PlayerUUID);
		
		Location randomTP = new Location(Bukkit.getWorld("world"), r.nextFloat() * (border.getCurrentSize()/2), 150, r.nextFloat() * (border.getCurrentSize()/2) );
		
		if(CanRevive()) {
			isUsed = true;
			p.teleport(randomTP);
			p.setGameMode(GameMode.SURVIVAL);
			playerPictos.removeToPictoActivated(this);
		}
	}
}
