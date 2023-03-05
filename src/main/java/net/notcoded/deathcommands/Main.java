package net.notcoded.deathcommands;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.server.MinecraftServer;
import net.notcoded.deathcommands.config.ClientModConfig;
import net.notcoded.deathcommands.config.ServerModConfig;
import org.lwjgl.glfw.GLFW;

public class Main implements ModInitializer {

	public static MinecraftServer server;

	public static MinecraftClient client;

	public static KeyBinding keyBinding;

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
		Main.client = MinecraftClient.getInstance();

		AutoConfig.register(ClientModConfig.class, GsonConfigSerializer::new);
		clientModConfig = AutoConfig.getConfigHolder(ClientModConfig.class).getConfig();

		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"deathcommands.keybinds.menu", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_Y, // The keycode of the key
				"deathcommands.title" // The translation key of the keybinding's category.
		));
	}

	private void loadServer() {
		AutoConfig.register(ServerModConfig.class, GsonConfigSerializer::new);
		serverModConfig = AutoConfig.getConfigHolder(ServerModConfig.class).getConfig();
	}
}
