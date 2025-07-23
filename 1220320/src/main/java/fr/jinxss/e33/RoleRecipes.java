package fr.jinxss.e33;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class RoleRecipes {

    public static void registerAll(JavaPlugin plugin) {
        registerMaelle(plugin);
        // Tu appelleras ici tous les autres plus tard
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
