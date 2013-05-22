package model.programs.parsing.language;

import model.programs.parsing.language.expression.constant.ConstantExpression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;
import world.entity.Entity;

public enum Type
{
	TYPE_BOOLEAN
	{
		@Override
		public ConstantExpression <Boolean> defaultValue (int line, int column)
		{
			return new BooleanLiteral(line, column, false);
		}
	},
	TYPE_DOUBLE
	{
		@Override
		public ConstantExpression <Double> defaultValue (int line, int column)
		{
			return new DoubleLiteral(line, column, 0.0);
		}
	},
	TYPE_ENTITY
	{
		@Override
		public ConstantExpression <Entity> defaultValue (int line, int column)
		{
			return new EntityLiteral(line, column, null);
		}
	};

	public abstract ConstantExpression <?> defaultValue (int line, int column);
}
