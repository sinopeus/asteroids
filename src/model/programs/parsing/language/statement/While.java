package model.programs.parsing.language.statement;

import model.programs.Program;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public class While extends Statement
{
	public While (int line, int column, Expression condition, Statement body)
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
	public void setParentProgram (Program parrentProgram)
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
}
