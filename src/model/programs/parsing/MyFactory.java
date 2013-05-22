package model.programs.parsing;

import java.util.List;

import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.Variable;
import model.programs.parsing.language.expression.constant.GetDirection;
import model.programs.parsing.language.expression.constant.Null;
import model.programs.parsing.language.expression.constant.Self;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.first_order.Cosine;
import model.programs.parsing.language.expression.first_order.GetRadius;
import model.programs.parsing.language.expression.first_order.GetVX;
import model.programs.parsing.language.expression.first_order.GetVY;
import model.programs.parsing.language.expression.first_order.GetX;
import model.programs.parsing.language.expression.first_order.GetY;
import model.programs.parsing.language.expression.first_order.Not;
import model.programs.parsing.language.expression.first_order.Sine;
import model.programs.parsing.language.expression.first_order.SquareRoot;
import model.programs.parsing.language.expression.second_order.Addition;
import model.programs.parsing.language.expression.second_order.And;
import model.programs.parsing.language.expression.second_order.Division;
import model.programs.parsing.language.expression.second_order.Equals;
import model.programs.parsing.language.expression.second_order.GE;
import model.programs.parsing.language.expression.second_order.GT;
import model.programs.parsing.language.expression.second_order.LE;
import model.programs.parsing.language.expression.second_order.LT;
import model.programs.parsing.language.expression.second_order.Multiplication;
import model.programs.parsing.language.expression.second_order.NotEquals;
import model.programs.parsing.language.expression.second_order.Or;
import model.programs.parsing.language.expression.second_order.Subtraction;
import model.programs.parsing.language.statement.Assignment;
import model.programs.parsing.language.statement.ForEach;
import model.programs.parsing.language.statement.If;
import model.programs.parsing.language.statement.Print;
import model.programs.parsing.language.statement.Sequence;
import model.programs.parsing.language.statement.Statement;
import model.programs.parsing.language.statement.While;
import model.programs.parsing.language.statement.action.Fire;
import model.programs.parsing.language.statement.action.Skip;
import model.programs.parsing.language.statement.action.ThrustOff;
import model.programs.parsing.language.statement.action.ThrustOn;
import model.programs.parsing.language.statement.action.Turn;

public class MyFactory implements ProgramFactory <Expression, Statement, Type>
{
	@Override
	public Expression createDoubleLiteral (int line, int column, double d)
	{
		
		return new DoubleLiteral(line, column, d);
	}

	@Override
	public Expression createBooleanLiteral (int line, int column, boolean b)
	{
		return new BooleanLiteral(line, column, b);
	}

	@Override
	public Expression createAnd (int line, int column, Expression e1, Expression e2)
	{
		return new And(line, column, e1, e2);
	}

	@Override
	public Expression createOr (int line, int column, Expression e1, Expression e2)
	{
		return new Or(line, column, e1, e2);
	}

	@Override
	public Expression createNot (int line, int column, Expression e)
	{
		return new Not(line, column, e);
	}

	@Override
	public Expression createNull (int line, int column)
	{
		return new Null(line, column);
	}

	@Override
	public Expression createSelf (int line, int column)
	{
		return new Self(line, column);
	}

	@Override
	public Expression createGetX (int line, int column, Expression e)
	{
		return new GetX(line, column, e);
	}

	@Override
	public Expression createGetY (int line, int column, Expression e)
	{
		return new GetY(line, column, e);
	}

	@Override
	public Expression createGetVX (int line, int column, Expression e)
	{
		return new GetVX(line, column, e);
	}

	@Override
	public Expression createGetVY (int line, int column, Expression e)
	{
		return new GetVY(line, column, e);
	}

	@Override
	public Expression createGetRadius (int line, int column, Expression e)
	{
		return new GetRadius(line, column, e);
	}

	@Override
	public Expression createVariable (int line, int column, String name)
	{
		return new Variable(line, column, name);
	}

	@Override
	public Expression createLessThan (int line, int column, Expression e1, Expression e2)
	{
		return new LT(line, column, e1, e2);
	}

	@Override
	public Expression createGreaterThan (int line, int column, Expression e1, Expression e2)
	{
		return new GT(line, column, e1, e2);
	}

	@Override
	public Expression createLessThanOrEqualTo (int line, int column, Expression e1, Expression e2)
	{
		return new LE(line, column, e1, e2);
	}

	@Override
	public Expression createGreaterThanOrEqualTo (int line, int column, Expression e1, Expression e2)
	{
		return new GE(line, column, e1, e2);
	}

	@Override
	public Expression createEquality (int line, int column, Expression e1, Expression e2)
	{
		return new Equals(line, column, e1, e2);
	}

	@Override
	public Expression createInequality (int line, int column, Expression e1, Expression e2)
	{
		return new NotEquals(line, column, e1, e2);
	}

	@Override
	public Expression createAdd (int line, int column, Expression e1, Expression e2)
	{
		return new Addition(line, column, e1, e2);
	}

	@Override
	public Expression createSubtraction (int line, int column, Expression e1, Expression e2)
	{
		return new Subtraction(line, column, e1, e2);
	}

	@Override
	public Expression createMul (int line, int column, Expression e1, Expression e2)
	{
		return new Multiplication(line, column, e1, e2);
	}

	@Override
	public Expression createDivision (int line, int column, Expression e1, Expression e2)
	{
		return new Division(line, column, e1, e2);
	}

	@Override
	public Expression createSqrt (int line, int column, Expression e)
	{
		return new SquareRoot(line, column, e);
	}

	@Override
	public Expression createGetDirection (int line, int column)
	{
		return new GetDirection(line, column);
	}

	@Override
	public Expression createSin (int line, int column, Expression e)
	{
		return new Sine(line, column, e);
	}

	@Override
	public Expression createCos (int line, int column, Expression e)
	{
		return new Cosine(line, column, e);
	}

	@Override
	public Statement createEnableThruster (int line, int column)
	{
		return new ThrustOn(line, column);
	}

	@Override
	public Statement createDisableThruster (int line, int column)
	{
		return new ThrustOff(line, column);
	}

	@Override
	public Statement createFire (int line, int column)
	{
		return new Fire(line, column);
	}

	@Override
	public Statement createTurn (int line, int column, Expression angle)
	{
		return new Turn(line, column, angle);
	}

	@Override
	public Statement createAssignment (int line, int column, String variable, Expression rhs)
	{
		Variable var = new Variable(line, column, variable);
		return new Assignment(line, column, var, rhs);
	}

	@Override
	public Statement createIf (int line, int column, Expression condition, Statement then, Statement otherwise)
	{
		return new If(line, column, condition, then, otherwise);
	}

	@Override
	public Statement createWhile (int line, int column, Expression condition, Statement body)
	{
		return new While(line, column, condition, body);
	}

	@Override
	public Statement createForeach (int line, int column, ProgramFactory.ForeachType type, String variableName, Statement body)
	{
		return new ForEach(line, column, type, variableName, body);
	}

	@Override
	public Statement createSkip (int line, int column)
	{
		return new Skip(line, column);
	}

	@Override
	public Statement createSequence (int line, int column, List <Statement> statements)
	{
		return new Sequence(line, column, statements);
	}

	@Override
	public Statement createPrint (int line, int column, Expression e)
	{
		return new Print(line, column, e);
	}

	@Override
	public Type createDoubleType ()
	{
		return Type.TYPE_DOUBLE;
	}

	@Override
	public Type createBooleanType ()
	{
		return Type.TYPE_BOOLEAN;
	}

	@Override
	public Type createEntityType ()
	{
		return Type.TYPE_ENTITY;
	}
}