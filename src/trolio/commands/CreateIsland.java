package trolio.commands;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import trolio.interfaces.CommandInterface;

public class CreateIsland extends JavaPlugin implements CommandInterface
{
	
	World world;
	
	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args)
	{
		
		Player player = (Player) sender;
		
		UUID uuid = player.getUniqueId();
		String playerUUID = uuid.toString();
		
		File dataFolder = new File(this.getDataFolder().getPath());
		String strData = dataFolder.toString();
		String[] split = strData.toString().split(File.pathSeparator);
		String rootFolder = split[split.length - 3];
		File root = new File(rootFolder);
		
		File srcDir = new File (root+File.pathSeparator+"backup");
		
		if (!srcDir.exists())
		{
			Bukkit.getLogger().warning("Backup does not exist!");
		}
		
		File destDir = new File (root+File.pathSeparator+"skyworld");
		
		try {
			FileUtils.copyDirectory(srcDir, destDir);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		Bukkit.getServer().createWorld(new WorldCreator(playerUUID));
		
		return false;
	}
}
