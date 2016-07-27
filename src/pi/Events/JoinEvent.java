package pi.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import pi.Main.Main;

public class JoinEvent implements Listener{

	private Inventory genders = Bukkit.createInventory(null, 27, ChatColor.LIGHT_PURPLE + "Genderselector");
	private ItemStack male = new ItemStack(Material.WOOL, 2, (short)11);
	private ItemStack female = new ItemStack(Material.WOOL, 1, (short)6);
	private Main plugin;
	public JoinEvent(Main plugin)
	{
		this.plugin = plugin;
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		new BukkitRunnable() {
			
			@Override
			public void run() {
				ItemMeta fe = female.getItemMeta();
				fe.setDisplayName(ChatColor.LIGHT_PURPLE + "Female");
				ItemMeta me = male.getItemMeta();
				female.setItemMeta(fe);
				me.setDisplayName(ChatColor.DARK_BLUE + "Male");
				male.setItemMeta(me);
				Player p = e.getPlayer();
				genders.setItem(11, male);
				genders.setItem(15, female);
				if(plugin.getConfig().contains(p.getUniqueId().toString()))
				{
					String name = plugin.getConfig().getString(p.getUniqueId().toString() + ".Name");
					String gender = plugin.getConfig().getString(p.getUniqueId().toString() + ".Gender");
					p.sendMessage(ChatColor.GREEN + "Your Name: " + name + ", your Gender: " + gender);
					if(gender.equalsIgnoreCase("female"))
					{
						
					}
					else
					{
						
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Please choose an Ingamename with /ingname Firstname Lastname");
					p.openInventory(genders);
					
				
				}
				
			}
		}.runTaskLater(plugin, 20);
		
		
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		
		try
		{
			if(e.getCurrentItem().getAmount() == 1)
		{
			plugin.getConfig().set(p.getUniqueId().toString() + ".Gender", "female");
			plugin.saveConfig();
			p.closeInventory();
			p.sendMessage(ChatColor.LIGHT_PURPLE + "Youre now Female!");
		}
		if(e.getCurrentItem().getAmount() == 2)
		{
			plugin.getConfig().set(p.getUniqueId().toString() + ".Gender", "male");
			plugin.saveConfig();
			p.closeInventory();
			p.sendMessage(ChatColor.DARK_BLUE + "Youre now Male!");
		}
		else
		{
			return;
		}
			
		}catch(NullPointerException ex)
		{
			return;
		}
			
		
		
	}
}
