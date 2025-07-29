package fr.jinxss.e33;

import org.bukkit.plugin.java.JavaPlugin;

import fr.jinxss.e33.listeners.PlayerListener;
import fr.jinxss.e33.listeners.RoleAttackListener;
import fr.jinxss.e33.listeners.RoleCraftListener;
import fr.jinxss.e33.listeners.RoleInteractListener;
import fr.jinxss.e33.uhcsystem.UHCSystem;

public class E33UHC extends JavaPlugin {

	private UHCSystem uhcSystem;
	
	@SuppressWarnings("unused")
	private PictoSystem pictoSystem;
	
 	@Override
    public void onEnable() {
 		pictoSystem = new PictoSystem(this);
        getLogger().info("MonPlugin est activé !");
        
        // Enregistre les crafts
        RoleRecipes.registerAll(this);

        // Enregistre les listeners
        getServer().getPluginManager().registerEvents(new RoleCraftListener(), this);
        getServer().getPluginManager().registerEvents(new RoleInteractListener(), this);
        getServer().getPluginManager().registerEvents(new RoleAttackListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
 		
 		uhcSystem = new UHCSystem(this);
    }
 	
 	public static String GetPréffix() {
 		
 		return "§8[§7E33_UHC§8]§r";
 		
 	}

    @Override
    public void onDisable() {
        getLogger().info("MonPlugin est désactivé !");
    }
    
    public UHCSystem getUHCSystem() {
    	return uhcSystem;
    }
	
}
