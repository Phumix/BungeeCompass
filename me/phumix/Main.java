package me.phumix;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.phumix.commands.Command;
import me.phumix.listener.Events;

public class Main extends JavaPlugin {

	public void onEnable() {
		getCommand("bungeecompass").setExecutor(new Command());
		getServer().getPluginManager().registerEvents(new Events(), this);
		saveDefaultConfig();
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getLogger().info("BungeeCompass has been loaded.");
	}
	public static void sendPlayer(Player player, String serverInString) {
		ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
		dataOutput.writeUTF("Connect");
		dataOutput.writeUTF(serverInString);
		player.sendPluginMessage(getPlugin(), "BungeeCord", dataOutput.toByteArray());
	}
	
	public static Plugin getPlugin() {
		return getPlugin(Main.class);
	}
	
}
