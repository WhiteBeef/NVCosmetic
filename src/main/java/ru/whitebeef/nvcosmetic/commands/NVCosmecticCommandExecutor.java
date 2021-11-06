package ru.whitebeef.nvcosmetic.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import ru.whitebeef.nvcosmetic.NVCosmetic;
import ru.whitebeef.nvcosmetic.utils.chat.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NVCosmecticCommandExecutor implements TabExecutor {

    // /nvcosmetics wear <player> <namespace> <color>
    // /nvcosmetics unwear <player>
    // /nvcosmetics add <plaver> <namespace>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) return true;
        if (args[1].equalsIgnoreCase("reload")) {
            NVCosmetic.getInstance().reload();
            sender.sendMessage(Color.colorize("&aКонфиг перезагружен."));
            return true;
        }


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> retArray = new ArrayList<>();
        if (args.length == 1) retArray.addAll(Arrays.asList("reload", "unwear", "wear", "add"));
        if (args.length == 2) Bukkit.getOnlinePlayers().forEach(player -> retArray.add(player.getName()));
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("wear") || args[0].equalsIgnoreCase("add")) {
            }
        }
        return retArray;
    }
}
