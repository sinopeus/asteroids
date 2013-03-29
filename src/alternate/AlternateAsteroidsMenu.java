package alternate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

@SuppressWarnings ("javadoc")
public class AlternateAsteroidsMenu <World, Ship, Asteroid, Bullet> extends JPanel
{
	private static final long	serialVersionUID	= 1L;

	public AlternateAsteroids	game;

	private BorderLayout		layout;
	private Box					labelBox;
	private JLabel				title;
	private JLabel[]			labels;

	private int					selectedIndex;
	private static final int	options				= 4;

	// ------------------------------------- INITIALISATION --------------------------------
	public AlternateAsteroidsMenu ()
	{
		initializeSelf();
		initializeKeyBindings();
	}

	private void initializeSelf ()
	{
		selectedIndex = 0;

		initializeLayout();
		initializeComponents();
	}

	private void initializeLayout ()
	{
		layout = new BorderLayout();
		this.setLayout(layout);
	}

	private void initializeComponents ()
	{
		initializeLabelBox();
	}

	private void initializeLabelBox ()
	{
		labels = new JLabel[options];
		labelBox = new Box(BoxLayout.Y_AXIS);
		labelBox.add(Box.createVerticalGlue());
		initializeHeaderLabel();
		labelBox.add(Box.createVerticalStrut(30));
		initializeSPLabel();
		labelBox.add(Box.createVerticalStrut(10));
		initializeMPLabel();
		labelBox.add(Box.createVerticalStrut(10));
		initializeSettingsLabel();
		labelBox.add(Box.createVerticalStrut(10));
		initializeExitLabel();
		labelBox.add(Box.createVerticalGlue());
		this.add(labelBox, BorderLayout.CENTER);
	}

	private void setToDefaultStyle (JLabel label)
	{
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setAlignmentY(Component.CENTER_ALIGNMENT);
		label.setForeground(Color.white);
		label.setFont(new Font("calibri", Font.PLAIN, 50));
	}

	private void initializeHeaderLabel ()
	{
		title = new JLabel("Asteroids");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setAlignmentY(Component.CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("calibri", Font.PLAIN, 150));
		labelBox.add(title);
	}

	private void initializeSPLabel ()
	{
		labels[0] = new JLabel("Single Player");
		setToDefaultStyle(labels[0]);
		labels[0].setForeground(Color.red);
		labelBox.add(labels[0]);
	}

	private void initializeMPLabel ()
	{
		labels[1] = new JLabel("Multi Player");
		setToDefaultStyle(labels[1]);
		labelBox.add(labels[1]);
	}

	private void initializeSettingsLabel ()
	{
		labels[2] = new JLabel("Settings");
		setToDefaultStyle(labels[2]);
		labelBox.add(labels[2]);
	}

	private void initializeExitLabel ()
	{
		labels[3] = new JLabel("Exit");
		setToDefaultStyle(labels[3]);
		labelBox.add(labels[3]);
	}

	// ------------------KEY BINDINGS----------------------

	private void initializeKeyBindings ()
	{
		this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "ESCAPE");
		this.getActionMap().put("ESCAPE", new Close());
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "UP");
		this.getActionMap().put("UP", new Up());
		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "DOWN");
		this.getActionMap().put("DOWN", new Down());
		this.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "ENTER");
		this.getActionMap().put("ENTER", new Select());
	}

	private class Close extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;

		@Override
		public void actionPerformed (ActionEvent e)
		{
			System.exit(0);
		}
	}

	private class Up extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;

		@Override
		public void actionPerformed (ActionEvent e)
		{
			labels[selectedIndex].setForeground(Color.white);
			selectedIndex = selectedIndex == 0 ? options - 1 : selectedIndex - 1;
			labels[selectedIndex].setForeground(Color.red);
			repaint();
		}
	}

	private class Down extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;

		@Override
		public void actionPerformed (ActionEvent e)
		{
			labels[selectedIndex].setForeground(Color.white);
			selectedIndex = (selectedIndex + 1) % options;
			labels[selectedIndex].setForeground(Color.red);
			repaint();
		}
	}

	private class Select extends AbstractAction
	{
		private static final long	serialVersionUID	= 1L;

		@Override
		public void actionPerformed (ActionEvent e)
		{
			switch (selectedIndex)
			{
				case 0:
					System.out.println("singleplayer");
					break;
				case 1:
					System.out.println("multiplayer");
					break;
				case 2:
					System.out.println("settingsmenu.");
					game.layout.show(game.getContentPane(), "SETTINGS");
					game.settings.requestFocusInWindow();
					break;
				case 3:
					System.exit(0);
			}
		}
	}

	// ------------------KEY BINDINGS----------------------

	// ------------------------------------- INITIALISATION --------------------------------

	@Override
	public void paintComponent (Graphics g)
	{
		g.drawImage(AlternateResources.getImage("menu-background"), 0, 0, getRootPane().getWidth(), getRootPane().getHeight(), null);
	}
}
