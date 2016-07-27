package pi.Cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pi.Main.Main;

public class ingname implements CommandExecutor{

	private Main plugin;
	public ingname(Main plugin)
	{
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cs instanceof Player)
		{
			Player p = (Player)cs;
			if(cmd.getLabel().equalsIgnoreCase("ingname"))
			{
				if(args.length == 2)
				{
					String fname = args[0];
					String lname = args[1];
					String fullname = fname + " " + lname;
					fullname = fullname.replace("ü", "ue");
					fullname = fullname.replace("ö", "oe");
					fullname = fullname.replace("ä", "ae");
					if(plugin.getConfig().contains(fullname))
					{
						p.sendMessage(ChatColor.RED + "This Name is already in use.");
					}
					else
					{
						plugin.getConfig().set(p.getUniqueId().toString() + ".Name", fullname);
						plugin.saveConfig();
						p.sendMessage(ChatColor.DARK_GREEN + "You are now called '" + fullname + "'!");
					}
					
				}
				else
				{
					p.sendMessage(ChatColor.RED + "/ingname <FirstName> <LastName>");
				}
			}
		}
		else
		{
			return false;
		}
		return true;
	}

}
