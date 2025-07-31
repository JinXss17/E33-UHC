package fr.jinxss.e33;

import org.bukkit.plugin.java.JavaPlugin;

import fr.jinxss.e33.Levelsystem.LevelSystem;
import fr.jinxss.e33.Listeners.ExplosiveArrowListener;
import fr.jinxss.e33.Listeners.PlayerListener;
import fr.jinxss.e33.Listeners.RoleAttackListener;
import fr.jinxss.e33.Listeners.RoleCraftListener;
import fr.jinxss.e33.Listeners.RoleInteractListener;
import fr.jinxss.e33.PictoSystem.PictoSystem;
import fr.jinxss.e33.e33commands.RandomRoleCommand;
import fr.jinxss.e33.mobsystem.MobCustomSpawner;
import fr.jinxss.e33.uhcsystem.UHCSystem;

public class E33UHC extends JavaPlugin {

	private UHCSystem uhcSystem;
	private LevelSystem levelSystem;
	
	@SuppressWarnings("unused")
	private PictoSystem pictoSystem;
	
 	@Override
    public void onEnable() {
    	//getServer().getPluginManager().registerEvents(new MobSpawnListener(), this);
 		new MobCustomSpawner(this).runTaskTimer(this, 20 * 10L, 20 * 5L); // 5 étant le nombre de secondes entre chaque spawn
 		
        getLogger().info("MonPlugin est activé !");
        
        // Enregistre les crafts
        RoleRecipes.registerAll(this);

		getCommand("giveRandomRole").setExecutor(new RandomRoleCommand());

        // Enregistre les listeners
        getServer().getPluginManager().registerEvents(new RoleCraftListener(), this);
        getServer().getPluginManager().registerEvents(new RoleInteractListener(), this);
        getServer().getPluginManager().registerEvents(new RoleAttackListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new ExplosiveArrowListener(), this);
 		
        
        levelSystem = new LevelSystem(this);
        pictoSystem = new PictoSystem(this);
 		uhcSystem = new UHCSystem(this);
 		
    }
 	
 	public static String GetPréffix() {
 		
 		return "§8[§7E33_UHC§8]§r";
 		
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
	
}
