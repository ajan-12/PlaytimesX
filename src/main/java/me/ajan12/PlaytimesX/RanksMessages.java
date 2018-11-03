package me.ajan12.PlaytimesX;

/*
    Done the ranks list!
    WORKS!
 */

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RanksMessages implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String[] msg = new String[18];
            msg[0] = ChatColor.GOLD + "-----======[ List of regular ranks ]======-----";
            msg[1] = ChatColor.YELLOW + "- " + ChatColor.GREEN + "[Member] " + ChatColor.YELLOW + "- The default rank given to all players.";
            msg[2] = ChatColor.YELLOW + "- " + ChatColor.GREEN + "[Member" + ChatColor.GOLD + "+" + ChatColor.GREEN + "] " + ChatColor.YELLOW + "- The + variation of Member. 3 homes, more Pets, /hat.";
            msg[3] = ChatColor.YELLOW + "- " + ChatColor.DARK_AQUA + "[Senior] " + ChatColor.YELLOW + "- Given to Members when they reach 200 hours of online-time. 4 homes, /hat.";
            msg[4] = ChatColor.YELLOW + "- " + ChatColor.DARK_AQUA + "[Senior" + ChatColor.GOLD + "+" + ChatColor.DARK_AQUA + "] " + ChatColor.YELLOW + "- The + variation of Senior. 6 homes, ability to set Skin.";
            msg[5] = ChatColor.YELLOW + "- " + ChatColor.AQUA + "[Veteran] " + ChatColor.YELLOW + "- Given to Seniors when they reach 450 hours of online-time. 8 homes, /nick.";
            msg[6] = ChatColor.YELLOW + "- " + ChatColor.AQUA + "[Veteran" + ChatColor.GOLD + "+" + ChatColor.AQUA + "] " + ChatColor.YELLOW + "- The + variation of Veteran. Unlimited homes, /fly, colored /nick.";
            msg[7] = ChatColor.YELLOW + "- " + ChatColor.GRAY + "[Legend] " + ChatColor.YELLOW + "- Given to Veterans when they reach 700 hours of online-time. Unlimited homes, colored /nick.";
            msg[8] = ChatColor.YELLOW + "- " + ChatColor.GRAY + "[Legend" + ChatColor.GOLD + "+" + ChatColor.GRAY + "] " + ChatColor.YELLOW + "- The + variation of Legend. More disguises, /enderchest.";
            msg[9] = ChatColor.YELLOW + "- " + ChatColor.GOLD + "[Donator] " + ChatColor.YELLOW + "- A player who has donated to support the server. Commands vary on donation package. See more info at www.ageofelysian.top!";
            msg[10] = ChatColor.GOLD + "-----======[ List of staff ranks ]======-----";
            msg[11] = ChatColor.RED + "- " + ChatColor.DARK_GREEN + "[Temp-Mod] " + ChatColor.YELLOW + "- A temporary server moderator. There to help new and old players, and make sure chat is not offensive.";
            msg[12] = ChatColor.RED + "- " + ChatColor.DARK_GREEN + "[Mod] " + ChatColor.YELLOW + "- A server moderator. There to help new and old players, and makes sure chat is not offensive.";
            msg[13] = ChatColor.RED + "- " + ChatColor.DARK_RED + "[Admin] " + ChatColor.YELLOW + "- Server Administrator. Responsible for server integrity and game features such as Spawn and Shop. If you have game-breaking issues, Admins are the first to report to.";
            msg[14] = ChatColor.RED + "- " + ChatColor.DARK_RED + "[Head-Admin] " + ChatColor.YELLOW + "- Head-Administrator of the server. Responsible for the command and coordination of Admins, and configuration of behind-the-scenes features.";
            msg[15] = ChatColor.RED + "- " + ChatColor.AQUA + "[Builder] " + ChatColor.YELLOW + "- Responsible for constructing and upkeeping server infrastructure, such as Shop, PvP arena and Spawn.";
            msg[16] = ChatColor.RED + "- " + ChatColor.DARK_AQUA + "[Head-Builder] " + ChatColor.YELLOW + "- Main coordinator of Builders. Oversees the construction of new structures.";
            msg[17] = ChatColor.RED + "- " + ChatColor.GOLD + "[Tec-Admin] " + ChatColor.YELLOW + "- Manages te technical behind-the-scenes aspects of the server.";
            p.sendMessage(msg);
            return true;
        } else {
            PlaytimesX.logger.warning("This command can only be applied in-game for now!");
            return true;
        }
    }
}
