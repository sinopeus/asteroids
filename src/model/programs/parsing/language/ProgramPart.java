package model.programs.parsing.language;

import model.IFacade.TypeCheckOutcome;
import model.programs.Program;
import model.programs.ProgramException;
import world.entity.ship.Ship;

public abstract class ProgramPart
{
	protected ProgramPart (int line, int column) throws IllegalArgumentException
	{
		setLine(line);
		setColumn(column);
	}

	protected Program	parentProgram;

	public Program getParentProgram ()
	{
		return parentProgram;
	}

	protected static boolean canHaveAsParentProgram (Program program)
	{
		return (program != null);
	}

	public void setParentProgram (Program parentProgram) throws IllegalArgumentException
	{
		if (!canHaveAsParentProgram(parentProgram)) throw new IllegalArgumentException("Illegal parent program provided.");
		this.parentProgram = parentProgram;
	}

	int	line;

	public int getLine ()
	{
		return line;
	}

	protected static boolean canHaveAsLine (int line)
	{
		return (line >= 0);
	}

	protected void setLine (int line) throws IllegalArgumentException
	{
		if (!canHaveAsLine(line)) throw new IllegalArgumentException("Invalid line provided for expression.");
		this.line = line;
	}

	int	column;

	public int getColumn ()
	{
		return column;
	}

	protected static boolean canHaveAsColumn (int column)
	{
		return (column >= 0);
	}

	protected void setColumn (int column) throws IllegalArgumentException
	{
		if (!canHaveAsColumn(column)) throw new IllegalArgumentException("Invalid column provided for expression.");
		this.column = column;
	}

	public Ship getOwnerShip ()
	{
		return getParentProgram().getOwner();
	}
	
	public abstract TypeCheckOutcome isTypeSafe();
}
