package model.programs.parsing.language;

import model.programs.parsing.language.expression.BooleanLiteral;
import model.programs.parsing.language.expression.ConstantExpression;
import model.programs.parsing.language.expression.DoubleLiteral;
import model.programs.parsing.language.expression.EntityLiteral;

public enum Type
{
	TYPE_BOOLEAN {
		@Override
		public ConstantExpression defaultValue (int line,int column)
		{
			return new BooleanLiteral(line,column, false);
		}
	}, TYPE_DOUBLE {
		@Override
		public ConstantExpression defaultValue (int line,int column)
		{
			return new DoubleLiteral(line,column, 0.0);
		}
	}, TYPE_ENTITY {
		@Override
		public ConstantExpression defaultValue (int line,int column)
		{
			return new EntityLiteral(line, column, null);
		}
	};
	
	public abstract ConstantExpression defaultValue(int line,int column);
}
