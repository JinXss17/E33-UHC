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
    	registerRole(plugin);
    }
    
    private static void registerRole(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRole - Role");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "Roles_role");

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("RBR", "PGP", "RBR");
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('P', Material.PISTON);
        recipe.setIngredient('B', Material.IRON_BARS);
        recipe.setIngredient('G', Material.GLOWSTONE);

        Bukkit.addRecipe(recipe);
    }

}
