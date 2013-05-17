package model.programs.parsing.language.statement;

import model.programs.Program;
import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public class If extends Statement
{
	public If (int line, int column, Expression condition, Statement then, Statement otherwise) throws ProgramException
	{
		super(line, column);
		setCondition(condition);
		setThenStatement(then);
		setOtherwiseStatement(otherwise);
		checked = false;
	}

	boolean	resultOfCondition;

	protected boolean getResultOfCondition ()
	{
		return resultOfCondition;
	}

	private boolean	checked;

	protected boolean isChecked ()
	{
		return checked;
	}

	protected void checkCondition ()
	{
		resultOfCondition = ((BooleanLiteral) (getCondition().evaluate())).getValue();
		checked = true;
	}

	Expression	condition;

	protected Expression getCondition ()
	{
		return condition;
	}

	protected boolean canHaveAsCondition (Expression condition)
	{
		return (condition != null);//TODO more checking?
	}

	protected void setCondition (Expression condition)
	{
		if (!canHaveAsCondition(condition)) throw new IllegalArgumentException("Invalid condition for if statement."); //TODO change the kind of exception?
		this.condition = condition;
	}

	Statement	then;

	protected Statement getThenStatement ()
	{
		return then;
	}

	protected boolean canHaveAsThenStatement (Statement then)
	{
		return (then != null); //TODO more checking?
	}

	protected void setThenStatement (Statement then)
	{
		if (!canHaveAsThenStatement(then)) throw new IllegalArgumentException("Invalid then statement for if statement");
		this.then = then;
	}

	Statement	otherwise;

	protected Statement getOtherwiseStatement ()
	{
		return otherwise;
	}

	protected boolean canHaveAsOtherwiseStatement (Statement otherwise)
	{
		return true; //TODO more checking?
	}

	protected void setOtherwiseStatement (Statement otherwise)
	{
		if (!canHaveAsOtherwiseStatement(otherwise)) throw new IllegalArgumentException("Invalid otherwise statement for if statement");
		this.otherwise = otherwise;
	}

	@Override
	public void setParentProgram (Program parrentProgram) throws ProgramException
	{
		super.setParentProgram(parrentProgram);
		getCondition().setParentProgram(parrentProgram);
		getThenStatement().setParentProgram(parrentProgram);
		if (getOtherwiseStatement() != null) getOtherwiseStatement().setParentProgram(parrentProgram);
	}

	@Override
	public void unfinish ()
	{
		super.unfinish();
		getThenStatement().unfinish();
		if (getOtherwiseStatement() != null) getOtherwiseStatement().unfinish();
	}

	private boolean execute (Statement statement)
	{
		super.execute();
		if (statement == null)
		{
			finish();
			return false;
		}
		if (!statement.isFinished())
		{
			boolean lastStatementWasAction = statement.execute();
			if (statement.isFinished()) finish();
			return lastStatementWasAction;
		} else
		{
			finish();
			return false; // TODO make this into an error?
		}
	}

	@Override
	public boolean execute ()
	{
		super.execute();
		if (!isChecked()) checkCondition();
		return resultOfCondition ? execute(getThenStatement()) : execute(getOtherwiseStatement());
	}

	@Override
	public String toString ()
	{
		return "If [condition=" + condition + ", then=" + then + ", otherwise=" + otherwise + "]";
	}
}
