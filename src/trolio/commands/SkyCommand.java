package trolio.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SkyCommand
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);
}
