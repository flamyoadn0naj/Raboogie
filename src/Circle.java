import java.awt.*;

public class Circle
{
   //------------------------------------------------------------------------//

	double px;
	double py;
	
	double vx = 0;
	double vy = 0;
	
	double ax = 0;
	double ay = 0;
	
	double r;
	
	int    A;

	double cosA;
	double sinA;
	
	
	boolean held = false;
	
   //------------------------------------------------------------------------//

	public Circle(double px, double py, double r, int A)
	{
		this.px = px;
		this.py = py;
		
		this.r  = r; 
		
		this.A  = A;
		
		cosA    = Lookup.cos[A];
		sinA    = Lookup.sin[A];
	}
	
   //------------------------------------------------------------------------//

	public boolean toLeftOf(Circle c)
	{
		return sinA * (c.px - px) - cosA * (c.py - py) > 0;	
	}
	
   //------------------------------------------------------------------------//

	public boolean inFrontOf(Circle c)
	{
		return cosA * (c.px - px) + sinA * (c.py - py) > 0;	
	}
	
	//------------------------------------------------------------------------//

   public boolean within(double distance, Circle c)
   {
      double dx = px - c.px;
      double dy = py - c.py;
      
      return dx * dx + dy * dy < distance * distance;     
   }
   
	//------------------------------------------------------------------------//

	public void draw(Graphics g)
	{
		g.drawOval((int)(px-r), (int)(py-r), (int)(2.0*r), (int)(2.0*r));
		
		g.drawLine((int)px, (int)py, (int)(px + r*cosA), (int)(py + r*sinA));
	}
	
   //------------------------------------------------------------------------//

	public void move()
	{
		vx += ax;           // Accelerate
		vy += ay;
		
		px += vx;           // Move
		py += vy;
	}
	
	//------------------------------------------------------------------------//

	public void setVelocity(double vx, double vy)
	{
		this.vx = vx;
		this.vy = vy;
	}
	
	//------------------------------------------------------------------------//

	public void setAcceleration(double ax, double ay)
	{
		this.ax = ax;
		this.ay = ay;
	}

	
	//------------------------------------------------------------------------//
	
	public void jump(double d)
	{
		setVelocity(0, -d);
	}
	
	//------------------------------------------------------------------------//

	public void toss(double vx, double vy)
	{
		setVelocity(vx, vy);
	}
	//------------------------------------------------------------------------//
	
	public void moveBy(double dx, double dy)
	{
		px += dx;
		py += dy;
	}
	
   //------------------------------------------------------------------------//

	public void goForward(double d)
	{
		double dx = d * cosA;
		double dy = d * sinA;
		
		moveBy(dx, dy);
	}
	
   //------------------------------------------------------------------------//

	public void goBackward(double d)
	{
		double dx = -d * cosA;
		double dy = -d * sinA;
		
		moveBy(dx, dy);
	}
	
   //------------------------------------------------------------------------//

	public void turnLeft(int dA)
	{
		A -= dA;
		
		if(A < 0)   A += 360;
		
		cosA = Lookup.cos[A];
		sinA = Lookup.sin[A];
	}

   //------------------------------------------------------------------------//

	public void turnRight(int dA)
	{
		A += dA;
		
		if(A > 359) A -= 360;
		
		cosA = Lookup.cos[A];
		sinA = Lookup.sin[A];
	}
	
   //------------------------------------------------------------------------//
	
	public boolean overlaps(Line L)
	{
	   double d = L.distanceTo(px, py);
	   
	   return d < r;
	}
	
	//------------------------------------------------------------------------//

	public void isPushedBackBy(Line L)
	{
	   double d = L.distanceTo(px, py);

	   double p = r - d;
	   
	   //px += p * L.Nx;
	   //py += p * L.Ny;
	   
	   moveBy(p*L.Nx, p*L.Ny);
	}
	
	//------------------------------------------------------------------------//
	
	public boolean overlaps(Circle c)
	{
		double dx = px - c.px;
		double dy = py - c.py;
		
		double d2 = dx*dx + dy*dy;
		
		double ri = r + c.r;
		
		return d2 <= ri*ri;
	}
	
   //------------------------------------------------------------------------//

	public void pushes(Circle c)
	{
		double dx = px - c.px;      // <dx, dy>  // vector in the direction from center of one circle to the other
		double dy = py - c.py;                 // with a magnitude equal to the distance between the two circles
		
		double d = Math.sqrt(dx*dx + dy*dy);    
		
		double ux = dx / d;      //<dx/d, dy/d>// unit vector in the direction from center of one circle to the other
		double uy = dy / d;
		
		double ri = r + c.r;
		
		double p = ri - d;
		
		px += ux * p/2;
		py += uy * p/2;
		
		c.px -= ux * p/2;
		c.py -= uy * p/2;			
	}
	
	//------------------------------------------------------------------------//

	public void bounceOff(Circle c)
	{
		double dx = c.px - px;
		double dy = c.py - py;
		
		//double mag = Math.sqrt(dx*dx + dy*dy);
		double mag = r + c.r;
		
		double ux = dx / mag;
		double uy = dy / mag;
		
		double tx = -uy;
		double ty =  ux;		
		
		double u = vx*ux + vy*uy;
		double t = vx*tx + vy*ty;
		
		double cu = c.vx*ux + c.vy*uy;
		double ct = c.vx*tx + c.vy*ty;
				
		vx   = .9*(t * tx + cu * ux);
		vy   = .9*(t * ty + cu * uy);
		
		c.vx = .9*(ct * tx + u * ux); 
		c.vy = .9*(ct * ty + u * uy);		
	}
	
	//------------------------------------------------------------------------//

	public void bounceOff(Line L)
	{
	   double d = L.distanceTo(px, py);

	   double p = r - d;
	   
	   px += 1.9*(p * L.Nx);
	   py += 1.9*(p * L.Ny);
	   	   
	   double mag  = 1.9 * (vx * L.Nx + vy * L.Ny);
	   
	   double tx = mag *L.Nx;
	   double ty = mag *L.Ny;
	   
	   vx -= tx;
	   vy -= ty;
	}
	
	//------------------------------------------------------------------------//

	public void grabbedAt(int x, int y)
	{
		double dx = px - x;
		double dy = py - y;
		
		double d2 = dx*dx + dy*dy;
		
		double r2 = r * r;
		
		held = d2 < r2;
	}
	
	//------------------------------------------------------------------------//

	public void released()
	{
	   held = false;	
	}
	
	//------------------------------------------------------------------------//
	
}
