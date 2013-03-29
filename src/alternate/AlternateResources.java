package alternate;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class AlternateResources
{
	public static boolean loadResources ()
	{
		try
		{
			File res = new File("AlternateResources");
			String[] imgs = res.list();
			for (String imgname : imgs)
			{
				BufferedImage img = ImageIO.read(new File(res, imgname));
				imgname = imgname.split("\\.")[0];
				images.put(imgname, img);
			}
			return true;
		} catch (IOException e)
		{
			return false;
		}
	}

	public static BufferedImage getImage (String name)
	{
		return images.get(name);
	}

	private static HashMap <String, BufferedImage>	images	= new HashMap <String, BufferedImage>();
}
