package io.github.kaitozerjo;

import dev.waterdog.waterdogpe.plugin.Plugin;
import io.github.kaitozerjo.commands.PluginCommand;

public class Main extends Plugin {

    @Override
    public void onEnable() {
        getProxy().getCommandMap().registerCommand(new PluginCommand());
    }
}
