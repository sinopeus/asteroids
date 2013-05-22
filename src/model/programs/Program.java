package model.programs;

import java.util.HashMap;
import java.util.Map;

import model.IFacade.TypeCheckOutcome;
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

	private Map <String, Type>	globalTypes;

	public Map <String, Type> getGlobalTypes ()
	{
		return globalTypes;
	}

	protected void setGlobalTypes (Map <String, Type> globalTypes) throws RecognitionException
	{
		if (!canHaveAsGlobals(globalTypes)) throw new RecognitionException();
		this.globalTypes = globalTypes;
	}

	private HashMap <String, ConstantExpression <?>>	globalValues;

	public HashMap <String, ConstantExpression <?>> getGlobalValues ()
	{
		return globalValues;
	}

	protected void setGlobalValues (Map <String, Type> globalTypes) throws RecognitionException
	{
		if (!canHaveAsGlobals(globalTypes)) throw new RecognitionException();
		HashMap <String, ConstantExpression <?>> globalVariables = new HashMap <String, ConstantExpression <?>>();
		int counter = 0;
		for (String name : globalTypes.keySet())
			globalVariables.put(name, globalTypes.get(name).defaultValue(counter++, 0));
		this.globalValues = globalVariables;
	}

	protected static boolean canHaveAsGlobals (Map <String, Type> globals)
	{
		return (globals != null); // TODO more checking?
	}

	Statement	statement;

	protected Statement getStatement ()
	{
		return statement;
	}

	protected static boolean canHaveAsStatement (Statement statement)
	{
		return (statement != null); // TODO more checking?
	}

	protected void setStatement (Statement statement) throws RecognitionException
	{
		if (!canHaveAsStatement(statement)) throw new RecognitionException();
		statement.setParentProgram(this);
		this.statement = statement;
	}

	private Ship	owner;

	public Ship getOwner ()
	{
		return owner;
	}

	protected static boolean canHaveAsOwner (Ship owner)
	{
		return (owner != null);//TODO more checking
	}

	public void setOwner (Ship owner) throws IllegalArgumentException
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

	public ConstantExpression <?> getVariableNamed (String name) throws IllegalArgumentException
	{
		if (!globalValues.containsKey(name)) throw new IllegalArgumentException("invalid variable name: " + name);
		return getGlobalValues().get(name);
	}

	public void setVariableValue (String name, ConstantExpression <?> value) throws IllegalArgumentException
	{
		if (name == null) throw new IllegalArgumentException("Illegal name.");
		if (value == null) throw new IllegalArgumentException("Illegal value.");
		if (!globalValues.containsKey(name)) throw new IllegalArgumentException("invalid variable name");
		getGlobalValues().remove(name);
		getGlobalValues().put(name, value);
	}

	public void executeUntilAfterNextAction ()
	{
		while (!getStatement().isFinished())
			if (getStatement().execute()) break;
	}

	public TypeCheckOutcome isTypeSafe ()
	{
		return getStatement().isTypeSafe();
	}

	@Override
	public String toString ()
	{
		return "Program [globals = " + globalValues + ", finished = " + finished + ", statement = " + statement + "]";
	}
}
