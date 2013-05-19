package model.programs.parsing;

import java.util.List;

import model.programs.parsing.language.ProgramException;
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
		try
		{
			return new DoubleLiteral(line, column, d);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createBooleanLiteral (int line, int column, boolean b)
	{
		try
		{
			return new BooleanLiteral(line, column, b);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createAnd (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new And(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createOr (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new Or(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createNot (int line, int column, Expression e)
	{
		try
		{
			return new Not(line, column, e);
		} catch (ProgramException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createNull (int line, int column)
	{
		try
		{
			return new Null(line, column);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createSelf (int line, int column)
	{
		try
		{
			return new Self(line, column);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createGetX (int line, int column, Expression e)
	{
		try
		{
			return new GetX(line, column, e);
		} catch (ProgramException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createGetY (int line, int column, Expression e)
	{
		try
		{
			return new GetY(line, column, e);
		} catch (ProgramException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createGetVX (int line, int column, Expression e)
	{
		try
		{
			return new GetVX(line, column, e);
		} catch (ProgramException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createGetVY (int line, int column, Expression e)
	{
		try
		{
			return new GetVY(line, column, e);
		} catch (ProgramException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createGetRadius (int line, int column, Expression e)
	{
		try
		{
			return new GetRadius(line, column, e);
		} catch (ProgramException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createVariable (int line, int column, String name)
	{
		try
		{
			return new Variable(line, column, name);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createLessThan (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new LT(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createGreaterThan (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new GT(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createLessThanOrEqualTo (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new LE(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createGreaterThanOrEqualTo (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new GE(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createEquality (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new Equals(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createInequality (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new NotEquals(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createAdd (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new Addition(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createSubtraction (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new Subtraction(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createMul (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new Multiplication(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createDivision (int line, int column, Expression e1, Expression e2)
	{
		try
		{
			return new Division(line, column, e1, e2);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createSqrt (int line, int column, Expression e)
	{
		try
		{
			return new SquareRoot(line, column, e);
		} catch (ProgramException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createGetDirection (int line, int column)
	{
		try
		{
			return new GetDirection(line, column);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createSin (int line, int column, Expression e)
	{
		try
		{
			return new Sine(line, column, e);
		} catch (ProgramException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression createCos (int line, int column, Expression e)
	{
		try
		{
			return new Cosine(line, column, e);
		} catch (ProgramException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Statement createEnableThruster (int line, int column)
	{
		try
		{
			return new ThrustOn(line, column);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statement createDisableThruster (int line, int column)
	{
		try
		{
			return new ThrustOff(line, column);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statement createFire (int line, int column)
	{
		try
		{
			return new Fire(line, column);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statement createTurn (int line, int column, Expression angle)
	{
		try
		{
			return new Turn(line, column, angle);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statement createAssignment (int line, int column, String variable, Expression rhs)
	{
		try
		{
			Variable var = new Variable(line, column, variable);
			return new Assignment(line, column, var, rhs);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statement createIf (int line, int column, Expression condition, Statement then, Statement otherwise)
	{
		try
		{
			return new If(line, column, condition, then, otherwise);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statement createWhile (int line, int column, Expression condition, Statement body)
	{
		try
		{
			return new While(line, column, condition, body);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statement createForeach (int line, int column, ProgramFactory.ForeachType type, String variableName, Statement body)
	{
		try
		{
			return new ForEach(line, column, type, variableName, body);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statement createSkip (int line, int column)
	{
		try
		{
			return new Skip(line, column);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statement createSequence (int line, int column, List <Statement> statements)
	{
		try
		{
			return new Sequence(line, column, statements);
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statement createPrint (int line, int column, Expression e)
	{
		try
		{
			return new Print(line, column, e);
		} catch (ProgramException e1)
		{
			e1.printStackTrace();
		}
		return null;
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
