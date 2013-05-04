package model.programs.parsing.language.expression;

public abstract class Expression
{
	public Expression (int line, int column)
	{
		setLine(line);
		setColumn(column);
	}

	int	line;

	public int getLine ()
	{
		return line;
	}

	private boolean canHaveAsLine (int line)
	{
		return (line >= 0); //TODO GE?
	}

	public void setLine (int line)
	{
		if (!canHaveAsLine(line)) throw new IllegalArgumentException("Invalid line provided for expression.");
		this.line = line;
	}

	int	column;

	public int getColumn ()
	{
		return column;
	}

	private boolean canHaveAsColumn (int column)
	{
		return (column >= 0); //TODO GE?
	}

	public void setColumn (int column)
	{
		if (!canHaveAsColumn(column)) throw new IllegalArgumentException("Invalid column provided for expression.");
		this.column = column;
	}

	public Class getType ()
	{
		return evaluate().getClass();
	}

	public abstract ConstantExpression evaluate (); //TODO change from object to something useful?
}
