package alternate;

import static java.lang.Math.sin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import main.CollisionListener;
import main.Drawable;
import main.Explosion;
import model.IFacade;
import world.World;
import world.entity.Asteroid;
import world.entity.Bullet;
import world.entity.ship.Ship;

@SuppressWarnings ("javadoc")
public class AlternateWorldView extends JPanel implements CollisionListener
{
	private static final long																			serialVersionUID	= 1L;

	public AlternateAsteroids																			game;
	public IFacade <world.World, world.entity.ship.Ship, world.entity.Asteroid, world.entity.Bullet>	facade;
	private World																						world;

	private boolean																						hasStarted;

	private int																							width,
			height;
	private BorderLayout																				layout;
	private Box																							labelBox;
	private JLabel																						title;
	private JLabel[]																					labels;

	private ArrayList <AlternateShipControl>															players;

	private String																						msg					= null;
	private Map <Object, Visualization <?>>																visualizations		= new HashMap <Object, Visualization <?>>();
	private Set <Explosion>																				explosions			= new HashSet <Explosion>();

	private Timer																						timer;
	private long																						timeAfterLastEvolve;

	private boolean																						debug;

	public AlternateWorldView (IFacade <world.World, world.entity.ship.Ship, world.entity.Asteroid, world.entity.Bullet> facade, int width, int height)
	{
		this.facade = facade;
		this.width = width;
		this.height = height;
		initializeSelf();
	}

	public void reset ()
	{
		initializeSelf();
	}

	private void initializeSelf ()
	{
		debug = false;
		players = new ArrayList <AlternateShipControl>();
		initializeTimer();
	}

	private void initializeTimer ()
	{
		timer = new Timer(1000 / 30, new ActionListener()
		{
			@Override
			public void actionPerformed (ActionEvent e)
			{
				AlternateShipControl player1 = players.get(0);
				AlternateShipControl player2 = null;
				if (players.size() == 2)
				{
					player2 = players.get(1);
				}

				long now = System.currentTimeMillis();
				long millisSinceLastEvolve = now - timeAfterLastEvolve;
				timeAfterLastEvolve = now;
				facade.turn(player1.getShip(), player1.getAddingAngle());
				if (player2 != null && player2.getAddingAngle() != 0)
				{
					facade.turn(player2.getShip(), player2.getAddingAngle());
				}
				if (player1.isFiring() && facade.getShips(world).contains(player1.getShip()))
				{
					player1.setFiring(false);
					facade.fireBullet(player1.getShip());
					//					game.getSound().play("torpedo");
				}
				if (player2 != null && player2.isFiring() && facade.getShips(world).contains(player2.getShip()))
				{
					player2.setFiring(false);
					facade.fireBullet(player2.getShip());
					//					game.getSound().play("torpedo");
				}
				facade.evolve(world, millisSinceLastEvolve / 1000., AlternateWorldView.this);
				Iterator <Explosion> iter = explosions.iterator();
				while (iter.hasNext())
				{
					boolean done = iter.next().evolve(millisSinceLastEvolve / 1000.);
					if (done) iter.remove();
				}
				boolean player1Alive = facade.getShips(world).contains(player1.getShip());
				if (player1Alive)
				{
					if (player2 != null && !facade.getShips(world).contains(player2.getShip()))
					{
						timer.stop();
						msg = "Player 1 wins!";
					} else if (facade.getAsteroids(world).isEmpty() && player2 == null)
					{
						timer.stop();
						msg = "You win!";
					}
				} else
				{
					timer.stop();
					if (player2 == null || !facade.getShips(world).contains(player2.getShip()))
					{
						msg = "Asteroids win!";
					} else
					{
						msg = "Player 2 wins!";
					}
				}
				repaint();
			}
		});
	}

	// ------------------KEY BINDINGS----------------------

	private void initializeKeyBindings ()
	{
		getInputMap().clear();
		getActionMap().clear();

		AlternateShipControl player1 = null;
		if (players.size() >= 1)
		{
			player1 = players.get(0);
		}
		AlternateShipControl player2 = null;
		if (players.size() >= 2)
		{
			player2 = players.get(1);
		}

		this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "CLOSE");
		this.getActionMap().put("CLOSE", new Close());

