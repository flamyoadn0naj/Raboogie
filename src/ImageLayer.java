import java.awt.*;

public class ImageLayer
{
	double x;
	double y;
	double z;
	
	int width;
	
	Image image;
	
	
	public ImageLayer(String name, double x, double y, double z, int width)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.width = width;
		
		image = Toolkit.getDefaultToolkit().getImage(name);		
	}
	
	public void draw(Graphics g)
	{
		for(int i = 0 ; i < 20; i++)
		
			g.drawImage(image, (int)(x + width*i - Camera.x/z), (int)(y - Camera.y/z), null);
	}

}
