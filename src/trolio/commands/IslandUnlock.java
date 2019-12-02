package trolio.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import trolio.handlers.LockHandler;

public class IslandUnlock implements SkyCommand
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		LockHandler.UnlockIsland();
		return false;
	}
	
}
