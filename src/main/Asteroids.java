package main;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

import model.Facade;
import model.IFacade;

@SuppressWarnings ("serial")
public class Asteroids <World, Ship, Asteroid, Bullet> extends JFrame
{

	private AsteroidsMenu <World, Ship, Asteroid, Bullet>	menu;
	private WorldView <World, Ship, Asteroid, Bullet>		view;
	private IFacade <World, Ship, Asteroid, Bullet>			facade;
	private int												width;
	private int												height;
	private Sound											sound;

	public Asteroids (IFacade <World, Ship, Asteroid, Bullet> facade, int width, int height, boolean undecorated, Sound sound)
	{
		super("Asteroids");
		this.sound = sound;
		this.width = width;
		this.height = height;
		menu = new AsteroidsMenu <World, Ship, Asteroid, Bullet>(this);
		this.facade = facade;
		setUndecorated(undecorated);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		System.out.print("error");
		getContentPane().add(menu);
		pack();
	}

	public int getWidth ()
	{
		return width;
	}

	public int getHeight ()
	{
		return height;
	}

	public Sound getSound ()
	{
		return sound;
	}

	public IFacade <World, Ship, Asteroid, Bullet> getFacade ()
	{
		return facade;
	}

	public void start ()
	{
		menu.reset();
		sound.start();
		setVisible(true);
		menu.requestFocusInWindow();
	}

	public void startSinglePlayerGame ()
	{
		World world = facade.createWorld(width, height);
		Ship player = facade.createShip(50, 50, 0, 0, 40, 0, 5E15);
		facade.addShip(world, player);
		Asteroid asteroid1 = facade.createAsteroid(60, height / 2., 200, 0, 20);
		facade.addAsteroid(world, asteroid1);
		Asteroid asteroid2 = facade.createAsteroid(width / 2. - 50, height / 2., 0, 500, 150);
		facade.addAsteroid(world, asteroid2);
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				Asteroid x = facade.createAsteroid(750 + 15 * i, 850 + 15 * j, 0, 0, 5);
				facade.addAsteroid(world, x);
			}
		}
		Asteroid x = facade.createAsteroid(700, 800, 500, 500, 5);
		facade.addAsteroid(world, x);
		view = new WorldView <World, Ship, Asteroid, Bullet>(this, world, player, null);
		if (!isUndecorated()) view.setPreferredSize(new Dimension(width, height));
		getContentPane().remove(menu);
		getContentPane().add(view);
		revalidate();
		repaint();
		view.requestFocusInWindow();
		view.startGame();
	}

	public void startMultiPlayerGame ()
	{
		World world = facade.createWorld(width, height);
		Ship player1 = facade.createShip(width / 5 * 4, height / 2., 0, 0, 40, Math.PI, 5E15);
		facade.addShip(world, player1);
		Ship player2 = facade.createShip(width / 5, height / 2., 0, 0, 40, 0, 5E15);
		facade.addShip(world, player2);
		Asteroid asteroid1 = facade.createAsteroid(width / 2, height / 2, 25, 50, 75);
		facade.addAsteroid(world, asteroid1);
		Asteroid asteroid2 = facade.createAsteroid(600, 100, -30, -40, 40);
		facade.addAsteroid(world, asteroid2);
		Asteroid asteroid3 = facade.createAsteroid(990, 550, -20, -3, 25);
		facade.addAsteroid(world, asteroid3);
		Asteroid asteroid4 = facade.createAsteroid(40, height - 100, 10, -8, 15);
		facade.addAsteroid(world, asteroid4);
		view = new WorldView <World, Ship, Asteroid, Bullet>(this, world, player1, player2);
		if (!isUndecorated()) view.setPreferredSize(new Dimension(width, height));
		getContentPane().remove(menu);
		getContentPane().add(view);
		revalidate();
		repaint();
		view.requestFocusInWindow();
		view.startGame();
	}

	public void showMenu ()
	{
		if (view != null)
		{
			getContentPane().remove(view);
			view = null;
		}
		menu.reset();
		getContentPane().add(menu);
		revalidate();
		repaint();
		menu.requestFocusInWindow();
		pack();
		menu.repaint();
	}

	public static void main (final String[] args)
	{
		boolean tryFullscreen = true;
		boolean enableSound = true;
		for (String arg : args)
		{
			if (arg.equals("-window"))
			{
				tryFullscreen = false;
			} else if (arg.equals("-nosound"))
			{
				enableSound = false;
			} else
			{
				System.out.println("unknown option: " + arg);
				return;
			}
		}
		if (args.length > 0 && args[0].equals("-window"))
		{
			tryFullscreen = false;
		}
		if (GraphicsEnvironment.isHeadless())
		{
			System.out.println("no screen found");
			return;
		}
		// <begin>
		@SuppressWarnings ("unchecked")
		IFacade <world.World, world.entity.ship.Ship, world.entity.Asteroid, world.entity.Bullet> facade = new Facade();
		// <end>
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice screen = env.getDefaultScreenDevice();
		Asteroids <world.World, world.entity.ship.Ship, world.entity.Asteroid, world.entity.Bullet> asteroids;
		Sound sound = enableSound ? new FileSoundManager("resources/sounds.txt") : new NullSound();
		if (tryFullscreen && screen.isFullScreenSupported())
		{
			Rectangle dimensions = screen.getDefaultConfiguration().getBounds();
			asteroids = new Asteroids <world.World, world.entity.ship.Ship, world.entity.Asteroid, world.entity.Bullet>(facade, dimensions.width, dimensions.height, true, sound);
			screen.setFullScreenWindow(asteroids);
		} else
		{
			asteroids = new Asteroids <world.World, world.entity.ship.Ship, world.entity.Asteroid, world.entity.Bullet>(facade, 1024, 768, false, sound);
		}
		asteroids.start();
	}
}
