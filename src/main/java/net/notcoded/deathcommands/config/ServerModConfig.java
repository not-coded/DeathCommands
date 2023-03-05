package net.notcoded.deathcommands.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;

import java.util.ArrayList;

@Config(name = "deathcommands-server")
public class ServerModConfig implements ConfigData {

    @Tooltip
    public boolean isEnabled = true;

    @Tooltip
    public ArrayList<String> player_messages = new ArrayList<>();

    @Tooltip
    public int player_amountTimes = 1;

    @Tooltip
    public ArrayList<String> server_messages = new ArrayList<>();

    @Tooltip
    public int server_amountTimes = 1;
}
