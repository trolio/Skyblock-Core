package trolio.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import trolio.Main;

public class IslandVisit implements SkyCommand
{
	
	public Player target;
	public Player targetOnline;
	public OfflinePlayer targetOffline;
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		
		Player player = (Player) sender;
		
		if (sender instanceof Player)
		{	
			String targetPlayer = args[1];
			String cmdSender = ((Player) sender).getDisplayName();
			
			targetOnline = Bukkit.getServer().getPlayer(args[1]);
			targetOffline = Bukkit.getOfflinePlayer(targetPlayer);
			
			if (targetPlayer.equalsIgnoreCase(cmdSender))
			{
				player.sendMessage(ChatColor.RED + "You can not visit your own island!");
			}
			else if (targetPlayer != cmdSender)
			{
				if (!(targetOnline == null))
				{
					player.teleport(getPlayerIslandOnline());
					sender.sendMessage(ChatColor.GOLD + "Sending you to " + targetOnline.getName() + "'s island!");
					targetOnline.sendMessage(ChatColor.GOLD + sender.getName() + " is visiting your island!");
				}
				else
				{
					player.teleport(getPlayerIslandOffline());
					sender.sendMessage(ChatColor.GOLD + "Sending you to " + targetOffline.getName() + "'s island!");
				}
			}
		}
		return false;
	}
	
	private Location getIslandLocationOnline()
	{
		return new Location (Main.skyWorld,
				Main.configIslands.getDouble("Islands." + this.targetOnline.getUniqueId() + ".x"),
				Main.configIslands.getDouble("Islands." + this.targetOnline.getUniqueId() + ".y"),
				Main.configIslands.getDouble("Islands." + this.targetOnline.getUniqueId() + ".z"));
	}
	
	private Location getIslandLocationOffline()
	{
		return new Location (Main.skyWorld,
				Main.configIslands.getDouble("Islands." + this.targetOffline.getUniqueId() + ".x"),
				Main.configIslands.getDouble("Islands." + this.targetOffline.getUniqueId() + ".y"),
				Main.configIslands.getDouble("Islands." + this.targetOffline.getUniqueId() + ".z"));
	}
	
	public Location getPlayerIslandOnline()
	{
		return getIslandLocationOnline().add(2, 4, 2);
	}
	
	public Location getPlayerIslandOffline()
	{
		return getIslandLocationOffline().add(2, 4, 2);
	}
}
