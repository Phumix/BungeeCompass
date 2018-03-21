package me.phumix.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.phumix.Main;
import me.phumix.utils.InventorySelector;


@SuppressWarnings("deprecation")
public class Events implements Listener {

	@EventHandler
	private void onClick(InventoryClickEvent event) {
	    Player player = (Player)event.getWhoClicked();
	    if ((event.getInventory().getName().equals(Main.getPlugin().getConfig().getString("inv-name").replace("&", "§"))) && 
	      (event.getCurrentItem() != null) && (event.getCurrentItem().getType() != null)) {
	      event.setCancelled(true);
	      int i = 1;
	      do {
	        if (event.getCurrentItem().getTypeId() == Main.getPlugin().getConfig()
	          .getInt("server." + i + ".itemid"))
	        {
	          if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Main.getPlugin().getConfig().getString("server." + i + ".display").replace("&", "§"))) {
	            Main.sendPlayer(player, 
	              Main.getPlugin().getConfig().getString("server." + i + ".bungee-connect"));
	            player.closeInventory();
					}
				}
				i++;
			} while (i <= Main.getPlugin().getConfig().getInt("total-servers"));
		}
	}

	@EventHandler
	private void onJoin(PlayerJoinEvent eventt) {
		Player player = eventt.getPlayer();
		if (Main.getPlugin().getConfig().getBoolean("join-compass")) {
			ItemStack stack = new ItemStack(Material.COMPASS);
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(Main.getPlugin().getConfig().getString("compass-name").replace("&", "§"));
			stack.setItemMeta(meta);
			player.getInventory().setItem(Main.getPlugin().getConfig().getInt("player-slot"), stack);
		}
	}

	@EventHandler
	private void onLeave(PlayerQuitEvent event) {
	    event.getPlayer().getInventory().clear();
	}

	@EventHandler
	private void onInteract(PlayerInteractEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS
				&& event.getAction() != Action.PHYSICAL) {
			if (event.getPlayer().hasPermission("bungeecompass.use")) {
				if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()
						.equals(Main.getPlugin().getConfig().getString("compass-name").replace("&", "§"))) {
					event.setCancelled(true);
					new InventorySelector(event.getPlayer());
				}
			}
		}
	}

	@EventHandler
	private void onDrop(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack().getType() == Material.COMPASS) {
			if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName()
					.equals(Main.getPlugin().getConfig().getString("compass-name").replace("&", "§"))) {
				if (Main.getPlugin().getConfig().getBoolean("compass-drop") == false) {
					event.setCancelled(true);
				}
			}
		}
	}

}
