package alternate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.CollisionListener;

public class AlternateWorldView <World, Ship, Asteroid, Bullet> extends JPanel implements CollisionListener
{
	private static final long	serialVersionUID	= 1L;
	
	public AlternateAsteroids	game;

	private BorderLayout		layout;
	private Box					labelBox;
	private JLabel				title;
	private JLabel[]			labels;

	private int					selectedIndex;
	private static final int	options				= 4;

	public AlternateWorldView ()
	{
		initializeSelf();
		initializeKeyBindings();
	}

	private void initializeSelf ()
	{
		
	}

	private void initializeKeyBindings ()
	{

	}

	@Override
	public void boundaryCollision (Object entity, double x, double y)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void objectCollision (Object entity1, Object entity2, double x, double y)
	{
		// TODO Auto-generated method stub

	}
}
