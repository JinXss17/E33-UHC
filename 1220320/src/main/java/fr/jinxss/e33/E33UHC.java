package fr.jinxss.e33;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import fr.jinxss.e33.Levelsystem.LevelSystem;
import fr.jinxss.e33.Listeners.ExplosiveArrowListener;
import fr.jinxss.e33.Listeners.MobSpawnListener;
import fr.jinxss.e33.Listeners.PlayerListener;
import fr.jinxss.e33.PictoSystem.PictoSystem;
import fr.jinxss.e33.mobsystem.AxonSpawner;
import fr.jinxss.e33.mobsystem.BossSpawner;
import fr.jinxss.e33.mobsystem.CustomMobsListener;
import fr.jinxss.e33.mobsystem.InvokeMobsCommand;
import fr.jinxss.e33.mobsystem.MobCustomSpawner;
import fr.jinxss.e33.uhcsystem.UHCSystem;
import net.md_5.bungee.api.ChatColor;

public class E33UHC extends JavaPlugin {

	private NamespacedKey CustomKey = new NamespacedKey(this, "Custom_Mob");
	
	private UHCSystem uhcSystem;
	private LevelSystem levelSystem;
	
	private MobCustomSpawner customMobSpawner;
	private BossSpawner bossSpawner;
	private AxonSpawner axonSpawner;
	private PictoSystem pictoSystem;
	
 	@Override
    public void onEnable() {
    	
 		customMobSpawner = new MobCustomSpawner(this);
 		bossSpawner = new BossSpawner(this);
 		axonSpawner = new AxonSpawner(this);
 		
        
        
        // Enregistre les crafts
 		//Temporairement Désactivée
        //RoleRecipes.registerAll(this);

        
		//getCommand("giveRandomRole").setExecutor(new RandomRoleCommand());
		getCommand("invoke").setExecutor(new InvokeMobsCommand(axonSpawner, bossSpawner));
		
        // Enregistre les listeners
		
		//Desactivation des roles temporairement
//        getServer().getPluginManager().registerEvents(new RoleCraftListener(), this);
//        getServer().getPluginManager().registerEvents(new RoleInteractListener(), this);
//        getServer().getPluginManager().registerEvents(new RoleAttackListener(), this);
        
        getServer().getPluginManager().registerEvents(new MobSpawnListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new ExplosiveArrowListener(), this);
        getServer().getPluginManager().registerEvents(new CustomMobsListener(this), this);
 		
        
        levelSystem = new LevelSystem(this);
        pictoSystem = new PictoSystem(this);
 		uhcSystem = new UHCSystem(this);
 		
 		getLogger().info(ChatColor.LIGHT_PURPLE + "UHC Ready !");
 		
    }
 	
 	public static String GetPréffix() {
 		
 		return "§8[§7E33_UHC§8]§r";
 		
 	}
 	
 	public void startSummoningCustomMobs() {
 		
 		customMobSpawner.StartSummonning();;
 		bossSpawner.StartSummonning();
 		axonSpawner.StartSummonning();
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
