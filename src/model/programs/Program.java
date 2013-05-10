package model.programs;

import java.util.HashMap;
import java.util.Map;

import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.BooleanLiteral;
import model.programs.parsing.language.expression.DoubleLiteral;
import model.programs.parsing.language.expression.EntityLiteral;
import model.programs.parsing.language.expression.Variable;
import model.programs.parsing.language.statement.Statement;

import org.antlr.runtime.RecognitionException;

import world.entity.Entity;
import world.entity.ship.Ship;

public class Program
{
	public Program (Map <String, Type> globals, Statement statement) throws RecognitionException
	{
		setGlobals(globals);
		setStatement(statement);
	}

	HashMap <String, Variable>	globals;

	public HashMap <String, Variable> getGlobals ()
	{
		return globals;
	}

	protected boolean canHaveAsGlobals (Map <String, Type> globals)
	{
		return (globals != null); // TODO more checking?
	}

	private void setGlobals (Map <String, Type> globals) throws RecognitionException
	{
		if (!canHaveAsGlobals(globals)) throw new RecognitionException();
		HashMap <String, Variable> globalVariables = new HashMap <String, Variable>();
		int counter = 0;
		for (String name : globals.keySet())
		{
			switch (globals.get(name))
			{
				case TYPE_DOUBLE:
					globalVariables.put(name, new Variable <DoubleLiteral, Double>(counter, 0, name));
					break;
				case TYPE_BOOLEAN:
					globalVariables.put(name, new Variable <BooleanLiteral, Boolean>(counter, 0, name));
					break;
				case TYPE_ENTITY:
					globalVariables.put(name, new Variable <EntityLiteral, Entity>(counter, 0, name));
					break;
				default:
					break;
			}
			counter++;
		}
		this.globals = globalVariables;
	}

	Statement	statement;

	public Statement getStatement ()
	{
		return statement;
	}

	protected boolean canHaveAsStatement (Statement statement)
	{
		return (statement != null); // TODO more checking?
	}

	public void setStatement (Statement statement) throws RecognitionException
	{
		if (!canHaveAsStatement(statement)) throw new RecognitionException();
		this.statement = statement;
	}

	private Ship	owner;

	public Ship getOwner ()
	{
		return owner;
	}

	protected boolean canHaveAsOwner (Ship owner)
	{
		return (owner != null);//TODO more checking
	}

	public void setOwner (Ship owner)
	{
		if (!canHaveAsOwner(owner)) throw new IllegalArgumentException("Invalid owner provided in program");
		this.owner = owner;
	}

	private boolean	finished;

	public boolean isFinished ()
	{
		return finished;
	}

	protected void finish ()
	{
		this.finished = true;
	}

	public void executeUntilAfterNextAction ()
	{
		while (!getStatement().isFinished())
			if (getStatement().execute(getOwner())) break;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return "Program [globals = " + globals + ", finished = " + finished + ", statement = " + statement + "]";
	}
}
