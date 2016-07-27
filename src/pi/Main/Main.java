package pi.Main;

import org.bukkit.plugin.java.JavaPlugin;

import pi.Cmds.ingname;
import pi.Events.JoinEvent;
import pi.Events.PlayerChat;

public class Main extends JavaPlugin {

	public void onEnable()
	{
		loadConfig();
		getCommand("ingname").setExecutor(new ingname(this));
		getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
		getServer().getPluginManager().registerEvents(new PlayerChat(this), this);
	}
	public void onDisable()
	{
		
	}
	public void loadConfig()
	{
		if(getDataFolder().exists())
		{
			reloadConfig();
			
		}
		else
		{
			getConfig().set("enable", true);
			getConfig().set("language", "english");
			saveConfig();
		}
	}
}
