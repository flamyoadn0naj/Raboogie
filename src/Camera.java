
public class Camera {

	public static int x = 0;
	public static int y = 0;

	public static int vx;
	public static int vy;

	public static int ay;

	public static int x_origin = 900;
	public static int y_origin = 440;

	public static final int GRAVITY = 1;

	public static void moveLeft(int dx)
	{
		x -= dx;
		vx = -dx;
	}

	public static void moveRight(int dx)
	{
		x += dx;
		vx = dx;
	}

	public static void moveUp(int dy)
	{
		y -= dy;
		vy = -dy;
	}

	public static void moveDown(int dy)
	{
		y += dy;
		vy = dy;
	}

	//------------------------------------------------------------------------//

	public static void update()
	{
		vy += ay;

		x += vx;
		y += vy;
	}

	//------------------------------------------------------------------------//

	public static void stops()
	{
		vx = 0;
		vy = 0;
		ay = 0;
	}

	//------------------------------------------------------------------------//

	public static void jump(int dy)
	{
		vy = -dy;
	}
	//------------------------------------------------------------------------//

	public static void applyGravity()
	{
		ay = GRAVITY;
	}
}
