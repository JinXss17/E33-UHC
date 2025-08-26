package fr.jinxss.e33.TeamSystem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.TeamSystem.List.TeamList;

public class TeamSystem {

	private ArrayList<Team> _TeamList = new ArrayList<Team>();
	
	private Inventory teamInv;
	
	private E33UHC plugin;
	
	public TeamSystem(E33UHC plugin) {
		
		this.plugin = plugin;
		
		this.plugin.getCommand("Team").setExecutor(new TeamCommand(this));
		this.plugin.getServer().getPluginManager().registerEvents(new TeamListeners(this), plugin);
		
		teamInv = Bukkit.createInventory(null, 18, "Team");
		
	}
	
	public void registerNewTeam(TeamList TeamL, String TeamName) {
		
		Team team = new Team(TeamL, TeamName);
		Bukkit.broadcastMessage("Team " + team.getTeamName() + " Created");
		_TeamList.add(team);
		
	}
	
	public void OpenTeamInventory(Player p) {
		
		initTeamInventory();
		
		p.openInventory(getInventory());
		
	}
	
	public Inventory getInventory() {
		
		return teamInv;
		
	}
	
	private void initTeamInventory() {
		
		teamInv.clear();
		
		for(Team team : _TeamList) {
			teamInv.addItem(team.getItem());
		}
		
	}
	
	public void setPlayerCustomName(Player p) {
		
		for(Team team : _TeamList) {
			if(team.contains(p)) {
				
				p.setCustomName("[" + team.getTeamName() + "§r]"+ p.getName());
				p.setDisplayName("[" + team.getTeamName() + "§r]"+ p.getName());
				p.setPlayerListName("[" + team.getTeamName() + "§r]"+ p.getName());
				p.setCustomNameVisible(true);
				return;
			}
		}
		
		p.setCustomName(p.getName());
		p.setDisplayName(p.getName());
		p.setPlayerListName(p.getName());
		p.setCustomNameVisible(false);
		
	}
	
	public void inversePlayerToTeam(Player p, Team team) {
		
		if(team.contains(p)) {
			removePlayerToTeam(team, p);
		}else {
			addPlayerToTeam(team, p);
		}
		
	}
	
	public void removePlayerToTeam(Team pTeam, Player p) {
		if(pTeam.contains(p)) {
			pTeam.removePlayer(p);
		}
		setPlayerCustomName(p);
	}
	
	public void addPlayerToTeam(Team pTeam, Player p) {
		
		for(Team team : _TeamList) {
			if(team.contains(p)) {
				team.removePlayer(p);
			}
		}
		pTeam.addPlayer(p);
		setPlayerCustomName(p);
	}
	
	public List<Team> getTeams(){
		return _TeamList;
	}
	
	public Team getPlayerTeam(Player p ) {
		for(Team team : _TeamList) {
			if(team.contains(p)) {
				return team;
			}else continue;
		}
		return null;
	}
	
	public E33UHC getMain() {
		return plugin;
	}
	
}
