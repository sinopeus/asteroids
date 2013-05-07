package model.programs.parsing.language.statement;

import java.util.List;

import world.entity.ship.Ship;

public class Sequence extends Statement
{

	public Sequence (int line, int column, List <Statement> sequence)
	{
		super(line, column);
		setSequence(sequence);
	}

	private List <Statement>	sequence;

	private List <Statement> getSequence ()
	{
		return sequence;
	}

	private boolean canHaveAsSequence (List <Statement> sequence)
	{
		return (sequence != null);//TODO more checking?
	}

	private void setSequence (List <Statement> sequence)
	{
		if (!canHaveAsSequence(sequence)) throw new IllegalArgumentException("Invalid sequence of statements.");//TODO other message and/or exception.
		this.sequence = sequence;
	}

	private int	selectedIndex;

	public int getSelectedIndex ()
	{
		return selectedIndex;
	}

	protected boolean canHaveAsSelectedIndex (int selectedIndex)
	{
		return ( (selectedIndex >= 0) && (selectedIndex < getSequence().size()));
	}

	private void setSelectedIndex (int selectedIndex)
	{
		if (!canHaveAsSelectedIndex(selectedIndex)) throw new IllegalArgumentException("Illegal selected index for sequence " + selectedIndex);
		this.selectedIndex = selectedIndex;
	}

	private void incrementIndex ()
	{
		if (getSelectedIndex() >= getSequence().size() - 1)
		{
			finish();
			return;
		}
		setSelectedIndex(getSelectedIndex() + 1);
	}

	private Statement getCurrentStatement ()
	{
		if (getSequence().isEmpty()) return null;
		return getSequence().get(getSelectedIndex());
	}

	@Override
	public boolean execute (Ship ship)
	{
		super.execute(ship);
		if (getCurrentStatement() == null)
		{
			finish();
			return false;
		}
		while (!getCurrentStatement().isFinished())
		{
			if (getCurrentStatement().execute(ship))
			{
				if (getCurrentStatement().isFinished()) incrementIndex();
				return true;
			} else incrementIndex();
		}
		return false;
	}

	@Override
	public String toString ()
	{
		return "Sequence [sequence=" + sequence + "]";
	}
}
