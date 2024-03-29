package model.programs.parsing.language.statement;

import model.programs.ProgramException;
import model.programs.parsing.language.ProgramPart;

public abstract class Statement extends ProgramPart
{
	protected Statement (int line, int column) throws IllegalArgumentException
	{
		super(line,column);
		finished = false;
	}

	private boolean	finished;

	public boolean isFinished ()
	{
		return finished;
	}

	public void unfinish ()
	{
		this.finished = false;
	}

	protected void finish ()
	{
		this.finished = true;
	}
	
	public abstract boolean containsAction();
	
	/**
	 * 
	 * @return True if and only if the last executed statement was an action.
	 */
	public boolean execute ()
	{
		if (isFinished()) throw new IllegalStateException("This statement is already finished.");
		return false;
	}
}
