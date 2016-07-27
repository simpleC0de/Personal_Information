package pi.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pi.Main.Main;

public class PlayerChat implements Listener{

	private Main plugin;
	public PlayerChat(Main plugin)
	{
		this.plugin = plugin;
	}
	@EventHandler
	public void onAsyncChat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString();
		if(plugin.getConfig().contains(uuid))
		{
			String fullname = plugin.getConfig().getString(uuid+".Name");
			String gender = plugin.getConfig().getString(uuid+".Gender");
			if(gender.equalsIgnoreCase("male"))
			{
				p.setDisplayName(ChatColor.DARK_BLUE +"«Male» " + ChatColor.BLUE + fullname);
			}
			else
			{
				p.setDisplayName(ChatColor.DARK_PURPLE +"«Female» " + ChatColor.LIGHT_PURPLE + fullname);
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "Before you can Chat, please select a Gender and a Name!");
			if(p.getDisplayName().equalsIgnoreCase(p.getDisplayName()))
			{
				e.setCancelled(true);
			}
		}
	}
}
