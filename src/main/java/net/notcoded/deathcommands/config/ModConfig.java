package net.notcoded.deathcommands.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;

import java.util.ArrayList;

@Config(name = "deathcommands")
public class ModConfig implements ConfigData {

    @Tooltip
    public boolean isEnabled = true;

    @Tooltip
    public ArrayList<String> messages = new ArrayList<>();

    @Tooltip
    public int amountTimes = 1;

    @Tooltip
    public boolean optionsMenu = true;
}
