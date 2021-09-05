package ru.whitebeef.nvcosmetic.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NVCosmecticCommandExecutor implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> retArray = new ArrayList<>();
        if (args.length == 1) retArray.addAll(Arrays.asList("reload", "set"));
        if (args.length == 2) Bukkit.getOnlinePlayers().forEach(player -> retArray.add(player.getName()));
        return retArray;
    }
}
