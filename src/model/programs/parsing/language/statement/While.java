package model.programs.parsing.language.statement;

import model.IFacade.TypeCheckOutcome;
import model.programs.Program;
import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public class While extends Statement
{
	public While (int line, int column, Expression condition, Statement body) throws IllegalArgumentException
	{
		super(line, column);
		checked = false;
		setCondition(condition);
		setBody(body);
	}

	Expression	condition;

	public Expression getCondition ()
	{
		return condition;
	}

	protected boolean canHaveAsCondition (Expression condition)
	{
		return (condition != null);//TODO more checking?
	}

	protected void setCondition (Expression condition)
	{
		if (!canHaveAsCondition(condition)) throw new IllegalArgumentException("Invalid condition provided for while statement."); //TODO ?
		this.condition = condition;
	}

	private Statement	body;

	public Statement getBody ()
	{
		return body;
	}

	protected boolean canHaveAsBody (Statement body)
	{
		return (body != null); //TODO more checking?
	}

	protected void setBody (Statement body)
	{
		this.body = body;
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
	
	@Override
	public void setParentProgram (Program parrentProgram) throws IllegalArgumentException
	{
		super.setParentProgram(parrentProgram);
		getCondition().setParentProgram(parrentProgram);
		getBody().setParentProgram(parrentProgram);
	}

	protected void checkCondition ()
	{
		resultOfCondition = ((BooleanLiteral) (getCondition().evaluate())).getValue();
		checked = true;
	}

	protected void finishIteration ()
	{
		getBody().unfinish();
		checkCondition();
	}

	@Override
	public void unfinish ()
	{
		getBody().unfinish();
		checked = false;
		super.unfinish();
	}

	public boolean execute ()//TODO check this, TEST IT
	{
		super.execute();
		if (!checked) checkCondition();
		while (getResultOfCondition())
		{
			if (getBody().isFinished())
			{
				finishIteration();
				continue;
			}
			if (getBody().execute()) return true;
			else finishIteration();
		}
		finish();
		return false;

	}
	
	@Override
	public TypeCheckOutcome isTypeSafe ()
	{
		TypeCheckOutcome conditionIsTypeSafe = getCondition().isTypeSafe();
		if(!conditionIsTypeSafe.isSuccessful()) return TypeCheckOutcome.failure("The condition of the while statement at " + getLine() + ", " + getColumn() + " is not type safe.");
		boolean conditionIsBoolean = getCondition().getType() == Type.TYPE_BOOLEAN;
		if(!conditionIsBoolean) return TypeCheckOutcome.failure("The condition of the while statement at " + getLine() + ", " + getColumn() + " is not a boolean.");
		TypeCheckOutcome bodyIsTypeSafe = getBody().isTypeSafe();
		if(!bodyIsTypeSafe.isSuccessful()) return TypeCheckOutcome.failure("The bode of the while statement at " + getLine() + ", " + getColumn() + " is not type safe.");
		return TypeCheckOutcome.success();
	}
	
	@Override
	public boolean containsAction ()
	{
		return getBody().containsAction();
	}

	@Override
	public String toString ()
	{
		return "While [condition=" + condition + ", body=" + body + "]";
	}
}
