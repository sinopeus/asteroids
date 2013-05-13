package model.programs;

import java.util.HashMap;
import java.util.Map;

import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.BooleanLiteral;
import model.programs.parsing.language.expression.ConstantExpression;
import model.programs.parsing.language.expression.DoubleLiteral;
import model.programs.parsing.language.expression.EntityLiteral;
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

	public Map <String, Type> getGlobalTypes ()
	{
		return globalTypes;
	}

	public void setGlobalTypes (Map <String, Type> globalTypes) throws RecognitionException
	{
		if (!canHaveAsGlobals(globalTypes)) throw new RecognitionException();
		this.globalTypes = globalTypes;
	}

	HashMap <String, ConstantExpression>	globalValues;

	public HashMap <String, ConstantExpression> getGlobalValues ()
	{
		return globalValues;
	}

	private void setGlobalValues (Map <String, Type> globalsTypes) throws RecognitionException
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
		statement.setParentProgram(this);
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
		if(!globalValues.containsKey(name)) throw new IllegalArgumentException("invalid variable name");
		return getGlobalValues().get(name);
	}

	public void setVariableValue (String name, ConstantExpression value)
	{
		if(!globalValues.containsKey(name)) throw new IllegalArgumentException("invalid variable name");
		getGlobalValues().remove(name);
		getGlobalValues().put(name, value);
	}

	public void executeUntilAfterNextAction ()
	{
		while (!getStatement().isFinished())
			if (getStatement().execute()) break;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return "Program [globals = " + globalValues + ", finished = " + finished + ", statement = " + statement + "]";
	}
}
