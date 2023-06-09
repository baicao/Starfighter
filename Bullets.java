

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Bullets
{
	private List<Ammo> ammo;

	public Bullets()
	{
		ammo = new ArrayList<>();
	}

	public void add(Ammo al)
	{
		ammo.add(al);
	}

	//post - draw each Ammo
	public void drawEmAll( Graphics window )
	{
		for(int i = 0; i<ammo.size();i++){
			ammo.get(i).draw(window);
		}
	}

	public void moveEmAll()
	{
		for(int i=0; i<ammo.size();i++){
			if(ammo.get(i).getY()>555){
				ammo.remove(i);
				i--;
			}

		}
	}

	public  void  remove(Ammo al)
	{
		ammo.remove(al);
	}

	public void cleanEmUp()
	{
		for(int i = 0; i<ammo.size(); i++){
			if(ammo.get(i).getY()<5){
				
			}
		}
	}

	public List<Ammo> getList()
	{
		return ammo;
	}

	public String toString()
	{
		return super.toString();
	}
}
