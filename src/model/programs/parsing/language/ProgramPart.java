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

	Program	parentProgram;

	protected Program getParentProgram ()
	{
		return parentProgram;
	}

	protected boolean canHaveAsParentProgram (Program program)
	{
		return (program != null);
	}

	public void setParentProgram (Program parrentProgram)
	{
		if (!canHaveAsParentProgram(parrentProgram)) throw new IllegalArgumentException();//TODO
		this.parentProgram = parrentProgram;
	}

	int	line;

	public int getLine ()
	{
		return line;
	}

	protected boolean canHaveAsLine (int line)
	{
		return (line >= 0); //TODO GE?
	}

	protected void setLine (int line)
	{
		if (!canHaveAsLine(line)) throw new IllegalArgumentException("Invalid line provided for expression.");
		this.line = line;
	}

	int	column;

	public int getColumn ()
	{
		return column;
	}

	protected boolean canHaveAsColumn (int column)
	{
		return (column >= 0); //TODO GE?
	}

	protected void setColumn (int column)
	{
		if (!canHaveAsColumn(column)) throw new IllegalArgumentException("Invalid column provided for expression.");
		this.column = column;
	}

	protected Ship getOwnerShip ()
	{
		return getParentProgram().getOwner();
	}
}
