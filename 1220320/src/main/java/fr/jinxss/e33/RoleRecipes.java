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
    	registerSciel(plugin);
    }
    
    private static void registerSciel(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRôle - Sciel");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "Roles_Sciel");

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        //Créer le craft pour le rôle sciel
        recipe.shape("RBR", "PGP", "RBR");
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('P', Material.PISTON);
        recipe.setIngredient('B', Material.IRON_BARS);
        recipe.setIngredient('G', Material.GLOWSTONE);

        Bukkit.addRecipe(recipe);
    }

}
