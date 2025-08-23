package fr.jinxss.e33.uhcsystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.jinxss.e33.E33UHC;
import fr.jinxss.e33.Levelsystem.PlayerLevel;
import fr.jinxss.e33.PictoSystem.PlayerPictos;
import fr.jinxss.e33.uhcsystem.list.EGameStates;

public class UHCSystem {
	
	private E33UHC plugin;
	
	private static ArrayList<Player> _PlayerList = new ArrayList<Player>();
	private static ArrayList<UUID> _DeadPlayerList = new ArrayList<UUID>();
	private static ArrayList<UUID> _AlivePlayerList = new ArrayList<UUID>();
	
	private float teleportHeight = 300;
	
	private boolean PvP = false;
	
	private EGameStates GameState;
	
	private UHCScoreBoard Board;
	private UHCConfigMenu menuConfig;
	UHCBorder border;
	
	private int FirstBossSpawn = 10; //en MIN
	
	
	public UHCSystem(E33UHC plugin) {
		this.plugin = plugin;
		GameState = EGameStates.Waiting;
		border = new UHCBorder();
		Board = new UHCScoreBoard(plugin, this, border , plugin.getLevelSystem());
		menuConfig = new UHCConfigMenu(plugin, this);
		plugin.getServer().getPluginManager().registerEvents(menuConfig, plugin);
	}
	
	public UHCConfigMenu getConfigMenu() {
		return menuConfig;
	}
	
	private float GetTeleportRay() {
		
		return (float)border.getCurrentSize();
		
	}
	
	public void StartGame() {
		
		Random r = new Random();
		border.setBorderSize(border.InitialBorderSize, 0);
		GameState = EGameStates.Playing;
		

 		
 		
 		
		for(Player p : Bukkit.getOnlinePlayers()) {
			addPlayerToGame(p);
		}
		
		for(Player p : _PlayerList) {
			
			r.setSeed(r.nextLong());
			
			float lX =  ((float) Math.sin(r.nextDouble())* GetTeleportRay() );
			float lZ =  ((float) Math.cos(r.nextDouble())* GetTeleportRay() );
			
			p.teleport(new Location(Bukkit.getWorld("World"),lX, teleportHeight, lZ) );
			p.getInventory().clear();
			_AlivePlayerList.add(p.getUniqueId());
			
			PlayerLevel playerLevel = new PlayerLevel(null, p);
			PlayerPictos playerPicto = new PlayerPictos(playerLevel, plugin);
			playerLevel.setPlayerPicto(playerPicto);
			
			plugin.getLevelSystem().registerPlayerLevel(p, playerLevel);
			plugin.getPictoSystem().registerPlayerPictos(p, playerPicto);
			
		}
		
		Board.startUpdating();
		// System de Roles
		if(plugin.isRolesToggled()) {
			plugin.loadRolesSystem();
		}
 		// System de Mobs Custom
 		if(plugin.isMobsToggled()) {
 			plugin.loadMobSystem();
 			plugin.startSummoningCustomMobs(FirstBossSpawn);
 		}
	}
	
	public void setGameState(EGameStates pGameState) {
		this.GameState = pGameState;
	}
	public EGameStates getGameState() {
		return this.GameState;
	}
	
	public UHCScoreBoard getBoard() {
		
		return Board;
	
	}
	
	public UHCBorder getBorder() {
		
		return border;
		
	}
	
	public void RegisterScoreBoard() {
		Board = new UHCScoreBoard(plugin, this, new UHCBorder(), plugin.getLevelSystem());
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
