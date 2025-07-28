package fr.jinxss.e33;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class RoleRecipes {
	
    public static void registerAll(JavaPlugin plugin) {
    	registerRenoir(plugin);
    }
    
    private static void registerRenoir(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRôle - Renoir");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "Roles_Renoir");

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        
        recipe.shape("RBR", "BSB", "RBR");
        recipe.setIngredient('R', Material.ROTTEN_FLESH);
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('B', Material.BONE);

        Bukkit.addRecipe(recipe);
    }

}
