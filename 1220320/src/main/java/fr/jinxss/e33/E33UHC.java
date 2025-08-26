package fr.jinxss.e33;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import fr.jinxss.e33.Levelsystem.LevelSystem;
import fr.jinxss.e33.PictoSystem.PictoSystem;
import fr.jinxss.e33.RolesSystem.RandomRoleCommand;
import fr.jinxss.e33.RolesSystem.RoleRecipes;
import fr.jinxss.e33.RolesSystem.Listeners.ExplosiveArrowListener;
import fr.jinxss.e33.RolesSystem.Listeners.RoleAttackListener;
import fr.jinxss.e33.RolesSystem.Listeners.RoleCraftListener;
import fr.jinxss.e33.RolesSystem.Listeners.RoleInteractListener;
import fr.jinxss.e33.TeamSystem.TeamCommand;
import fr.jinxss.e33.TeamSystem.TeamSystem;
import fr.jinxss.e33.TeamSystem.TeamTabCompleterCommand;
import fr.jinxss.e33.mobsystem.InvokeMobsCommand;
import fr.jinxss.e33.mobsystem.Listeners.CustomMobsListener;
import fr.jinxss.e33.mobsystem.Listeners.MobSpawnListener;
import fr.jinxss.e33.mobsystem.Spawner.AxonSpawner;
import fr.jinxss.e33.mobsystem.Spawner.BossSpawner;
import fr.jinxss.e33.mobsystem.Spawner.MobCustomSpawner;
import fr.jinxss.e33.uhcsystem.PlayerListener;
import fr.jinxss.e33.uhcsystem.UHCSystem;
import net.md_5.bungee.api.ChatColor;

public class E33UHC extends JavaPlugin {

	private NamespacedKey CustomKey = new NamespacedKey(this, "Custom_Mob");
	
	private UHCSystem uhcSystem;
	private LevelSystem levelSystem;
	private TeamSystem teamSystem;
	
	private MobCustomSpawner customMobSpawner;
	private BossSpawner bossSpawner;
	private AxonSpawner axonSpawner;
	private PictoSystem pictoSystem;
	
	private String RoleConfigPath = "System.Roles";
	private String MobConfigPath = "System.Mobs";
	private String PictoDropRatePath = "System.Picto.DropRate";
	
	private String ConfigFileName = "config";
	
	File config;
	YamlConfiguration yamlConfiguration;
	
 	@Override
    public void onEnable() {
 		
 		File file = new File(getDataFolder(), String.valueOf(ConfigFileName) + ".yml");
 		if(!file.exists()) {
 			createFile(ConfigFileName);
 	 		
 	 		
 	 		setDefault(MobConfigPath);
 	 		setDefault(RoleConfigPath);
 	 		setDefault(PictoDropRatePath);
 		}
 		
 		
 		config = getFile(ConfigFileName);
 		yamlConfiguration = YamlConfiguration.loadConfiguration(getFile(ConfigFileName));
 		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
 		
 		loadPictoSystem();
 		
 		uhcSystem = new UHCSystem(this);
 		
 		teamSystem = new TeamSystem(this);
 		
 		getCommand("Team").setExecutor(new TeamCommand(teamSystem));
 		getCommand("Team").setTabCompleter(new TeamTabCompleterCommand());
 		getLogger().info(ChatColor.LIGHT_PURPLE + "UHC Ready !");
 		
 	}
 	
 	public void ToggleRoles() {
 		
 		yamlConfiguration.set(RoleConfigPath, !yamlConfiguration.getBoolean(RoleConfigPath) );
 		try {
	          yamlConfiguration.save(getFile(ConfigFileName));
		} catch (IOException e) {
	          e.printStackTrace();
		} 
 	}
 	
 	public void ToggleMobs() {
 		
 		yamlConfiguration.set(MobConfigPath, !yamlConfiguration.getBoolean(MobConfigPath) );
 		try {
	          yamlConfiguration.save(getFile(ConfigFileName));
		} catch (IOException e) {
	          e.printStackTrace();
		} 
 	}
 	
 	public void loadPictoSystem() {
 		pictoSystem = new PictoSystem(this);
 		levelSystem = new LevelSystem(this);
 	}
 	
 	public void loadRolesSystem() {
 		
 		getCommand("giveRandomRole").setExecutor(new RandomRoleCommand());
 		
        getServer().getPluginManager().registerEvents(new RoleCraftListener(), this);
        getServer().getPluginManager().registerEvents(new RoleInteractListener(), this);
        getServer().getPluginManager().registerEvents(new RoleAttackListener(), this);
        getServer().getPluginManager().registerEvents(new ExplosiveArrowListener(), this);
        
        // Enregistre les crafts
        RoleRecipes.registerAll(this);
 	}
 	
 	public void loadMobSystem() {
 		
		customMobSpawner = new MobCustomSpawner(this);
 		bossSpawner = new BossSpawner(this);
 		axonSpawner = new AxonSpawner(this);
 		
 		getServer().getPluginManager().registerEvents(new MobSpawnListener(this), this);
 		getServer().getPluginManager().registerEvents(new CustomMobsListener(this), this);
 		
 		getCommand("invoke").setExecutor(new InvokeMobsCommand(axonSpawner, bossSpawner));
 	}
 	
 	private void setDefault(String ConfigPath) {
 		if(ConfigPath.equals(PictoDropRatePath)) {
				yamlConfiguration.set(ConfigPath, 0.5f);
			}else {
				yamlConfiguration.set(ConfigPath, false);
			}	
 		try {
 	          yamlConfiguration.save(getFile(ConfigFileName));
 		} catch (IOException e) {
 	          e.printStackTrace();
 		} 
 	}
 	
 	private void createFile(String name) {
	    if (!getDataFolder().exists())
	      getDataFolder().mkdir(); 
	    File file = new File(getDataFolder(), String.valueOf(name) + ".yml");
	    if (!file.exists())
	      try {
	        file.createNewFile();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }  
 	}
 	
 	private File getFile(String name) {
	    return new File(getDataFolder(), String.valueOf(name) + ".yml");
 	}
 	
 	public YamlConfiguration getConfigFile() {
 		return yamlConfiguration;
 	}
 	
 	public boolean isRolesToggled() {
 		
 		return getConfigFile().getBoolean(RoleConfigPath);
 		
 	}
 	
 	public boolean isMobsToggled() {
 		
 		return getConfigFile().getBoolean(MobConfigPath);
 		
 	}
 	
 	public float getPictoDropRate() {
 		
 		return (float)getConfigFile().getDouble(PictoDropRatePath);
 		
 	}
 	
 	public static String GetPréffix() {
 		
 		return "§8[§7E33_UHC§8]§r";
 		
 	}
 	
 	public void startSummoningCustomMobs(int Delay) {
 		
 		customMobSpawner.StartSummonning();
 		Bukkit.getScheduler().runTaskLater(this, () -> {bossSpawner.StartSummonning();}, Delay * 20 * 60);
 		Bukkit.getScheduler().runTaskLater(this, () -> {axonSpawner.StartSummonning();}, Delay * 20 * 60 *  3);
 	}

    @Override
    public void onDisable() {
        getLogger().info("MonPlugin est désactivé !");
    }
    
    public PictoSystem getPictoSystem() {
    	return pictoSystem;
    }
    
    public LevelSystem getLevelSystem() {
    	return levelSystem;
    }
    
    public UHCSystem getUHCSystem() {
    	return uhcSystem;
    }

	public NamespacedKey getCustomKey() {
		return CustomKey;
	}
	
}
