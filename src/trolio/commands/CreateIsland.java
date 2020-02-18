package trolio.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import trolio.Main;
import trolio.gen.Island;

public class CreateIsland implements SkyCommand
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			Island island = new Island(player);
			
			island.islandGen();
			Main.saveData();
			island.teleportIsland();
		}
		else
		{
			Bukkit.getLogger().warning("You must be in game to use this command.");
		}
		return true;
	}
	
}
