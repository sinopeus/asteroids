package model.programs.parsing.language.expression;

public class BooleanVariable extends Variable <BooleanLiteral, Boolean>
{

	public BooleanVariable (int line, int column, String name)
	{
		super(line, column, name);
		setValue(new BooleanLiteral(line, column, false));
	}
}
