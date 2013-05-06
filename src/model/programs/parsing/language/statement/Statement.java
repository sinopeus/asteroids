package model.programs.parsing.language.statement;

public abstract class Statement
{
	public Statement (int line, int column)
	{
		finished = false;
		setLine(line);
		setColumn(column);
	}

	int	line;

	public int getLine ()
	{
		return line;
	}

	private boolean canHaveAsLine (int line)
	{
		return (line >= 0); //TODO GE?
	}

	public void setLine (int line)
	{
		if (!canHaveAsLine(line)) throw new IllegalArgumentException("Invalid line provided for expression.");
		this.line = line;
	}

	int	column;

	public int getColumn ()
	{
		return column;
	}

	private boolean canHaveAsColumn (int column)
	{
		return (column >= 0); //TODO GE?
	}

	public void setColumn (int column)
	{
		if (!canHaveAsColumn(column)) throw new IllegalArgumentException("Invalid column provided for expression.");
		this.column = column;
	}

	private boolean	finished;

	public boolean isFinished ()
	{
		return finished;
	}

	protected void finish ()
	{
		this.finished = false;
	}

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
