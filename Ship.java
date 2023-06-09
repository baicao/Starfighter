//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing
{
	private int speed;
	private Image image;

	public Ship()
	{
		this(10,10,90,90,10);
	}

	public Ship(int x, int y)
	{
		super(x, y);
	   //add code here
	}


	public Ship(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed=s;
		try
		{
			URL url = getClass().getResource("/ship.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.print("ERROR");
			//feel free to do something here
		}
	}


	public void setSpeed(int s)
	{
		speed = s;
	   //add more code
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void move(String direction)
	{
		if(direction.equals("LEFT")){
			super.setX(super.getX()-speed);
		}
		if(direction.equals("RIGHT")){
			super.setX(super.getX()+speed);
		}
		if(direction.equals("UP")){
			super.setY(super.getY()-speed);
		}
		if(direction.equals("DOWN")){
			super.setY(super.getY()+speed);
		}
		//add code here
	}

	public void draw( Graphics window )
	{

		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString()
	{
		return super.toString() + getSpeed();
	}
}
