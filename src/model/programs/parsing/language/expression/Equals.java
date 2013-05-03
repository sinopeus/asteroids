package model.programs.parsing.language.expression;

import Utilities.Util;

public class Equals extends SecondOrderExpressionOfNumbers
{
	public Equals (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected Object function (Double first, Double second)
	{
		return Util.fuzzyEquals(Double.valueOf(first), Double.valueOf(second));
	}
}
