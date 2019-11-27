package trolio.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import trolio.interfaces.CommandInterface;

public class IslandCommand implements CommandInterface
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = (Player) sender;
		player.sendMessage("Arguments not complete!");
		return false;
	}
}
