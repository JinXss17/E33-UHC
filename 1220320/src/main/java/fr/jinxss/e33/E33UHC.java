package fr.jinxss.e33;

import org.bukkit.plugin.java.JavaPlugin;

import fr.jinxss.e33.mobsystem.MobCustomSpawner;

public class E33UHC extends JavaPlugin {

	
 	@Override
    public void onEnable() {
 		//getServer().getPluginManager().registerEvents(new MobSpawnListener(), this);
 		new MobCustomSpawner(this).runTaskTimer(this, 20 * 10L, 20 * 5L); // 5 étant le nombre de secondes entre chaque spawn
        getLogger().info("MonPlugin est activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("MonPlugin est désactivé !");
    }
	
}
