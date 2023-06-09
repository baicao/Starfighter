//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing
{
	private int speed;
	private Image image;

	public Alien()
	{
		this(0,0,30,30,0);
	}

	public Alien(int x, int y)
	{
		super.setX(x);
		super.setY(y);
		
		//add code here
	}

	public Alien(int x, int y, int s)
	{
		super.setX(x);
		super.setY(y);
		speed = s;
		//add code here
	}

	public Alien(int x, int y, int w, int h, int s)
	{
		super(x, y, w,h);
		speed=s;
		try
		{
			URL url = getClass().getResource("/alien.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			//feel free to do something here
		}
	}

	public void setSpeed(int s)
	{
		speed = s;
	   //add code
	}

	public int getSpeed()
	{
	   return speed;
	}

   public void move(String direction)
	{
		if(direction.equals("LEFT")){
			super.setX(super.getX()-speed);
		}else if(direction.equals("RIGHT")){
			super.setX(super.getX()+speed);
		}

	}
	
	   //add code here

	public void draw( Graphics window )
	{
   	window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString()
	{
		return super.toString()+getSpeed();
	}
}
