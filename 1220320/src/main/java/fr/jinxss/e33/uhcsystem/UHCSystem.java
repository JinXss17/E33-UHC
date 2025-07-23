package fr.jinxss.e33.uhcsystem;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UHCSystem {
	
	private static ArrayList<Player> _PlayerList = new ArrayList<Player>();
	private static ArrayList<UUID> _DeadPlayerList = new ArrayList<UUID>();
	private static ArrayList<UUID> _AlivePlayerList = new ArrayList<UUID>();
	
	
	public void RegisterNewScoreBoard() {
		
	}
	
	public void RegisterNewTimer() {
		
	}
	
	public void RegisterNewGame() {
		
	}
	
	public void addPlayerToGame(Player pPlayer) {
		if(!_PlayerList.contains(pPlayer))_PlayerList.add(pPlayer);
	}
	public void RemovePlayerToGame(Player pPlayer) {
		
		if(_PlayerList.contains(pPlayer))_PlayerList.remove(pPlayer); 
		
	}
	public ArrayList<Player> getPlayers() {
		return _PlayerList;
	}
	public ArrayList<Player> getDeadPlayers() {
		
		ArrayList<Player> lDeadPlayerList = new ArrayList<Player>();
		
		for(UUID uuid  : _DeadPlayerList) {
			Player lPlayer = Bukkit.getPlayer(uuid);
			lDeadPlayerList.add(lPlayer);
		}
		
		return lDeadPlayerList;
	}
	public ArrayList<Player> getAlivePlayers() {
		ArrayList<Player> lAlivePlayerList = new ArrayList<Player>();
		
		for(UUID uuid  : _AlivePlayerList) {
			Player lPlayer = Bukkit.getPlayer(uuid);
			lAlivePlayerList.add(lPlayer);
		}
		
		return lAlivePlayerList;
	}

}
