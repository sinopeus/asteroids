package model.programs.parsing.language.expression.constant;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;
import world.entity.Entity;

public class Self extends ConstantExpression <Entity>
{
	public Self (int line, int column) throws ProgramException //TODO not sure about how to do this.
	{
		super(line, column, null);
	}
	
	@Override
	public ConstantExpression <Entity> evaluate ()
	{
		EntityLiteral el = null;
		try
		{
			el=new EntityLiteral(getLine(),getColumn(), getOwnerShip());
		} catch (ProgramException e)
		{
			e.printStackTrace(); //THIS SHOULD NEVER EVER HAPPEN
		}
		return el;
	}

	@Override
	public String toString ()
	{
		return "Self";
	}
}
