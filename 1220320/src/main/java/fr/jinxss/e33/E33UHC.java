package fr.jinxss.e33;

import org.bukkit.plugin.java.JavaPlugin;

import fr.jinxss.e33.Listeners.PvPListener;

public class E33UHC extends JavaPlugin {

	
 	@Override
    public void onEnable() {
        getLogger().info("MonPlugin est activé !");
        
        getServer().getPluginManager().registerEvents(new PvPListener(), this);
        
        
        
    }

    @Override
    public void onDisable() {
        getLogger().info("MonPlugin est désactivé !");
    }
	
}
