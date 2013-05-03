package model.programs.parsing;

import java.util.List;

import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.statement.Statement;

public class MyFactory implements ProgramFactory<Expression, Statement, Type> {

	@Override
	public Expression createDoubleLiteral (int line, int column, double d)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createBooleanLiteral (int line, int column, boolean b)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAnd (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createOr (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createNot (int line, int column, Expression e)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createNull (int line, int column)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSelf (int line, int column)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetX (int line, int column, Expression e)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetY (int line, int column, Expression e)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetVX (int line, int column, Expression e)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetVY (int line, int column, Expression e)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetRadius (int line, int column, Expression e)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createVariable (int line, int column, String name)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createLessThan (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGreaterThan (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createLessThanOrEqualTo (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGreaterThanOrEqualTo (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createEquality (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createInequality (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAdd (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSubtraction (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createMul (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createDivision (int line, int column, Expression e1, Expression e2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSqrt (int line, int column, Expression e)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetDirection (int line, int column)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSin (int line, int column, Expression e)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createCos (int line, int column, Expression e)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createEnableThruster (int line, int column)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createDisableThruster (int line, int column)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createFire (int line, int column)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createTurn (int line, int column, Expression angle)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignment (int line, int column, String variable, Expression rhs)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createIf (int line, int column, Expression condition, Statement then, Statement otherwise)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWhile (int line, int column, Expression condition, Statement body)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createForeach (int line, int column, model.programs.parsing.ProgramFactory.ForeachType type, String variableName, Statement body)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSkip (int line, int column)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSequence (int line, int column, List <Statement> statements)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createPrint (int line, int column, Expression e)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createDoubleType ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createBooleanType ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createEntityType ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	
}
