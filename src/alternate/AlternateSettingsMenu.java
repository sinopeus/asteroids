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

public class AlternateSettingsMenu extends JPanel
{
	private static final long	serialVersionUID	= 1L;

	public AlternateAsteroids	game;

	private BorderLayout		layout;
	private Box					labelBox;
	private JLabel				title;
	private JLabel[]			labels;

	private int					selectedIndex;
	private static final int	options				= 4;

	public AlternateSettingsMenu ()
	{
		initializeSelf();
		initializeKeyBindings();
	}

	private void initializeSelf ()
	{
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
		initializeSetting1();
		labelBox.add(Box.createVerticalStrut(10));
		initializeSetting2();
		labelBox.add(Box.createVerticalStrut(10));
		initializeSetting3();
		labelBox.add(Box.createVerticalStrut(10));
		initializeBackLabel();
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
		title = new JLabel("Settings");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setAlignmentY(Component.CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("calibri", Font.PLAIN, 100));
		labelBox.add(title);
	}

	private void initializeSetting1 ()
	{
		labels[0] = new JLabel("Setting 1");
		setToDefaultStyle(labels[0]);
		labels[0].setForeground(Color.red);
		labelBox.add(labels[0]);
	}

	private void initializeSetting2 ()
	{
		labels[1] = new JLabel("Setting 2");
		setToDefaultStyle(labels[1]);
		labels[1].setForeground(Color.white);
		labelBox.add(labels[1]);
	}

	private void initializeSetting3 ()
	{
		labels[2] = new JLabel("Setting 3");
		setToDefaultStyle(labels[2]);
		labels[2].setForeground(Color.white);
		labelBox.add(labels[2]);
	}

	private void initializeBackLabel ()
	{
		labels[3] = new JLabel("Back");
		setToDefaultStyle(labels[3]);
		labels[3].setForeground(Color.white);
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
			game.layout.show(game.getContentPane(), "MENU");
			game.menu.requestFocusInWindow();
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
					System.out.println("setting1");
					break;
				case 1:
					System.out.println("setting2");
					break;
				case 2:
					System.out.println("setting3.");
					break;
				case 3:
					game.layout.show(game.getContentPane(), "MENU");
					game.menu.requestFocusInWindow();
			}
		}
	}

	// ------------------KEY BINDINGS----------------------

	@Override
	public void paintComponent (Graphics g)
	{
		g.drawImage(AlternateResources.getImage("web1"), 0, 0, getRootPane().getWidth(), getRootPane().getHeight(), null);
	}
}
