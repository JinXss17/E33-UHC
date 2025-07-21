package fr.jinxss.e33;

import org.bukkit.plugin.java.JavaPlugin;

public class E33UHC extends JavaPlugin {

	
 	@Override
    public void onEnable() {
        getLogger().info("MonPlugin est activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("MonPlugin est désactivé !");
    }
	
}
