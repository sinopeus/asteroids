package model.programs.parsing.language;

import world.entity.Entity;
import model.programs.ProgramException;
import model.programs.parsing.language.expression.constant.ConstantExpression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;

public enum Type
{
	TYPE_BOOLEAN
	{
		@Override
		public ConstantExpression<Boolean> defaultValue (int line, int column)
		{
			try
			{
				return new BooleanLiteral(line, column, false);
			} catch (ProgramException e)
			{
				e.printStackTrace();
			}
			return null;
		}
	},
	TYPE_DOUBLE
	{
		@Override
		public ConstantExpression<Double> defaultValue (int line, int column)
		{
			try
			{
				return new DoubleLiteral(line, column, 0.0);
			} catch (ProgramException e)
			{
				e.printStackTrace();
			}
			return null;
		}
	},
	TYPE_ENTITY
	{
		@Override
		public ConstantExpression<Entity> defaultValue (int line, int column)
		{
			try
			{
				return new EntityLiteral(line, column, null);
			} catch (ProgramException e)
			{
				e.printStackTrace();
			}
			return null;
		}
	};

	public abstract ConstantExpression<?> defaultValue (int line, int column);
}
