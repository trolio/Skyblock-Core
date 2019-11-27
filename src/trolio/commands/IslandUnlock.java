package trolio.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import trolio.interfaces.CommandInterface;

public class IslandUnlock implements CommandInterface
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = (Player) sender;
		
		if (args.length > 1)
		{
			return false;
		}
		
		player.sendMessage(ChatColor.GOLD + "Island is now open to vistors");
		
		return false;
	}
}
