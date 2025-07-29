package fr.jinxss.e33;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Roles {

    protected UUID uuid;
    protected String name;
    protected double speed;
    protected double force;
    protected double resi;

    // Liste des objets associés au rôle (peut être vide, 1 ou plusieurs)
    protected List<ItemStack> roleItems = new ArrayList<>();


    public Roles(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public List<ItemStack> getRoleItems() {
        return roleItems;
    }

    // Méthode appelée quand le rôle est attribué
    public abstract void onAssign();

    // Méthode appelée quand le joueur utilise son ou ses pouvoir(s)
    public abstract void onPowerUse();
    
    public int getSpeed() {
        return speed/100;
    }

    public int getForce() {
        return force/100;
    }

    public int getResi() {
        return resi/100;
    }

}