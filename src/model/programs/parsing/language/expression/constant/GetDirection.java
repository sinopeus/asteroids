package model.programs.parsing.language.expression.constant;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;

public class GetDirection extends ConstantExpression <Double>
{
	public GetDirection (int line, int column) throws ProgramException
	{
		super(line, column, 0.0);
	}

	@Override
	public ConstantExpression <Double> evaluate ()
	{
		DoubleLiteral dl = null;
		try
		{
			dl = new DoubleLiteral(getLine(), getColumn(), getOwnerShip().getDirection().getAngle().get());
		} catch (ProgramException e)
		{
			e.printStackTrace(); //THIS SHOULD NEVER EVER HAPPEN
		}
		return dl;
	}
	
}
