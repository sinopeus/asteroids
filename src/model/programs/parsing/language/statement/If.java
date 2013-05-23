package model.programs.parsing.language.statement;

import model.IFacade.TypeCheckOutcome;
import model.programs.Program;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public class If extends Statement
{
	public If (int line, int column, Expression condition, Statement then, Statement otherwise) throws IllegalArgumentException
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

	protected static boolean canHaveAsCondition (Expression condition)
	{
		return (condition != null);
	}

	protected void setCondition (Expression condition)
	{
		if (!canHaveAsCondition(condition)) throw new IllegalArgumentException("Invalid condition for if statement.");
		this.condition = condition;
	}

	Statement	then;

	protected Statement getThenStatement ()
	{
		return then;
	}

	protected static boolean canHaveAsThenStatement (Statement then)
	{
		return (then != null);
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

	protected static boolean canHaveAsOtherwiseStatement (Statement otherwise)
	{
		return true;
	}

	protected void setOtherwiseStatement (Statement otherwise)
	{
		if (!canHaveAsOtherwiseStatement(otherwise)) throw new IllegalArgumentException("Invalid otherwise statement for if statement");
		this.otherwise = otherwise;
	}

	@Override
	public void setParentProgram (Program parrentProgram) throws IllegalArgumentException
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
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome conditionIsTypeSafe = getCondition().isTypeSafe();
		if (!conditionIsTypeSafe.isSuccessful()) return conditionIsTypeSafe;
		boolean conditionIsBoolean = getCondition().getType() == Type.TYPE_BOOLEAN;
		if (!conditionIsBoolean) return TypeCheckOutcome.failure("The condition of the if statement at " + getLine() + ", " + getColumn() + " is not type safe.");
		TypeCheckOutcome thenIsTypeSafe = getThenStatement().isTypeSafe();
		if (!thenIsTypeSafe.isSuccessful()) return thenIsTypeSafe;
		if (getOtherwiseStatement() != null)
		{
			TypeCheckOutcome otherwiseIsTypeSafe = getOtherwiseStatement().isTypeSafe();
			if (!otherwiseIsTypeSafe.isSuccessful()) return otherwiseIsTypeSafe;
		}
		return TypeCheckOutcome.success();
	}

	@Override
	public boolean containsAction ()
	{
		return (getThenStatement().containsAction() || getOtherwiseStatement().containsAction());
	}

	@Override
	public String toString ()
	{
		return "If [condition=" + condition + ", then=" + then + ", otherwise=" + otherwise + "]";
	}
}
