package trolio.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import trolio.interfaces.CommandInterface;

public class CreateIsland extends JavaPlugin implements CommandInterface
{
	
	private static void copyFileStructure(File source, File target)
	{
		try
		{
			ArrayList ignore = new ArrayList<>(Arrays.asList("uid.dat", "session.lock"));
			
			if (!ignore.contains(source.getName()))
			{
				if (source.isDirectory())
				{
					if (!target.exists())
						if (!target.mkdirs())
							throw new IOException("Could not create world directory");
					
					String files[] = source.list();
					
					for (String file : files)
					{
						File srcFile = new File (source, file);
						File destFile = new File (target, file);
						copyFileStructure(srcFile, destFile);
					}
				}
				else
				{
					InputStream in = new FileInputStream(source);
					OutputStream out = new FileOutputStream(target);
					byte[] buffer = new byte[1024];
					int length;
					
					while ((length = in.read(buffer)) > 0)
						out.write(buffer, 0, length);
					in.close();
					out.close();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args)
	{
		return false;
	}
}
