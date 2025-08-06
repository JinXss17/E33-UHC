package fr.jinxss.e33.e33system.roles;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.jinxss.e33.e33system.Roles;


public class Lune extends Roles {

    public enum Element {
        FEU, GLACE, FOUDRE, TERRE
    }

    private Element current = Element.FEU;
    private final ItemStack catalyst;

    public Lune(UUID uuid, String name) {
        super(uuid, name);

        catalyst = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta meta = catalyst.getItemMeta();
        meta.setDisplayName("§bCatalyseur d’élément");
        catalyst.setItemMeta(meta);

        roleItems.add(catalyst);
    }

    @Override
    public void onAssign() {
        Player p = getPlayer();
        if (p == null) return;

        p.sendMessage("§bTu es Lune ! Clique sur ton catalyseur pour changer d’élément.");
        p.getInventory().addItem(catalyst);
    }

    @Override
    public void onPowerUse() {
        Player p = getPlayer();
        if (p == null) return;

        p.removePotionEffect(PotionEffectType.SPEED);
        p.removePotionEffect(PotionEffectType.RESISTANCE);
        
        // Rotation des éléments
        switch (current) {
            case TERRE:
                current = Element.FEU;
                p.sendMessage("§c→ ÉLÉMENT : FEU");
                break;
            case FEU:
                current = Element.GLACE;
                p.sendMessage("§3→ ÉLÉMENT : GLACE");
                break;
            case GLACE:
                current = Element.FOUDRE;
                p.sendMessage("§f→ ÉLÉMENT : FOUDRE");
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                break;
            case FOUDRE:
                current = Element.TERRE;
                p.sendMessage("§7→ ÉLÉMENT : TERRE");
                p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, Integer.MAX_VALUE, 0, false, false));
                break;
        }

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1f, 1.2f);
    }

    public Element getCurrentElement() {
        return current;
    }
    
    public boolean isLuneItem(ItemStack item) {
        return item != null && item.isSimilar(catalyst);
    }
}