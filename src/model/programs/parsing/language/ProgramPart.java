package model.programs.parsing.language;

import model.programs.Program;
import world.entity.ship.Ship;

public abstract class ProgramPart
{
	protected ProgramPart (int line, int column)
	{
		setLine(line);
		setColumn(column);
	}

	Program	parrentProgram;

	public Program getParrentProgram ()
	{
		return parrentProgram;
	}

	protected boolean canHaveAsParentProgram (Program program)
	{
		return (program != null);
	}

	public void setParrentProgram (Program parrentProgram)
	{
		if (!canHaveAsParentProgram(parrentProgram)) throw new IllegalArgumentException();//TODO
		this.parrentProgram = parrentProgram;
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

	protected Ship getOwnerShip ()
	{
		return getParrentProgram().getOwner();
	}
}
