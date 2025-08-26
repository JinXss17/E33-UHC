package fr.jinxss.e33.TeamSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.jinxss.e33.TeamSystem.List.TeamList;

public class Team {
	
	private final TeamList team;
	private String TeamName;
	
	private ArrayList<UUID> PlayerInTeam = new ArrayList<UUID>();
	
	public Team(TeamList Team, String TeamName) {
		this.team = Team;
		this.TeamName = TeamName;
	}
	
	public String getTeamName() {
		return team.getChatColor() + TeamName;
	}
	
	public boolean contains(Player p) {
		
		if(PlayerInTeam.contains(p.getUniqueId()))return true;
		else return false;
		
	}
	
	public ItemStack getItem() {
		
		ItemStack it = team.getBannerColor();
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(team.getChatColor() + TeamName);
		List<String> lore = meta.getLore();
		if(lore == null) {
			lore = new ArrayList<>();
		}
		for(UUID uuid : PlayerInTeam) {
			Player p = Bukkit.getPlayer(uuid);
			lore.add(team.getChatColor() + p.getName());
		}
		meta.setLore(lore);
		
		it.setItemMeta(meta);
		
		return it;
		
	}
	
	//Ajoute/retire le joueur de l'équipe selon si il s'y trouve ou non
	public void InversePlayerInList(Player p) {
		if(!PlayerInTeam.contains(p.getUniqueId()))PlayerInTeam.add(p.getUniqueId());
		else PlayerInTeam.remove(p.getUniqueId());
	}
	
	public void addPlayer(Player p) {
		if(!PlayerInTeam.contains(p.getUniqueId()))PlayerInTeam.add(p.getUniqueId());
	}
	public void removePlayer(Player p) {
		if(PlayerInTeam.contains(p.getUniqueId()))PlayerInTeam.remove(p.getUniqueId());
	}

}
