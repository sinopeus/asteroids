package model.programs.parsing.language.expression.constant;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
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
	
	@Override
	public boolean isTypeSafe ()
	{
		return true;
	}
	
	@Override
	public Type getType ()
	{
		return Type.TYPE_DOUBLE;
	}

	@Override
	public String toString ()
	{
		return "GetDirection";
	}
}
