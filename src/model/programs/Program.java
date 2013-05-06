package model.programs;

import java.util.ArrayList;

import model.programs.parsing.language.statement.Statement;

public class Program extends ArrayList <Statement>
{
	private int	currentIndex;

	private int getCurrentIndex ()
	{
		return currentIndex;
	}

	private boolean canHaveAsCurrentIndex (int index)
	{
		return ( (index >= 0) && (index < this.size()));
	}

	private void setCurrentIndex (int currentIndex)
	{
		if (!canHaveAsCurrentIndex(currentIndex)) throw new IllegalArgumentException("invalid current index."); //TODO change message and/or style?
		this.currentIndex = currentIndex;
	}

	private void incrementCurrentIndex ()
	{
		setCurrentIndex(getCurrentIndex() + 1);
	}

	private Statement getCurrentStatement ()
	{
		return this.get(getCurrentIndex());
	}

	public void executeUntilAfterNextAction ()
	{
		while (!getCurrentStatement().isFinished())
			if (!getCurrentStatement().executeUntilAction()) break;
			else incrementCurrentIndex();

	}
}
