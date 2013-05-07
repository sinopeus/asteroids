package model.programs.parsing.language.statement;

import java.util.ArrayList;

import model.programs.parsing.ProgramFactory;
import model.programs.parsing.language.expression.EntityLiteral;
import model.programs.parsing.language.expression.Variable;
import world.World;
import world.entity.Asteroid;
import world.entity.Bullet;
import world.entity.Entity;
import world.entity.ship.Ship;

public class ForEach extends Statement
{
	public ForEach (int line, int column, ProgramFactory.ForeachType type, String variableName, Statement body)
	{
		super(line, column);
		setVariable(new Variable <EntityLiteral,Entity>(line, column, variableName));
		setBody(body);
	}

	Variable <EntityLiteral,Entity>	variable;

	public Variable <EntityLiteral,Entity> getVariable ()
	{
		return variable;
	}

	protected boolean canHaveAsVariable (Variable <EntityLiteral,Entity> variable)
	{
		return (variable != null); //TODO more checking.
	}

	private void setVariable (Variable <EntityLiteral,Entity> variable)
	{
		if (!canHaveAsVariable(variable)) throw new IllegalArgumentException("Invalid variable provided for foreach statement.");//text & type
		this.variable = variable;
	}

	Statement	body;

	public Statement getBody ()
	{
		return body;
	}

	protected boolean canHaveAsBody (Statement body)
	{
		return (body != null);//TODO more checking?
	}

	private void setBody (Statement body)
	{
		if (!canHaveAsBody(body)) throw new IllegalArgumentException("Invalid body provided for foreach statement.");//text & type
		this.body = body;
	}

	private ArrayList <EntityLiteral>	selection;

	public ArrayList <EntityLiteral> getSelection ()
	{
		return selection;
	}

	protected boolean canHaveAsSelection (ArrayList <EntityLiteral> selection)
	{
		return (selection != null);//TODO more checking?
	}

	private void calculateSelection (ProgramFactory.ForeachType type)
	{
		ArrayList <EntityLiteral> selection = new ArrayList <EntityLiteral>();
		World w = null; //TODO change this into something useful.
		switch (type)
		{
			case SHIP:
				for (Entity e : w)
					if (e instanceof Ship) selection.add(new EntityLiteral(getLine(), getColumn(), e));
				break;
			case ASTEROID:
				for (Entity e : w)
					if (e instanceof Asteroid) selection.add(new EntityLiteral(getLine(), getColumn(), e));
				break;
			case BULLET:
				for (Entity e : w)
					if (e instanceof Bullet) selection.add(new EntityLiteral(getLine(), getColumn(), e));
				break;
			case ANY:
				for (Entity e : w)
					if (e instanceof Entity) selection.add(new EntityLiteral(getLine(), getColumn(), e));
				break;
			default:
				throw new RuntimeException();
		}
		this.selection = selection;
	}

	public boolean execute ()
	{
		return false;
	}
}
