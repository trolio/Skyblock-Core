package trolio.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import trolio.gen.Island;

public class IslandHome implements SkyCommand
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			Island island = new Island(player);
			island.teleportIsland();
		}
		else
		{
			Bukkit.getLogger().info("You must be ingame to perform this command!");
		}
		
		return true;
	}
	
}
