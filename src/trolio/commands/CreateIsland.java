package trolio.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;
import trolio.interfaces.CommandInterface;

public class CreateIsland implements CommandInterface
{
	
	World world;
	
	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = (Player) sender;
		
		if (args.length > 1)
		{
			return false;
		}
		
		player.sendMessage(ChatColor.GOLD + "Creating Island!");
		return false;
	}
}
