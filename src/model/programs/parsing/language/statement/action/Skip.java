package model.programs.parsing.language.statement.action;

public class Skip extends Action
{
	public Skip (int line, int column)
	{
		super(line, column);
	}
	
	@Override
	public boolean executeUntilAction ()
	{
		//TODO just do nothing?
		return true;
	}
}
