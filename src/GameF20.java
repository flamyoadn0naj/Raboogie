import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class GameF20 extends GameApplet
{
	Image dino;
   //------------------------------------------------------------------------//

	Soldier    soldier    = new Soldier(150, 150, Soldier.DOWN);
//	BattleLord battlelord = new BattleLord(200, 500, BattleLord.LEFT);
	
	BadCircle[] bc = new BadCircle[3];
	
	Circle c = new Circle(100, 100, 30, 90);
		
	Line[] L = new Line[3];
	
	
	Random rnd = new Random(System.currentTimeMillis());

	
   //------------------------------------------------------------------------//
	
	public void init()
	{
		dino = Toolkit.getDefaultToolkit().getImage("Resources Root/Dino/Jump (1).png");
		double gravity = 0.7;
		
		for(int i = 0; i < bc.length; i++)
		{	
			bc[i] = new BadCircle(rnd.nextInt(1300)+50, rnd.nextInt(800)+50, 30, 0);
		   //bc[i].setAcceleration(0, gravity);
		}

		double[][] v = 
		{
		   {1500, 900,    0, 900},
		   {1450,   0, 1450, 900},
		   {  50, 900,   50,   0},
		};

		for(int i = 0; i < v.length; i++)
		{
	   	L[i] = new Line(v[i][0], v[i][1], v[i][2], v[i][3]);
		}
		
		super.init();
	}
	
   //------------------------------------------------------------------------//

	public void mousePressed(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		

		for(int i = 0; i < L.length; i++)
		
			L[i].grabbedAt(mx, my);
		
		
		for(int i = 0; i < bc.length; i++)
			
			bc[i].grabbedAt(mx, my);
	}
	
	//------------------------------------------------------------------------//

	public void mouseDragged(MouseEvent e)
	{
		int nx = e.getX();		
		int ny = e.getY();
		
		int dx = nx - mx;
		int dy = ny - my;
				
		mx = nx;
		my = ny;
				
		for(int i = 0; i < L.length; i++)
			
			L[i].draggedBy(dx, dy);
		
		for(int i = 0; i < bc.length; i++)
			
			if(bc[i].held)
			{	
				bc[i].moveBy(dx, dy);
				
				bc[i].ay = 0;
			}
		
		
		
	}
	
	//------------------------------------------------------------------------//
	
	public void mouseReleased(MouseEvent e)
	{
		for(int i = 0; i < L.length; i++)
			
		   L[i].released();

		for(int i = 0; i < L.length; i++)
		{
			
		   bc[i].released();
		   
		   bc[i].ay = 0.7;
		}
	}
	//------------------------------------------------------------------------//
	
	public void inGameLoop() {

		if(pressing[UP])  soldier.moveUp(5);
		if(pressing[DN])  soldier.moveDown(5);
		if(pressing[LT])  soldier.moveLeft(5);
		if(pressing[RT])  soldier.moveRight(5);
		if(pressing[RT])  c.toss(-8,10);
	}					
	
	//------------------------------------------------------------------------//

	/* g.drawImages(img, x location, y location.null) */
	public void paint(Graphics g) {
		//soldier.draw(g);
		c.draw(g);
		
		/*
		for(int i = 0; i < bc.length; i++)
		{
			bc[i].draw(g);
		}
		
		
		g.setColor(Color.RED);
		c.draw(g);
		g.setColor(Color.BLACK);
		*/
		
		//g.drawString("" + c[0].distanceTo(c[1]), 10, 30);
		
		
		//g.drawLine((int)(c[0].px + 1000*c[0].cosA), (int)(c[0].py + 1000*c[0].sinA), (int)(c[0].px - 1000*c[0].cosA), (int)(c[0].py - 1000*c[0].sinA) );
		
		for(int i = 0; i < L.length; i++)
		{
   		L[i].draw(g);
		}
 	}
	
	//------------------------------------------------------------------------//

}

