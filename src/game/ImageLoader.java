package game;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	private Map<String, Image> imageBank;	
	private static ImageLoader instance;
	
	private ImageLoader()
	{
		imageBank = new TreeMap<String, Image>();
	}
	
	static ImageLoader getLoader() {
		if(instance == null)
			instance = new ImageLoader();
		return instance;	
	}
	
	public Image getImage(String pic) {
		Image loaded = null;
		if(imageBank.containsKey(pic))	
			return imageBank.get(pic);
		else {
			try {
				ClassLoader myLoader = this.getClass().getClassLoader();
				InputStream imageStream = myLoader.getResourceAsStream(pic);
				loaded = ImageIO.read(imageStream);	
			} catch (IOException e) {System.out.println ("Could not load: " + e);}
			
			imageBank.put(pic, loaded);
			return loaded;
		}
	}
}
