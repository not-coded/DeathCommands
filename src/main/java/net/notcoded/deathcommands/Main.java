package net.notcoded.deathcommands;

import com.mojang.blaze3d.platform.InputConstants;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.notcoded.deathcommands.config.ClientModConfig;
import net.notcoded.deathcommands.config.ServerModConfig;
import org.lwjgl.glfw.GLFW;

public class Main implements ModInitializer {

	public static MinecraftServer server;

	public static Minecraft client;

	public static KeyMapping keyBinding;

	public static ClientModConfig clientModConfig;

	public static ServerModConfig serverModConfig;
	@Override
	public void onInitialize() {

		try {
			loadClient();
		} catch(NoClassDefFoundError | Exception ignored){
			loadServer();
		}
	}

	private void loadClient(){
		Main.client = Minecraft.getInstance();

		AutoConfig.register(ClientModConfig.class, GsonConfigSerializer::new);
		clientModConfig = AutoConfig.getConfigHolder(ClientModConfig.class).getConfig();

		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyMapping(
				"deathcommands.keybinds.menu", // The translation key of the keybinding's name
				InputConstants.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_Y, // The keycode of the key
				"deathcommands.title" // The translation key of the keybinding's category.
		));
	}

	private void loadServer() {
		AutoConfig.register(ServerModConfig.class, GsonConfigSerializer::new);
		serverModConfig = AutoConfig.getConfigHolder(ServerModConfig.class).getConfig();
	}
}
