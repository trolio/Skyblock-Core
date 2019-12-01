package trolio.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import trolio.interfaces.CommandInterface;

public class CreateIsland extends JavaPlugin implements CommandInterface
{
	
	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args)
	{
		return false;
	}
}
