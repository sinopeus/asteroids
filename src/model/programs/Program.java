package model.programs;

import java.util.Map;

import model.programs.parsing.language.Type;
import model.programs.parsing.language.statement.Statement;

import org.antlr.runtime.RecognitionException;

public class Program
{
	private int	currentIndex;

	public Program (Map <String, Type> globals, Statement statement) throws RecognitionException
	{
		if (!canHaveAsGlobals(globals)) throw new RecognitionException();
		if (!canHaveAsStatement(statement)) throw new RecognitionException(); //TODO correct exceptions?
	}

	Map <String, Type>	globals;

	public Map <String, Type> getGlobals ()
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
		this.globals = globals;
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
