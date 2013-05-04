package model.programs.parsing.language.expression;

import world.entity.Entity;

public class Self extends ConstantExpression <Entity>
{

	public Self (int line, int column, Entity value) //TODO not sure about how to do this.
	{
		super(line, column, value);
	}
}
