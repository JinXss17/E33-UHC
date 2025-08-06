package fr.jinxss.e33;

import java.util.*;

//import fr.jinxss.e33.Roles;

import java.util.UUID;

import fr.jinxss.e33.e33system.Roles;

public class RoleManager {

    private static final Set<String> assignedRoles = new HashSet<>();
    private static final Map<UUID, Roles> playerRoles = new HashMap<>();

    public static boolean isRoleAvailable(String roleName) {
        return !assignedRoles.contains(roleName);
    }

    public static boolean hasRole(UUID uuid) {
        return playerRoles.containsKey(uuid);
    }

    public static Roles getRole(UUID uuid) {
        return playerRoles.get(uuid);
    }

    public static void assignRole(UUID uuid, String roleName, Roles role) {
        assignedRoles.add(roleName);
        playerRoles.put(uuid, role);
    }
}