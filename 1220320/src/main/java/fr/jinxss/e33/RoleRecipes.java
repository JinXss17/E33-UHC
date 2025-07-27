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
    	registerGustave(plugin);
    	registerMaelle(plugin);
    }
    

    private static void registerGustave(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRôle - Gustave");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "gustave_role");

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("RBR", "PGP", "RBR");
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('P', Material.PISTON);
        recipe.setIngredient('B', Material.IRON_BARS);
        recipe.setIngredient('G', Material.GLOWSTONE);

        Bukkit.addRecipe(recipe);
    }
    
    private static void registerLune(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRôle - Lune");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "Lune_role");

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("BIS", "IGI", "DIA");
        recipe.setIngredient('B', Material.BLAZE_POWDER);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.SNOW_BLOCK);
        recipe.setIngredient('G', Material.GLOWSTONE);
        recipe.setIngredient('D', Material.DIRT);
        recipe.setIngredient('A', Material.AMETHYST_BLOCK);
        
        Bukkit.addRecipe(recipe);
    }

    private static void registerMaelle(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRôle - Maelle");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "maelle_role");

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("MPM", "PIP", "MPM");
        recipe.setIngredient('M', Material.MAGMA_CREAM);
        recipe.setIngredient('P', Material.BLAZE_POWDER);
        recipe.setIngredient('I', Material.IRON_SWORD);

        Bukkit.addRecipe(recipe);
    }
}
