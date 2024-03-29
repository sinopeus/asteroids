package model.programs.parsing;

import java.util.ArrayList;
import java.util.List;

import model.programs.parsing.language.expression.constant.literal.EntityLiteral;
import world.World;
import world.entity.Asteroid;
import world.entity.Bullet;
import world.entity.Entity;
import world.entity.ship.Ship;

/**
 * Factory for creating expressions, statements and types.
 */
public interface ProgramFactory <E, S, T>
{
	public enum ForeachType
	{
		SHIP
		{
			@Override
			public ArrayList <EntityLiteral> getSelectionFromWorld (int line, int column, World world)
			{
				ArrayList <EntityLiteral> selection = new ArrayList <EntityLiteral>();
				for (Entity e : world)
					if (e instanceof Ship) selection.add(new EntityLiteral(line, column, e));
				return selection;
			}
		},
		ASTEROID
		{
			@Override
			public ArrayList <EntityLiteral> getSelectionFromWorld (int line, int column, World world)
			{
				ArrayList <EntityLiteral> selection = new ArrayList <EntityLiteral>();
				for (Entity e : world)
					if (e instanceof Asteroid) selection.add(new EntityLiteral(line, column, e));
				return selection;
			}
		},
		BULLET
		{
			@Override
			public ArrayList <EntityLiteral> getSelectionFromWorld (int line, int column, World world)
			{
				ArrayList <EntityLiteral> selection = new ArrayList <EntityLiteral>();
				for (Entity e : world)
					if (e instanceof Bullet) selection.add(new EntityLiteral(line, column, e));

				return selection;
			}
		},
		ANY
		{
			@Override
			public ArrayList <EntityLiteral> getSelectionFromWorld (int line, int column, World world)
			{
				ArrayList <EntityLiteral> selection = new ArrayList <EntityLiteral>();
				for (Entity e : world)
					selection.add(new EntityLiteral(line, column, e));
				return selection;
			}
		};
		public abstract ArrayList <EntityLiteral> getSelectionFromWorld (int line, int column, World world);

	}

	public E createDoubleLiteral (int line, int column, double d);

	public E createBooleanLiteral (int line, int column, boolean b);

	public E createAnd (int line, int column, E e1, E e2);

	public E createOr (int line, int column, E e1, E e2);

	public E createNot (int line, int column, E e);

	public E createNull (int line, int column);

	public E createSelf (int line, int column);

	public E createGetX (int line, int column, E e);

	public E createGetY (int line, int column, E e);

	public E createGetVX (int line, int column, E e);

	public E createGetVY (int line, int column, E e);

	public E createGetRadius (int line, int column, E e);

	public E createVariable (int line, int column, String name);

	public E createLessThan (int line, int column, E e1, E e2);

	public E createGreaterThan (int line, int column, E e1, E e2);

	public E createLessThanOrEqualTo (int line, int column, E e1, E e2);

	public E createGreaterThanOrEqualTo (int line, int column, E e1, E e2);

	public E createEquality (int line, int column, E e1, E e2);

	public E createInequality (int line, int column, E e1, E e2);

	public E createAdd (int line, int column, E e1, E e2);

	public E createSubtraction (int line, int column, E e1, E e2);

	public E createMul (int line, int column, E e1, E e2);

	public E createDivision (int line, int column, E e1, E e2);

	public E createSqrt (int line, int column, E e);

	public E createGetDirection (int line, int column);

	public E createSin (int line, int column, E e);

	public E createCos (int line, int column, E e);

	public S createEnableThruster (int line, int column);

	public S createDisableThruster (int line, int column);

	public S createFire (int line, int column);

	public S createTurn (int line, int column, E angle);

	public S createAssignment (int line, int column, String variable, E rhs);

	public S createIf (int line, int column, E condition, S then, S otherwise);

	public S createWhile (int line, int column, E condition, S body);

	public S createForeach (int line, int column, ForeachType type, String variableName, S body);

	public S createSkip (int line, int column);

	public S createSequence (int line, int column, List <S> statements);

	public S createPrint (int line, int column, E e);

	public T createDoubleType ();

	public T createBooleanType ();

	public T createEntityType ();
}
