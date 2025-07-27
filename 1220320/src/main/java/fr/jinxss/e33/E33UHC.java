package fr.jinxss.e33;

import org.bukkit.plugin.java.JavaPlugin;

import fr.jinxss.e33.Listeners.PlayerListener;
import fr.jinxss.e33.uhcsystem.UHCSystem;

public class E33UHC extends JavaPlugin {

	private UHCSystem uhcSystem;
	
 	@Override
    public void onEnable() {
 		
 		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
 		
 		uhcSystem = new UHCSystem(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("MonPlugin est désactivé !");
    }
    
    public UHCSystem getUHCSystem() {
    	return uhcSystem;
    }
	
}
