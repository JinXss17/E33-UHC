package fr.jinxss.e33.RolesSystem;

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
    	registerLune(plugin);
    	registerRenoir(plugin);
    	registerSciel(plugin);
    	registerMonoco(plugin);
    	registerEsquie(plugin);
    	registerPeintresse(plugin);
    	registerConservateur(plugin);
    	
    }
    

    private static void registerGustave(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRôle - Gustave");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "gustave_role");

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        //Créer le craft pour le rôle sciel
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
    
	private static void registerSciel(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRôle - Sciel");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "Roles_Sciel");




        ShapedRecipe recipe = new ShapedRecipe(key, result);
        //Créer le craft pour le rôle sciel
        recipe.shape("PAP", "AHA", "PAP");
        recipe.setIngredient('P', Material.PAPER);
        recipe.setIngredient('H', Material.DIAMOND_HOE);
        recipe.setIngredient('A', Material.AMETHYST_SHARD);

        Bukkit.addRecipe(recipe);
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
    
    private static void registerMonoco(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRôle - Monoco");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "Roles_Monoco");

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        
        recipe.shape("SRS", "RFR", "SRS");
        recipe.setIngredient('S', Material.FERMENTED_SPIDER_EYE);
        recipe.setIngredient('F', Material.RABBIT_FOOT);
        recipe.setIngredient('R', Material.ROTTEN_FLESH);
        Bukkit.addRecipe(recipe);
    }
    
    private static void registerEsquie(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRôle - Esquie");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "Roles_Esquie");

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        
        recipe.shape("SAS", "GSD", "SPS");
        recipe.setIngredient('S', Material.STONE);
        recipe.setIngredient('A', Material.ANDESITE);
        recipe.setIngredient('G', Material.GRANITE);
        recipe.setIngredient('D', Material.DIORITE);
        recipe.setIngredient('P', Material.DEEPSLATE);
        Bukkit.addRecipe(recipe);
    }
    
    private static void registerPeintresse(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRôle - Peintresse");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "Roles_Peintresse");

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        
        recipe.shape("FDF", "DPD", "FDF");
        recipe.setIngredient('F', Material.FEATHER);
        recipe.setIngredient('P', Material.PAINTING);
        recipe.setIngredient('D', Material.DEEPSLATE);
        Bukkit.addRecipe(recipe);
    }
    
    private static void registerConservateur(JavaPlugin plugin) {
        ItemStack result = new ItemStack(Material.FEATHER);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("§dRôle - Conservateur");
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "Roles_Conservateur");

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        
        recipe.shape("LAL", "ASA", "LAL");
        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        recipe.setIngredient('S', Material.DIAMOND_SWORD);
        recipe.setIngredient('L', Material.LEATHER);
        Bukkit.addRecipe(recipe);
    }
    
}
