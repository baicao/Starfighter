//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.net.URL;

public class Ammo extends MovingThing
{
	private int speed;
	private Image imageBullet;

	public Ammo()
	{
		this(0,0,0);
	}


	public Ammo(int x, int y, int s)
	{
		super(x,y,5,25);
		speed =s;
		try
		{
			URL url = getClass().getResource("/mdi_bullet.png");
			imageBullet = ImageIO.read(url);
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

	public void draw( Graphics window )
	{
		window.drawImage(imageBullet,getX(),getY(),getWidth(),getHeight(),null);
		
		//add code to draw the ammo
	}
	
	
	public void move( String direction )
	{
		super.setY(super.getY()-speed);

		//add code to draw the ammo
	}

	public String toString()
	{
		return super.toString()+getSpeed();
	}
}
