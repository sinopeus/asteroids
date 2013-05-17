package model.programs;

import java.util.HashMap;
import java.util.Map;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.ConstantExpression;
import model.programs.parsing.language.statement.Statement;

import org.antlr.runtime.RecognitionException;

import world.entity.ship.Ship;

public class Program
{
	public Program (Map <String, Type> globals, Statement statement) throws RecognitionException
	{
		setGlobalTypes(globals);
		setGlobalValues(globals);
		setStatement(statement);
	}

	Map <String, Type>	globalTypes;

	protected Map <String, Type> getGlobalTypes ()
	{
		return globalTypes;
	}

	protected void setGlobalTypes (Map <String, Type> globalTypes) throws RecognitionException
	{
		if (!canHaveAsGlobals(globalTypes)) throw new RecognitionException();
		this.globalTypes = globalTypes;
	}

	HashMap <String, ConstantExpression>	globalValues;

	protected HashMap <String, ConstantExpression> getGlobalValues ()
	{
		return globalValues;
	}

	protected void setGlobalValues (Map <String, Type> globalsTypes) throws RecognitionException
	{
		if (!canHaveAsGlobals(globalsTypes)) throw new RecognitionException();
		HashMap <String, ConstantExpression> globalVariables = new HashMap <String, ConstantExpression>();
		int counter = 0;
		for (String name : globalsTypes.keySet())
			globalVariables.put(name, globalTypes.get(name).defaultValue(counter++, 0));
		this.globalValues = globalVariables;
	}

	protected boolean canHaveAsGlobals (Map <String, Type> globals)
	{
		return (globals != null); // TODO more checking?
	}

	Statement	statement;

	protected Statement getStatement ()
	{
		return statement;
	}

	protected boolean canHaveAsStatement (Statement statement)
	{
		return (statement != null); // TODO more checking?
	}

	protected void setStatement (Statement statement) throws RecognitionException
	{
		if (!canHaveAsStatement(statement)) throw new RecognitionException();
		this.statement = statement;
		try
		{
			statement.setParentProgram(this);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
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

	public ConstantExpression getVariableNamed (String name)
	{
		if (!globalValues.containsKey(name)) throw new IllegalArgumentException("invalid variable name");
		return getGlobalValues().get(name);
	}

	public void setVariableValue (String name, ConstantExpression value)
	{
		if (!globalValues.containsKey(name)) throw new IllegalArgumentException("invalid variable name");
		getGlobalValues().remove(name);
		getGlobalValues().put(name, value);
	}

	public void executeUntilAfterNextAction ()
	{
		while (!getStatement().isFinished())
			if (getStatement().execute()) break;
	}

	@Override
	public String toString ()
	{
		return "Program [globals = " + globalValues + ", finished = " + finished + ", statement = " + statement + "]";
	}
}
