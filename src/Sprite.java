import java.awt.*;

public class Sprite {
	int x;
	int y;

	double vx = 0;
	double vy = 0;

	double ax = 0;
	double ay = 0;

	public static final double GRAVITY = 1;

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
	
	public Sprite(int x, int y,int w, int h, int action, String[] name, int duration, int count, String ext) {
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

	public void jumpUp(int dy){

		vy = -dy;

		action = UP;

		moving = true;
	}

	public void stops() {
		vx = 0;
		vy = 0;
		ay = 0;
	}

	public void applyGravity()
	{
		ay = GRAVITY;
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
			g.drawRect((int)x - Camera.x + Camera.x_origin,(int)y - Camera.y + Camera.y_origin,32,32);
			g.drawImage(animation[action].getCurrentImage(), (int) x - Camera.x + Camera.x_origin,
					(int) y - Camera.y + Camera.y_origin, 32, 32,
					null);

		} else {
			g.drawRect((int)x - Camera.x + Camera.x_origin,(int)y - Camera.y + Camera.y_origin,32,32);
			g.drawImage(animation[action].getStillImage(), (int) x - Camera.x + Camera.x_origin,
					(int) y - Camera.y + Camera.y_origin,	32, 32,
					null);
		}
		
		moving = false;
	}
}