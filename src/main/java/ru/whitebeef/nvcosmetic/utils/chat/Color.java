package ru.whitebeef.nvcosmetic.utils.chat;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {
    private static final Pattern HEX_PATTERN = Pattern.compile("<#[A-Fa-f0-9]{6}>");

    public static String colorize(String message) {
        if (message == null) return "";
        String parsed = message;
        Matcher matcher = HEX_PATTERN.matcher(parsed);
        while (matcher.find()) {
            String color = parsed.substring(matcher.start() + 1, matcher.end() - 1);
            parsed = parsed.replace(color, String.valueOf(ChatColor.of(color)));
            matcher = HEX_PATTERN.matcher(parsed);
        }
        return ChatColor.translateAlternateColorCodes('&', parsed);
    }

    public static ArrayList<String> colorize(String... text) {
        ArrayList<String> retList = new ArrayList<>();
        for (String str : text)
            if (str != null)
                retList.add(colorize(str));
        return retList;
    }

    public static ArrayList<String> colorize(List<String> text) {
        ArrayList<String> retList = new ArrayList<>();
        text.forEach(str -> {
            if (str != null) retList.add(colorize(str));
        });
        return retList;
    }

    public static int getColorFromHEX(String hex) {
        return Integer.parseInt(hex.replace("#", "")
                .replace("<", "")
                .replace(">", ""), 16);
    }

    public static String getHexFromColor(int color) {
        return "#" + Integer.toString(color, 16);
    }
}
