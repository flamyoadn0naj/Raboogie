import java.awt.*;

public class Sprite {
	double x;
	double y;

	double vx = 0;
	double vy = 0;

	double ax = 0;
	double ay = 0;

	public static final double GRAVITY = .4;

	int w;
	int h;
	
	int action;
	
	boolean moving = false;
	
	public static final int UP    = 0;
	public static final int DOWN  = 1;
	public static final int LEFT  = 2;
	public static final int RIGHT = 3;
	public static final int JUMP  = 4;
	
	
	Animation[] animation;
	
	public Sprite(double x, double y,int w, int h, int action, String[] name, int duration, int count, String ext) {
		// where the sprite is
		this.x = x;
		this.y = y;

		this.h = h;
		this.w = w;

		// what sprite is currently doing. which way its facing etc. (starting image)
		this.action = action;
		
		animation = new Animation[name.length]; 
		
		for(int i = 0; i < name.length; i++) {

			animation[i] = new Animation(name[i], duration, count, ext);
		}
	}
	
	public void moveBy(double dx) {
		vx += dx;
	}
	
	public void moveUp(double dy) {
		y -= dy;
		
		action = UP;
		
		moving = true;
	}
	
	public void moveDown(double dy) {
		y += dy;
		
		action = DOWN;
		
		moving = true;
	}
	
	public void moveLeft(double dx) {
		x -= dx;
		
		action = LEFT;
		
		moving = true;
	}
	
	public void moveRight(double dx) {
		x += dx;
		
		action = RIGHT;
		
		moving = true;
	}

	public void update() {
		vy += ay;

		x += vx;
		y += vy;
	}

	public void jumpUp(double d){

		vy += -1;
		ay = GRAVITY;

		action = UP;

		moving = true;
	}

	public void setVelocity(double vx, double vy)
	{
		this.vx = vx;
		this.vy = vy;
	}

	public boolean overlaps(Rect r)
	{
		return (r.x + r.w >=   x)  &&
				(  x +   w >= r.x)  &&
				(r.y + r.h >=   y)  &&
				(  y +   h >= r.y);
	}

	//------------------------------------------------------------------------//

	public boolean contains(int mx, int my)
	{
		return (mx >= x  ) &&
				(mx <= x+w) &&
				(my >= y  ) &&
				(my <= y+h);
	}
	
		
	public void draw(Graphics g) {
		if(moving) {
			g.drawRect((int)x - Camera.x,(int)y - Camera.y,50,75);
			g.drawImage(animation[action].getCurrentImage(), (int) x - Camera.x, (int) y - Camera.y, 75, 75, null);

		} else {
			g.drawRect((int)x - Camera.x,(int)y - Camera.y,50,75);
			g.drawImage(animation[action].getStillImage(), (int) x - Camera.x, (int) y - Camera.y, 75, 75, null);
		}
		
		moving = false;
	}
}