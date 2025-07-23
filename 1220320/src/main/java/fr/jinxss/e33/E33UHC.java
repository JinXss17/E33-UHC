package fr.jinxss.e33;

import org.bukkit.plugin.java.JavaPlugin;

import fr.jinxss.e33.listeners.RoleCraftListener;

public class E33UHC extends JavaPlugin {

	
 	@Override
    public void onEnable() {
        getLogger().info("MonPlugin est activé !");
        
        // Enregistre les crafts
        RoleRecipes.registerAll(this);

        // Enregistre les listeners
        getServer().getPluginManager().registerEvents(new RoleCraftListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("MonPlugin est désactivé !");
    }
	
}
