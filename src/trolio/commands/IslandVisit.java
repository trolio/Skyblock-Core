package trolio.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import trolio.Main;

public class IslandVisit implements SkyCommand
{
	
	private Player target;
	private Player owner;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{	
			String targetPlayer = args[0];
			
			target = Bukkit.getServer().getPlayer(targetPlayer);
		}
		return false;
	}
	
	private Location getIslandLocation()
	{
		return new Location (Main.skyWorld,
				Main.config.getDouble("Islands." + this.target.getUniqueId() + ".x"),
				Main.config.getDouble("Islands." + this.target.getUniqueId() + ".y"),
				Main.config.getDouble("Islands." + this.target.getUniqueId() + ".z"));
	}
	
	public Location getPlayerIsland()
	{
		return getIslandLocation().add(2, 4, 2);
	}
	
	public void tpIsland()
	{
		this.owner.sendMessage(ChatColor.GOLD + "Sending you to island!");
		this.owner.teleport(getPlayerIsland());
	}
	
}
