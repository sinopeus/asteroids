package model.programs.parsing.language.expression;

public class Variable <T extends ConstantExpression> extends Expression
{

	public Variable (int line, int column, String name)
	{
		super(line, column);
		setName(name);
	}

	String	name;

	public String getName ()
	{
		return name;
	}

	protected boolean canHaveAsName (String name)
	{
		return (name != null); //TODO more checking
	}

	private void setName (String name)
	{
		if (!canHaveAsName(name)) throw new IllegalArgumentException("Invalid name for variable.");
		this.name = name;
	}

	T	value;

	private T getValue ()
	{
		return value;
	}

	protected boolean canHaveAsValue (T value)
	{
		return (value != null); //TODO some more checking?
	}

	public void setValue (T value)
	{
		if (!canHaveAsValue(value)) throw new IllegalArgumentException("Invalid value for variable.");
		this.value = value;
	}

	public Class getType ()
	{
		return getValue().getClass();
	}

	@Override
	public Object evaluate ()
	{
		return getValue();
	}

}
