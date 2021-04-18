import java.awt.*;

public class Animation
{
	private Image[] image;
	private int current = 0; // current image.
	
	private int delay;
	private int duration;	// duration of how long each image stay on the screen per sec.

	/* Load images. Name of the image, duration the image on screen, number of images to load in, images type. */
	public Animation(String name, int duration, int count, String ext)
	{
		image = new Image[count]; 
		
		
		for(int i = 0; i < count; i++)
			
		   // image[i] = Toolkit.getDefaultToolkit().getImage(name + "_" + i + "." + ext);

			// For Robot
			image[i] = Toolkit.getDefaultToolkit().getImage(name + " (" + i +")"+ "." + ext);
		
		this.duration = duration;
		
		delay = duration;
	}
	
	/* Get the image that should be placed on the screen*/
	public Image getCurrentImage()
	{
		if(delay == 0)
		{
			current++;
			
		   if(current == image.length)   current = 1;
		   
		   delay = duration;
		}
		
		delay--;
		
		return image[current];
	}
	
	
	
	public Image getStillImage()
	{
		return image[0];
	}
	
	

}
