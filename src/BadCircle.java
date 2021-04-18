
public class BadCircle extends Circle
{
	public BadCircle(double x, double y, double r, int A)
	{
		super(x, y, r, A);
	}
	
	public void turnToward(Circle c)
	{		
		if(toLeftOf(c))  turnLeft(2);
		
		else             turnRight(2);		
	}
	
	
	public void chase(Circle c)
	{
		turnToward(c);
		
		goForward(3);
	}

}
