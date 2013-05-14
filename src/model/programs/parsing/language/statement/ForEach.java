package model.programs.parsing.language.statement;

import java.util.ArrayList;

import model.programs.Program;
import model.programs.parsing.ProgramFactory.ForeachType;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;
import world.World;
import world.entity.Asteroid;
import world.entity.Bullet;
import world.entity.Entity;
import world.entity.ship.Ship;

public class ForEach extends Statement
{
	public ForEach (int line, int column, ForeachType type, String variableName, Statement body)
	{
		super(line, column);
		setType(type);
		setVariableName(variableName);
		setBody(body);
		this.isAtStartOfIteration = true;
	}

	private ForeachType	type;

	public ForeachType getType ()
	{
		return type;
	}

	protected boolean canHaveAsType (ForeachType type)
	{
		return true;
	}

	public void setType (ForeachType type)
	{
		if (!canHaveAsType(type)) throw new IllegalArgumentException("Invalid type for foreach statement");
		this.type = type;
	}

	String	variableName;

	public String getVariableName ()
	{
		return variableName;
	}

	protected boolean canHaveAsVariable (String variableName)
	{
		return (variableName != null); //TODO more checking.
	}

	public void setVariableName (String variableName)
	{
		if (!canHaveAsVariable(variableName)) throw new IllegalArgumentException("Invalid variable  name provided for foreach statement.");
		this.variableName = variableName;
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

	private void calculateSelection () //TODO can I change the enum in programfactory?
	{
		ArrayList <EntityLiteral> selection = new ArrayList <EntityLiteral>();
		World w = getOwnerShip().getWorld();
		switch (getType())
		{
			case SHIP:
				for (Entity e : w)
				{
					if (e == getOwnerShip()) continue; //TODO  remove this?
					if (e instanceof Ship) selection.add(new EntityLiteral(getLine(), getColumn(), e));
				}
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

	private int	currentIndex;

	public int getCurrentIndex ()
	{
		return currentIndex;
	}

	protected boolean canHaveAsCurrentIndex (int selectedIndex)
	{
		if (getSelection().size() == 0) return true;
		return ( (selectedIndex >= 0) && (selectedIndex < getSelection().size()));
	}

	private void setSelectedIndex (int selectedIndex)
	{
		if (!canHaveAsCurrentIndex(selectedIndex)) throw new IllegalArgumentException("Illegal selected index for sequence " + selectedIndex);
		this.currentIndex = selectedIndex;
	}

	private void incrementIndex ()
	{
		if (getCurrentIndex() >= getSelection().size() - 1)
		{
			finish();
			return;
		}
		setSelectedIndex(getCurrentIndex() + 1);
		getBody().unfinish();
	}

	private boolean	isAtStartOfIteration;

	private boolean isAtStartOfIteration ()
	{
		return isAtStartOfIteration;
	}

	private void startIteration ()
	{
		getParentProgram().setVariableValue(getVariableName(), getSelection().get(getCurrentIndex()));
		isAtStartOfIteration = false;
	}

	private void finishIteration ()
	{
		isAtStartOfIteration = true;
	}

	@Override
	public void setParentProgram (Program parrentProgram)
	{
		super.setParentProgram(parrentProgram);
		getBody().setParentProgram(parrentProgram);
	}

	@Override
	public void unfinish ()
	{
		getBody().unfinish();
		super.unfinish();
	}

	public boolean execute ()
	{
		super.execute();
		if (getSelection() == null) calculateSelection();
		if (getSelection().isEmpty())
		{
			finish();
			return false;
		}

		while (!this.isFinished())
		{
			if (isAtStartOfIteration) startIteration();
			if (getBody().execute())
			{
				if (getBody().isFinished())
				{
					incrementIndex();
					if (this.isFinished()) return true;
				}
				return true;
			} else
			{
				incrementIndex();
				if (this.isFinished()) return false;
			}
			finishIteration();
		}
		finish();
		return false;
	}

	@Override
	public String toString ()
	{
		return "ForEach [type=" + type + ", variableName=" + variableName + ", body=" + body + "]";
	}
}
