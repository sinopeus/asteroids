package model.programs;


public class TypeException extends ProgramException
{
	public TypeException (int line, int column)
	{
		super(line, column);
	}

	public TypeException (int line, int column, String message)
	{
		super(line, column, message);
	}

	private static final long	serialVersionUID	= 1L;
}
