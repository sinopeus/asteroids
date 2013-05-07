package model.programs.parsing.language.statement;

import model.programs.parsing.language.expression.BooleanLiteral;
import model.programs.parsing.language.expression.Expression;
import world.entity.ship.Ship;

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

	private void setCondition (Expression condition)
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

	private void setBody (Statement body)
	{
		this.body = body;
	}

	boolean	resultOfCondition;

	public boolean getResultOfCondition ()
	{
		return resultOfCondition;
	}

	private boolean	checked;

	public boolean isChecked ()
	{
		return checked;
	}

	private void checkCondition (Ship ship)
	{
		resultOfCondition = ((BooleanLiteral) (getCondition().evaluate(ship))).getValue();
		checked = true;
	}

	private void finishIteration (Ship ship)
	{
		getBody().unfinish();
		checkCondition(ship);
	}

	@Override
	public void unfinish ()
	{
		getBody().unfinish();
		super.unfinish();
	}

	public boolean execute (Ship ship)//TODO check this, TEST IT
	{
		super.execute(ship);
		if (!checked) checkCondition(ship);
		while (getResultOfCondition())
		{
			if (getBody().isFinished())
			{
				finishIteration(ship);
				continue;
			}
			if (getBody().execute(ship)) return true;
			else finishIteration(ship);
		}
		finish();
		return false;

	}
}
