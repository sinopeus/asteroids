package alternate;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.FileSoundManager;
import main.Sound;
import model.Facade;
import model.IFacade;
import world.World;
import world.entity.Asteroid;
import world.entity.Bullet;
import world.entity.ship.Ship;

public class AlternateAsteroids extends JFrame
{
	private IFacade <World, Ship, Asteroid, Bullet>					facade;
	private CardLayout												layout;
	private Sound													sound;

	private AlternateAsteroidsMenu <World, Ship, Asteroid, Bullet>	menu;
	private AlternateWorldView <World, Ship, Asteroid, Bullet>		game;
	private AlternateSettingsMenu									settings;

	public AlternateAsteroids (IFacade <World, Ship, Asteroid, Bullet> facade)
	{
		this.facade = facade;

		initializeSelf();
	}

	private void initializeSelf ()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screen.width, screen.height);
		setTitle("AlternateAsteroids");
		setUndecorated(true);

		initializeLayoutManager();
		initializeContentPane();
		intializeCards();
	}

	private void initializeLayoutManager ()
	{
		layout = new CardLayout();
	}

	private void initializeContentPane ()
	{
		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(layout);
		setContentPane(contentPane);
	}

	private void initializeSound ()
	{
		this.sound = new FileSoundManager("resources/sounds.txt");
	}

	private void intializeCards ()
	{
		initializeMenu();
		initializeView();
		intializeSettings();
	}

	private void initializeMenu ()
	{
		menu = new AlternateAsteroidsMenu <World, Ship, Asteroid, Bullet>();
		getContentPane().add(menu, "MENU");
	}

	private void initializeView ()
	{
		game = new AlternateWorldView <World, Ship, Asteroid, Bullet>();
		getContentPane().add(game, "GAME");
	}

	private void intializeSettings ()
	{
		settings = new AlternateSettingsMenu();
		getContentPane().add(settings, "SETTINGS");
	}

	public void start ()
	{
		setVisible(true);
	}

	public static void main (String[] args) throws InvocationTargetException, InterruptedException
	{
		AlternateResources.loadResources();
		EventQueue.invokeAndWait(new Runnable()
		{
			@Override
			public void run ()
			{
				IFacade <World, Ship, Asteroid, Bullet> facade = new Facade();
				AlternateAsteroids a = new AlternateAsteroids(facade);
				a.start();
			}
		});
	}

	//	public static void main (final String[] args)
	//	{
	//		//---------------------------ARGUMENT SHIT------------------------------
	//		boolean tryFullscreen = true;
	//		boolean enableSound = true;
	//		for (String arg : args)
	//		{
	//			if (arg.equals("-window"))
	//			{
	//				tryFullscreen = false;
	//			} else if (arg.equals("-nosound"))
	//			{
	//				enableSound = false;
	//			} else
	//			{
	//				System.out.println("unknown option: " + arg);
	//				return;
	//			}
	//		}
	//		if (args.length > 0 && args[0].equals("-window"))
	//		{
	//			tryFullscreen = false;
	//		}
	//		if (GraphicsEnvironment.isHeadless())
	//		{
	//			System.out.println("no screen found");
	//			return;
	//		}
	//		//---------------------------ARGUMENT SHIT------------------------------
	//
	//		IFacade <world.World, world.entity.ship.Ship, world.entity.Asteroid, world.entity.Bullet> facade = new Facade();
	//
	//		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	//		GraphicsDevice screen = env.getDefaultScreenDevice();
	//
	//		AlternateAsteroids asteroids;
	//		asteroids = new AlternateAsteroids(facade);
	//
	//		asteroids.start();
	//	}
}
