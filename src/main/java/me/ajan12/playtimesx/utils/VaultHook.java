package me.ajan12.PlaytimesX.Utils;

import me.ajan12.PlaytimesX.PlaytimesX;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

public class VaultHook {

    private static Permission perms;

    public static boolean init(final PlaytimesX main) {
        final Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");
        if (vault == null) {
            main.getLogger().warning(ConfigManager.Messages.ENABLE__VAULT_NOT_FOUND.getMessage());
            return false;
        }

        final RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        if (perms == null) {
            main.getLogger().warning(ConfigManager.Messages.ENABLE__PERM_PLUGIN_NOT_FOUND.getMessage());
            return false;
        }
        return true;
    }

    public static Permission getPerms() { return perms; }
}
