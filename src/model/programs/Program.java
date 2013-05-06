package model.programs;

import java.util.ArrayList;
import java.util.Map;

import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.BooleanLiteral;
import model.programs.parsing.language.expression.DoubleLiteral;
import model.programs.parsing.language.expression.EntityLiteral;
import model.programs.parsing.language.expression.Variable;
import model.programs.parsing.language.statement.Statement;

import org.antlr.runtime.RecognitionException;

public class Program
{
	public Program (Map <String, Type> globals, Statement statement) throws RecognitionException
	{
		setGlobals(globals);
		setStatement(statement);
	}

	ArrayList <Variable>	globals;

	public ArrayList <Variable> getGlobals ()
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
		ArrayList <Variable> globalVariables = new ArrayList <Variable>();
		int counter = 0;
		for (String name : globals.keySet())
		{
			switch (globals.get(name))
			{
				case TYPE_DOUBLE:
					globalVariables.add(new Variable <DoubleLiteral>(counter++, 0, name));
					break;
				case TYPE_BOOLEAN:
					globalVariables.add(new Variable <BooleanLiteral>(counter++, 0, name));
					break;
				case TYPE_ENTITY:
					globalVariables.add(new Variable <EntityLiteral>(counter++, 0, name));
					break;
			}
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

	public void executeUntilAfterNextAction ()
	{
		while (!getStatement().isFinished())
			if (getStatement().execute()) break;
	}
}
