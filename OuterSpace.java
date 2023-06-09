//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private Alien alienOne;
	private Alien alienTwo;
	private Bullets Bullets;
	private List<Alien> alienList = new ArrayList<>();
	private Timer alienTimer ;
	int ATTACK_INTERVAL = 1000;
	static final int WIDTH =800;
	static final int HEIGHT = 600;
	static final int ALIEN_SIZE = 50;

	/* uncomment once you are ready for this part
	 *
   private AlienHorde horde;
	private Bullets shots;
	*/

	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace()
	{
		setBackground(Color.black);

		keys = new boolean[5];

		//instantiate other instance variables
		//Ship, Alien
		ship=new Ship(40,300,50,50,5);
		alienOne = new Alien(555,90,50,50,2);
		alienTwo = new Alien(225,70,50,50,2);

		this.addKeyListener(this);
		Bullets = new Bullets();
		new Thread(this).start();
		setAlienTimer();

		setVisible(true);
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,WIDTH,HEIGHT);
		ship.draw(graphToBack);
		alienOne.draw(graphToBack);
		alienTwo.draw(graphToBack);
//		Bullets.drawEmAll(graphToBack);
//		Bullets.moveEmAll();
		alienOne.move("RIGHT");
		alienTwo.move("RIGHT");




		if(keys[0] == true)
		{
			if( ship.getX()-ship.getSpeed() >= 0){
				ship.move("LEFT");
			}
		}
		if(keys[1] == true)
		{
			if( ship.getX()+ship.getSpeed() <= 800-50){
				ship.move("RIGHT");
			}
		}
		if(keys[2] == true)
		{
			if( ship.getY()-ship.getSpeed() >= 0){
				ship.move("UP");
			}
		}
		if(keys[3] == true)
		{
			if( ship.getY()+ship.getSpeed() < 600-50){
				ship.move("DOWN");
			}
		}
		if(keys[4] == true)
		{
			Ammo shot = new Ammo(ship.getX() + 24, ship.getY(), 5);
			Bullets.add(shot);
			keys[4]=false;
		}

		ArrayList<Alien> alienToDelete  = new ArrayList<Alien>();
		ArrayList<Ammo> ammoToDelete  = new ArrayList<Ammo>();
		for(Ammo shot : Bullets.getList()){
			shot.move(null);
			shot.draw(graphToBack);
			if(shot.getY()<=0)
			{
				ammoToDelete.add(shot);
			}
		}
		for (int i=0;i<alienList.size()-1;i++) {
			Alien alien = alienList.get(i);

			if( alien.getX() + alien.getSpeed() + ALIEN_SIZE > WIDTH){
				alienToDelete.add(alien);
				continue;
			}
			alien.move("RIGHT");
			alien.draw(graphToBack);
			for(int j=0;j<Bullets.getList().size();j++){
				Ammo shot = Bullets.getList().get(j);
				if(alien.getX()>=shot.getX() && alien.getX()<=shot.getX()+ALIEN_SIZE && alien.getY()>=shot.getY() && alien.getY()<=shot.getY()+ALIEN_SIZE ){
					ammoToDelete.add(shot);
					alienToDelete.add(alien);
					break;
				}
			}
		}



		for(Ammo shot :ammoToDelete){
			Bullets.getList().remove(shot);
		}
		for(Alien alien :alienToDelete){
			alienList.remove(alien);
		}

		//add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship


		twoDGraph.drawImage(back, null, 0, 0);
	}


	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{
      //no code needed here
	}

	public void createMoreAlien(){

		int y = (int)(Math.random()*(getHeight()-200));
		Alien alien = new Alien(0,y,50,50,2);
		alienList.add(alien);
	}

	public void setAlienTimer(){
		if (alienTimer == null ){
			Timer alienTimer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					createMoreAlien();
				}
			};
			alienTimer.scheduleAtFixedRate(task, 0, ATTACK_INTERVAL);
		}
	}

	/**
	 * Creates more Aliens
	 */
	private class TimerHandler implements ActionListener{
		public void actionPerformed(ActionEvent actionEvent){
			createMoreAlien();
		}
	}


   public void run() {
   	try {
		Date start = new Date();
		while(true)
		{
			   Thread.currentThread().sleep(5);
			   repaint();
		 }
	  }catch(Exception e){
	  }
  	}
}

