package fr.jinxss.e33.uhcsystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.uhcsystem.list.EGameStates;

public class UHCSystem {
	
	private E33UHC plugin;
	
	private static ArrayList<Player> _PlayerList = new ArrayList<Player>();
	private static ArrayList<UUID> _DeadPlayerList = new ArrayList<UUID>();
	private static ArrayList<UUID> _AlivePlayerList = new ArrayList<UUID>();
	
	private float randomTeleportRay = 400;
	private float teleportHeight = 120;
	
	private boolean PvP = false;
	
	private EGameStates GameState;
	
	@SuppressWarnings("unused")
	private UHCScoreBoard Board;
	
	
	public UHCSystem(E33UHC plugin) {
		this.plugin = plugin ;
		GameState = EGameStates.Waiting;
	}
	
	public void StartGame() {
		
		Random r = new Random();
		
		for(Player p : _PlayerList) {
			
			r.setSeed(r.nextLong());
			
			float lX =  ((float) Math.sin(r.nextDouble()* randomTeleportRay ) );
			float lZ =  ((float) Math.cos(r.nextDouble()* randomTeleportRay ) );
			
			p.teleport(new Location(Bukkit.getWorld("World"),lX, teleportHeight, lZ) );
			_AlivePlayerList.add(p.getUniqueId());
		}
		
		RegisterScoreBoard();
	}
	
	public void setGameState(EGameStates pGameState) {
		this.GameState = pGameState;
	}
	public EGameStates getGameState() {
		return this.GameState;
	}
	
	public void RegisterScoreBoard() {
		Board = new UHCScoreBoard(plugin, this, new UHCBorder());
	}
	
	public boolean isPvpEnabled() {
	    return PvP;
	}
	
	public void TogglePvP() {
		PvP = !PvP;
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
