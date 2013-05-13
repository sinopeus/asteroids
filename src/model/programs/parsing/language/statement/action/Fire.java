package model.programs.parsing.language.statement.action;


public class Fire extends Action
{
	public Fire (int line, int column)
	{
		super(line, column);
	}

	@Override
	public boolean execute ()
	{
		super.execute();
		getOwnerShip().fire();
		finish();
		return true;
	}

	@Override
	public String toString ()
	{
		return "Fire [isFinished()=" + isFinished() + "]";
	}
}
