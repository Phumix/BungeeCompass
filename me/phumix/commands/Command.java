package me.phumix.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.phumix.Main;

public class Command implements CommandExecutor {

	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0 || args.length > 1) {
				sender.sendMessage("[BungeeCompass] / Use: /bungeecompass reload.");
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					Main.getPlugin().reloadConfig();
					sender.sendMessage("[BungeeCompass] / Config reloaded.");
				} else {
					sender.sendMessage("[BungeeCompass] / Use: /bungeecompass reload.");
				}
			}
		} else {
			Player player = (Player) sender;
			if (player.hasPermission("bungeecompass.command")) {
				if (args.length == 0 || args.length > 1) {
					player.sendMessage("§3This server is running BungeeCompass " + Main.getPlugin().getDescription().getVersion() + " by §ePhumix§a.");
					player.sendMessage("§aCommands:");
					player.sendMessage("§e/bungeecompass reload - §aReload the config§f.");
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						Main.getPlugin().reloadConfig();
						sender.sendMessage("§f[§aBungeeCompass§f] §aConfig reloaded.");
					} else {
						sender.sendMessage("§f[§aBungeeCompass§f] §aUse: /bungeecompass reload.");
					}
				}
			} else {
				player.sendMessage("§cNo permission.");
			}
		}
		return false;
	}

}
