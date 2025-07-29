package fr.jinxss.e33.e33commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.jinxss.e33.RoleManager;
import fr.jinxss.e33.e33system.nevrons.Petank;

public class RandomRoleCommand implements CommandExecutor {

    private final Set<String> uniqueRolesLeft = new HashSet<>(Arrays.asList(
            "petank"//, "mime", "noco", "sakapattate", "matthieu_le_coloss", "trompetiste", "demineur"
    ));

    private final List<String> multiRoles = Arrays.asList(
           // "clair", "obscur"
    );

    private final Random random = new Random();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Utilisation: /giveRandomRole <joueur>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null || !target.isOnline()) {
            sender.sendMessage(ChatColor.RED + "Ce joueur n'est pas en ligne.");
            return true;
        }

        UUID uuid = target.getUniqueId();
        if (RoleManager.hasRole(uuid)) {
            sender.sendMessage(ChatColor.RED + "Ce joueur a déjà un rôle.");
            return true;
        }

        // Mélanger tous les rôles disponibles (uniques + multiples)
        List<String> availableRoles = new ArrayList<>(uniqueRolesLeft);
        availableRoles.addAll(multiRoles);

        if (availableRoles.isEmpty()) {
            sender.sendMessage(ChatColor.RED + "Plus aucun rôle disponible.");
            return true;
        }

        String selectedRole = availableRoles.get(random.nextInt(availableRoles.size()));
        assignRoleToPlayer(uuid, selectedRole);

        // Si c’est un rôle unique → on le retire
        if (uniqueRolesLeft.contains(selectedRole)) {
            uniqueRolesLeft.remove(selectedRole);
        }

        sender.sendMessage(ChatColor.GREEN + "Rôle '" + selectedRole + "' assigné à " + target.getName() + ".");
        target.sendMessage(ChatColor.GOLD + "Tu as reçu le rôle: §e" + selectedRole);
        return true;
    }

    private void assignRoleToPlayer(UUID uuid, String roleName) {
        switch (roleName.toLowerCase()) {
	        case "petank" -> {
	            Petank petank = new Petank(uuid, "petank");
	            RoleManager.assignRole(uuid, "petank", petank);
	            petank.onAssign();
	        }
            //case "mime" -> RoleManager.assignRole(uuid, "mime", new Mime(uuid, "mime"));
            //case "clair" -> RoleManager.assignRole(uuid, "clair", new Clair(uuid, "clair"));
            //case "obscur" -> RoleManager.assignRole(uuid, "obscur", new Obscur(uuid, "obscur"));
            //case "noco" -> RoleManager.assignRole(uuid, "noco", new Noco(uuid, "noco"));
            //case "sakapattate" -> RoleManager.assignRole(uuid, "sakapattate", new Sakapattate(uuid, "sakapattate"));
            //case "demineur" -> RoleManager.assignRole(uuid, "demineur", new Demineur(uuid, "demineur"));
            //case "trompetiste" -> RoleManager.assignRole(uuid, "trompetiste", new Trompetiste(uuid, "trompetiste"));
            //case "matthieu_le_coloss" -> RoleManager.assignRole(uuid, "matthieu_le_coloss", new MatthieuLeColoss(uuid, "matthieu_le_coloss"));
            default -> Bukkit.getPlayer(uuid).sendMessage(ChatColor.RED + "Erreur : rôle inconnu.");
        }

        // Appelle la méthode onAssign()
        RoleManager.getRole(uuid).onAssign();
    }
}