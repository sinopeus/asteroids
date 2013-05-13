package model.programs.parsing.language.expression.constant;

import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;



public class GetDirection extends ConstantExpression <Double>
{

	public GetDirection (int line, int column)
	{
		super(line, column, null);
	}

	@Override
	public ConstantExpression <Double> evaluate ()
	{
		return new DoubleLiteral(getLine(), getColumn(), getOwnerShip().getDirection().getAngle().get());
	}
}