		//CONTROLLS FOR PLAYER 1
		if (player1 != null)
		{
			this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "THRUSTONP1");
			this.getActionMap().put("THRUSTONP1", new ThrustOn(player1.getShip()));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("released UP"), "THRUSTOFFP1");
			this.getActionMap().put("THRUSTOFFP1", new ThrustOff(player1.getShip()));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "RIGHTP1");
			this.getActionMap().put("RIGHTP1", new Right(player1));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "RIGHTOFFP1");
			this.getActionMap().put("RIGHTOFFP1", new TurningOff(player1));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "LEFTP1");
			this.getActionMap().put("LEFTP1", new Left(player1));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "LEFTOFFP1");
			this.getActionMap().put("LEFTOFFP1", new TurningOff(player1));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "FIREP1");
			this.getActionMap().put("FIREP1", new Fire(player1));
		}

		//CONTROLLS FOR PLAYER 2
		if (player2 != null)
		{
			this.getInputMap().put(KeyStroke.getKeyStroke("Z"), "THRUSTONP2");
			this.getActionMap().put("THRUSTONP2", new ThrustOn(player2.getShip()));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("released Z"), "THRUSTOFFP2");
			this.getActionMap().put("THRUSTOFFP2", new ThrustOff(player2.getShip()));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("D"), "RIGHTP2");
			this.getActionMap().put("RIGHTP2", new Right(player2));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("released D"), "RIGHTOFFP2");
			this.getActionMap().put("RIGHTOFFP2", new TurningOff(player2));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("Q"), "LEFTP2");
			this.getActionMap().put("LEFTP2", new Left(player2));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("released Q"), "LEFTOFFP2");
			this.getActionMap().put("LEFTOFFP2", new TurningOff(player2));
			
			this.getInputMap().put(KeyStroke.getKeyStroke("A"), "FIREP2");
			this.getActionMap().put("FIREP2", new Fire(player2));
		}
		
		this.getInputMap().put(KeyStroke.getKeyStroke("F3"), "DEBUG");
		this.getActionMap().put("DEBUG", new Debug());
	}

	private class Close extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;

		@Override
		public void actionPerformed (ActionEvent e)
		{
			timer.stop();
			msg = null;
			game.layout.show(game.getContentPane(), "MENU");
			game.menu.requestFocusInWindow();
		}
	}

	private class ThrustOn extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;
		private Ship				ship;

		public ThrustOn (Ship ship)
		{
			this.ship = ship;
		}

		@Override
		public void actionPerformed (ActionEvent e)
		{
			facade.setThrusterActive(ship, true);
		}
	}
	
	private class ThrustOff extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;
		private Ship				ship;

		public ThrustOff (Ship ship)
		{
			this.ship = ship;
		}

		@Override
		public void actionPerformed (ActionEvent e)
		{
			facade.setThrusterActive(ship, false);
		}
	}
	
	private class Left extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;
		private AlternateShipControl				shipcontrol;

		public Left (AlternateShipControl shipcontrol)
		{
			this.shipcontrol = shipcontrol;
		}

		@Override
		public void actionPerformed (ActionEvent e)
		{
			shipcontrol.setAddingAngle(Math.PI / 20);
		}
	}
	
	private class Right extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;
		private AlternateShipControl				shipcontrol;

		public Right (AlternateShipControl shipcontrol)
		{
			this.shipcontrol = shipcontrol;
		}

		@Override
		public void actionPerformed (ActionEvent e)
		{
			shipcontrol.setAddingAngle(-Math.PI / 20);
		}
	}
	
	private class TurningOff extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;
		private AlternateShipControl				shipcontrol;

		public TurningOff (AlternateShipControl shipcontrol)
		{
			this.shipcontrol = shipcontrol;
		}

		@Override
		public void actionPerformed (ActionEvent e)
		{
			shipcontrol.setAddingAngle(0);
		}
	}
	
	private class Fire extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;
		private AlternateShipControl				shipcontrol;

		public Fire (AlternateShipControl shipcontrol)
		{
			this.shipcontrol = shipcontrol;
		}

		@Override
		public void actionPerformed (ActionEvent e)
		{
			shipcontrol.setFiring(true);
		}
	}

	private class Debug extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;

		@Override
		public void actionPerformed (ActionEvent e)
		{
			debug = !debug;
		}
	}

	// ------------------KEY BINDINGS----------------------

	// ------------------GAME MODES----------------------

	public void startSinglePlayerGame ()
	{
		World world = facade.createWorld(width, height);
		this.world = world;
		Ship player = facade.createShip(width / 2., height / 2., 0, 0, 40, 0, 5E15);
		facade.addShip(world, player);
		Asteroid asteroid1 = facade.createAsteroid(200, 200, 25, 50, 20);
		facade.addAsteroid(world, asteroid1);
		Asteroid asteroid2 = facade.createAsteroid(600, 80, -30, -40, 80);
		facade.addAsteroid(world, asteroid2);

		int size1 = (int) (2 * facade.getShipRadius(player));
		Image image1 = AlternateResources.getImage("chrome").getScaledInstance(size1, size1, Image.SCALE_DEFAULT);
		visualizations.put(player, new ShipVisualization(Color.RED, player, image1));

		players.add(new AlternateShipControl(player));

		initializeKeyBindings();
		
		game.layout.show(game.getContentPane(), "GAME");
		requestFocusInWindow();
		startGame();
		hasStarted = true;
	}

	public void startMultiPlayerGame ()
	{
		World world = facade.createWorld(width, height);
		this.world = world;
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

		int size1 = (int) (2 * facade.getShipRadius(player1));
		Image image1 = AlternateResources.getImage("chrome").getScaledInstance(size1, size1, Image.SCALE_DEFAULT);
		visualizations.put(player1, new ShipVisualization(Color.RED, player1, image1));
		int size2 = (int) (2 * facade.getShipRadius(player2));
		Image image2 = AlternateResources.getImage("firefox").getScaledInstance(size2, size2, Image.SCALE_DEFAULT);
		visualizations.put(player2, new ShipVisualization(Color.GREEN, player2, image2));

		players.add(new AlternateShipControl(player1));
		players.add(new AlternateShipControl(player2));

		initializeKeyBindings();
		
		game.layout.show(game.getContentPane(), "GAME");
		requestFocusInWindow();
		startGame();
		hasStarted = true;
	}

	public void startGame ()
	{
		//		game.getSound().loop("game-theme");
		timeAfterLastEvolve = System.currentTimeMillis();
		timer.start();
	}

	// ------------------GAME MODES----------------------

	@Override
	protected void paintComponent (Graphics g)
	{
		if (hasStarted)
		{
			super.paintComponent(g);

			g.drawImage(AlternateResources.getImage("background"), 0, 0, getRootPane().getWidth(), getRootPane().getHeight(), null);

			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(Color.WHITE);

			//RENDER SHIPS
			for (Ship ship : facade.getShips(world))
			{
				if (!visualizations.containsKey(ship))
				{
					visualizations.put(ship, new ShipVisualization(Color.BLUE, ship, null));
				}
				visualizations.get(ship).draw(g2d);
			}

			//RENDER ASTEROIDS
			for (Asteroid asteroid : facade.getAsteroids(world))
			{
				if (!visualizations.containsKey(asteroid))
				{
					visualizations.put(asteroid, new AsteroidVisualization(asteroid));
				}
				visualizations.get(asteroid).draw(g2d);
			}

			//RENDER BULLETS
			for (Bullet bullet : facade.getBullets(world))
			{
				if (!visualizations.containsKey(bullet))
				{
					Ship ship = facade.getBulletSource(bullet);
					visualizations.put(bullet, new BulletVisualization(visualizations.get(ship).getColor(), bullet));
				}
				visualizations.get(bullet).draw(g2d);
			}

			//RENDER EXPLOSIONS
			for (Explosion explosion : explosions)
			{
				explosion.draw(g2d);
			}

			//RENDER MESSAGE
			if (msg != null)
			{
				g2d.setColor(Color.WHITE);
				g2d.setFont(g2d.getFont().deriveFont(40f));
				drawCenteredString(g2d, msg);
				g2d.setFont(g2d.getFont().deriveFont(20f));
				drawCenteredString(g2d, "Press ESC to continue ...", getHeight() / 3 * 2);
			}

			if (debug)
			{
				showDebugInfo(g2d);
			}
		}
	}

	private void drawCenteredString (Graphics2D g2d, String txt, int y)
	{
		int width = getWidth();
		Rectangle2D bounds = g2d.getFontMetrics().getStringBounds(txt, g2d);
		g2d.drawString(txt, width / 2 - (int) bounds.getCenterX(), y);
	}

	private void drawCenteredString (Graphics2D g2d, String txt)
	{
		int width = getWidth();
		int height = getHeight();
		Rectangle2D bounds = g2d.getFontMetrics().getStringBounds(txt, g2d);
		g2d.drawString(txt, width / 2 - (int) bounds.getCenterX(), height / 2 - (int) bounds.getCenterY());
	}

	private void showDebugInfo (Graphics2D g2d)
	{
		Rectangle2D bounds = g2d.getFontMetrics().getStringBounds("DEBUG", g2d);
		g2d.drawString("DEBUG", width / 2 - (int) bounds.getCenterX(), 50);
	}

	// ------------------COLLISIONLISTENER-----------------------

	@Override
	public void boundaryCollision (Object entity, double x, double y)
	{
		
	}

	@Override
	public void objectCollision (Object entity1, Object entity2, double x, double y)
	{
		if ((facade.isBullets(entity1) && !facade.isBullets(entity2)) || (facade.isBullets(entity2) && !facade.isBullets(entity1)))
		{
//			game.getSound().play("explosion");
			explosions.add(new Explosion(x, facade.getWorldHeight(world) - y));
		}
	}

	// ------------------COLLISIONLISTENER-----------------------

	// ------------------VISUALIZATIONS-----------------------

	public abstract class Visualization <T> implements Drawable
	{
		private final Color	color;
		private final T		object;
		private final Image	image;

		public Visualization (Color color, T object, Image image)
		{
			if (color == null) throw new IllegalArgumentException("color null");
			if (object == null) throw new IllegalArgumentException("object null");
			this.color = color;
			this.object = object;
			this.image = image;
		}

		public Color getColor ()
		{
			return color;
		}

		public T getObject ()
		{
			return object;
		}

		public Image getImage ()
		{
			return image;
		}
	}

	public class ShipVisualization extends Visualization <Ship>
	{

		public ShipVisualization (Color color, Ship ship, Image image)
		{
			super(color, ship, image);
		}

		@Override
		public void draw (Graphics2D g2d)
		{
			double radius = facade.getShipRadius(getObject());
			double angle = -facade.getShipDirection(getObject());
			double x = facade.getShipX(getObject());
			double y = facade.getWorldHeight(world) - facade.getShipY(getObject());
			g2d.setColor(getColor());
			if (getImage() == null)
			{
				g2d.drawOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
			} else
			{
				AffineTransform T = AffineTransform.getTranslateInstance(radius, radius);
				T.rotate(angle);
				T.translate(-radius, -radius);
				T.preConcatenate(AffineTransform.getTranslateInstance(x - radius, y - radius));
				g2d.drawImage(getImage(), T, null);
			}
			g2d.drawLine((int) x, (int) y, (int) (x + Math.cos(angle) * radius), (int) (y + sin(angle) * radius));
		}
	}

	public class AsteroidVisualization extends Visualization <Asteroid>
	{

		public AsteroidVisualization (IFacade <World, Ship, Asteroid, Bullet> facade, Asteroid asteroid, Image image)
		{
			super(Color.WHITE, asteroid, image);
		}

		public AsteroidVisualization (Asteroid asteroid)
		{
			this(facade, asteroid, AlternateResources.getImage("internetexplorer"));
		}

		@Override
		public void draw (Graphics2D g2d)
		{
			World world = facade.getAsteroidWorld(getObject());
			if (world != null)
			{
				double radius = facade.getAsteroidRadius(getObject());
				double x = facade.getAsteroidX(getObject());
				double y = facade.getWorldHeight(world) - facade.getAsteroidY(getObject());
				if (getImage() == null)
				{
					g2d.setColor(getColor());
					g2d.drawOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
				} else
				{
					AffineTransform T = AffineTransform.getScaleInstance(2 * radius / getImage().getWidth(null), 2 * radius / getImage().getHeight(null));
					T.preConcatenate(AffineTransform.getTranslateInstance(x - radius, y - radius));
					g2d.drawImage(this.getImage(), T, null);
				}
			}
		}
	}

	public class BulletVisualization extends Visualization <Bullet>
	{

		public BulletVisualization (Color color, Bullet bullet)
		{
			super(color, bullet, null);
		}

		@Override
		public void draw (Graphics2D g2d)
		{
			double radius = facade.getBulletRadius(getObject());
			double x = facade.getBulletX(getObject());
			double y = facade.getWorldHeight(world) - facade.getBulletY(getObject());
			g2d.setColor(getColor());
			g2d.drawOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
		}
	}

	// ------------------VISUALIZATIONS-----------------------
}
