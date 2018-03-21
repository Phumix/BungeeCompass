package me.phumix.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.phumix.Main;




public class InventorySelector
{
  public InventorySelector(Player player)
  {
    Inventory inv = Bukkit.createInventory(player, Main.getPlugin().getConfig().getInt("inv-size"), 
      Main.getPlugin().getConfig().getString("inv-name").replace("&", "§"));
    int i = 1;
    
    do
    {
      @SuppressWarnings("deprecation")
      ItemStack stack = new ItemStack(Main.getPlugin().getConfig().getInt("server." + i + ".itemid"));
      ItemMeta meta = stack.getItemMeta();
      meta.setDisplayName(Main.getPlugin().getConfig().getString("server." + i + ".display").replace("&", "§"));
      if (Main.getPlugin().getConfig().getBoolean("server." + i + ".activatelore")) {
        meta.setLore(Main.getPlugin().getConfig().getStringList("server." + i + ".lore"));
      }
      stack.setItemMeta(meta);
      stack.setAmount(Main.getPlugin().getConfig().getInt("server." + i + ".itemamount"));
      inv.setItem(Main.getPlugin().getConfig().getInt("server." + i + ".invslot"), stack);
      i++;
    } while (
    










      i <= Main.getPlugin().getConfig().getInt("total-servers"));
    
    player.openInventory(inv);
  }
}
