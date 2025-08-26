package fr.jinxss.e33.Grades;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.jinxss.e33.E33UHC;

public class GradeManager {

	private E33UHC plugin;
	
	private HashMap<UUID, Grade> Grades = new HashMap<UUID, Grade>();
	
	public GradeManager(E33UHC plugin) {
		this.plugin = plugin;
	}
	
	public void setGradeToPlayer(Player p, String GradeName) {
		
		Grade grade = new Grade(GradeName);
		Grades.put(p.getUniqueId(), grade);
		plugin.getConfigFile().set(plugin.getGradeConfigPath()+ "." + p.getUniqueId(), grade.getName());
		plugin.saveConfig();
		
		SetPlayerNameGrade(p);
		
	}
	
	public Grade getPlayerGrade(Player p) {
		return Grades.get(p.getUniqueId());
	}
	
	public void registerPlayerGrade(Player p) {
		
		String gradeString = plugin.getConfigFile().getString(plugin.getGradeConfigPath() + "."+ p.getUniqueId());
		if(gradeString == null)return;
		setGradeToPlayer(p, gradeString);
		SetPlayerNameGrade(p);
	}
	
	private void SetPlayerNameGrade(Player p) {
		
		if(getPlayerGrade(p) == null) return;
		
		p.setCustomName( "[" +getPlayerGrade(p).getName().replace('&', '§') + "§r] " + p.getName());
		p.setDisplayName( "[" +getPlayerGrade(p).getName().replace('&', '§') + "§r] " + p.getName());
		p.setPlayerListName( "[" +getPlayerGrade(p).getName().replace('&', '§') + "§r] " + p.getName());
		p.setCustomNameVisible(true);
	}
	
}
