package model.programs.parsing.language.expression;


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
