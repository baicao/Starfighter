//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import javax.swing.JFrame;
import java.awt.*;

public class StarFighter extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public StarFighter()
	{
	 	super("STARFIGHTER");
		setSize(WIDTH,HEIGHT);

		Container pane = getContentPane();
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		pane.setBackground(Color.BLACK);
		OuterSpace theGame = new OuterSpace();
		((Component)theGame).setFocusable(true);
		theGame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pane.add(theGame);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(theGame.getHeight());

	}

	public static void main( String args[] )
	{
		StarFighter run = new StarFighter();
	}
}
