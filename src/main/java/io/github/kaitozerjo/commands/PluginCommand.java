package io.github.kaitozerjo.commands;

import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.plugin.PluginManager;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PluginCommand extends Command {

    public PluginCommand() {
        super("waterdogplugins", CommandSettings.builder()
                .setDescription("Proxy Plugins Command")
                .setUsageMessage("/waterdogplugins [pluginName]")
                .setPermission("waterdog.command.plugins")
                .setAliases("wdpl")
                .build());
    }

    @Override
    public boolean onExecute(CommandSender sender, String alias, String[] args) {
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        Plugin[] plugins = pluginManager.getPlugins().toArray(new Plugin[0]);

        if (plugins.length == 0) {
            sender.sendMessage("§cNo plugins are currently installed.");
            return true;
        }

        if (args.length == 0) {
            String pluginList = Arrays.stream(plugins)
                    .map(p -> p.getDescription().getName())
                    .collect(Collectors.joining(", "));
            sender.sendMessage(String.format("§ePlugins (%d): §f%s", plugins.length, pluginList));
        } else {
            // Show information about a specific plugin
            String pluginName = args[0];
            Plugin plugin = Arrays.stream(plugins)
                    .filter(p -> p.getDescription().getName().equalsIgnoreCase(pluginName))
                    .findFirst()
                    .orElse(null);

            if (plugin == null) {
                sender.sendMessage("§cPlugin not found: " + pluginName);
            } else {
                sender.sendMessage(String.format(plugin.getDescription().getName() + ":"));
                sender.sendMessage(String.format("§fVersion: §6%s", plugin.getDescription().getVersion()));
                sender.sendMessage(String.format("§fAuthor: §e%s", plugin.getDescription().getAuthor()));
                sender.sendMessage(String.format("§fDescription: §a%s", plugin.getDescription()));
            }
        }

        return true;
    }
}
