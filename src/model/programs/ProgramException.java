package model.programs;

public class ProgramException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public ProgramException (int line, int column)
	{
		super("(line " + line + ", column " + column + ")");
	}

	public ProgramException (int line, int column, String message)
	{
		super(message + " (line " + line + ", column " + column + ")");
	}
}
