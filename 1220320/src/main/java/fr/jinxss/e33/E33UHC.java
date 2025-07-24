package fr.jinxss.e33;

import org.bukkit.plugin.java.JavaPlugin;

import fr.jinxss.e33.listeners.RoleAttackListener;
import fr.jinxss.e33.listeners.RoleCraftListener;
import fr.jinxss.e33.listeners.RoleInteractListener;

public class E33UHC extends JavaPlugin {

	
 	@Override
    public void onEnable() {
        getLogger().info("MonPlugin est activé !");
        
        // Enregistre les crafts
        RoleRecipes.registerAll(this);

        // Enregistre les listeners
        getServer().getPluginManager().registerEvents(new RoleCraftListener(), this);
        getServer().getPluginManager().registerEvents(new RoleInteractListener(), this);
        getServer().getPluginManager().registerEvents(new RoleAttackListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("MonPlugin est désactivé !");
    }
	
}
