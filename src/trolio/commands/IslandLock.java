package trolio.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import trolio.handlers.LockHandler;

public class IslandLock implements SkyCommand
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		LockHandler.LockIsland();
		return false;
	}
	
}
