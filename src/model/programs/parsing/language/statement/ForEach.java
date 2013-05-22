package model.programs.parsing.language.statement;

import java.util.ArrayList;

import model.IFacade.TypeCheckOutcome;
import model.programs.Program;
import model.programs.parsing.ProgramFactory.ForeachType;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;

public class ForEach extends Statement
{
	public ForEach (int line, int column, ForeachType type, String variableName, Statement body) throws IllegalArgumentException
	{
		super(line, column);
		setType(type);
		setVariableName(variableName);
		setBody(body);
		this.AtStartOfIteration = true;
	}

	private ForeachType	type;

	protected ForeachType getType ()
	{
		return type;
	}

	protected static boolean canHaveAsType (ForeachType type)
	{
		return (type != null);
	}

	protected void setType (ForeachType type)
	{
		if (!canHaveAsType(type)) throw new IllegalArgumentException("Invalid type for foreach statement");
		this.type = type;
	}

	String	variableName;

	protected String getVariableName ()
	{
		return variableName;
	}

	protected static boolean canHaveAsVariable (String variableName)
	{
		return (variableName != null);
	}

	protected void setVariableName (String variableName)
	{
		if (!canHaveAsVariable(variableName)) throw new IllegalArgumentException("Invalid variable  name provided for foreach statement.");
		this.variableName = variableName;
	}

	Statement	body;

	protected Statement getBody ()
	{
		return body;
	}

	protected static boolean canHaveAsBody (Statement body)
	{
		return (body != null);
	}

	protected void setBody (Statement body)
	{
		if (!canHaveAsBody(body)) throw new IllegalArgumentException("Invalid body provided for foreach statement.");//text & type
		this.body = body;
	}

	private ArrayList <EntityLiteral>	selection;

	protected ArrayList <EntityLiteral> getSelection ()
	{
		return selection;
	}

	protected static boolean canHaveAsSelection (ArrayList <EntityLiteral> selection)
	{
		return (selection != null);
	}

	protected void calculateSelection ()
	{
		this.selection = getType().getSelectionFromWorld(getCurrentIndex(), getColumn(), getOwnerShip().getWorld());
	}

	private int	currentIndex;

	protected int getCurrentIndex ()
	{
		return currentIndex;
	}

	protected boolean canHaveAsCurrentIndex (int selectedIndex)
	{
		if (getSelection().size() == 0) return true;
		return ( (selectedIndex >= 0) && (selectedIndex < getSelection().size()));
	}

	protected void setSelectedIndex (int selectedIndex)
	{
		if (!canHaveAsCurrentIndex(selectedIndex)) throw new IllegalArgumentException("Illegal selected index for sequence " + selectedIndex);
		this.currentIndex = selectedIndex;
	}

	protected void incrementIndex ()
	{
		if (getCurrentIndex() >= getSelection().size() - 1)
		{
			finish();
			return;
		}
		setSelectedIndex(getCurrentIndex() + 1);
		getBody().unfinish();
	}

	private boolean	AtStartOfIteration;

	protected boolean isAtStartOfIteration ()
	{
		return AtStartOfIteration;
	}

	protected void startIteration ()
	{
		getParentProgram().setVariableValue(getVariableName(), getSelection().get(getCurrentIndex()));
		AtStartOfIteration = false;
	}

	protected void finishIteration ()
	{
		AtStartOfIteration = true;
	}

	@Override
	public void setParentProgram (Program parrentProgram) throws IllegalArgumentException
	{
		super.setParentProgram(parrentProgram);
		getBody().setParentProgram(parrentProgram);
	}

	@Override
	public void unfinish ()
	{
		getBody().unfinish();
		setSelectedIndex(0);
		calculateSelection();
		AtStartOfIteration = true;
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
			if (isAtStartOfIteration()) startIteration();
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
	public TypeCheckOutcome isTypeSafe ()
	{
		boolean variableIsEntity = getParentProgram().getVariableNamed(getVariableName()).getType() == Type.TYPE_ENTITY;
		if (!variableIsEntity) return TypeCheckOutcome.failure("The foreach at " + getLine() + ", " + getColumn() + " is not type safe.");
		TypeCheckOutcome bodyIsTypeSafe = getBody().isTypeSafe();
		if (!bodyIsTypeSafe.isSuccessful()) return bodyIsTypeSafe;
		boolean bodyConstainsAction = getBody().containsAction();
		if (bodyConstainsAction) return TypeCheckOutcome.failure("The body of the foreach at " + getLine() + ", " + getColumn() + " contains (an) action(s).");
		return TypeCheckOutcome.success();
	}

	@Override
	public boolean containsAction ()
	{
		return true;
	}

	@Override
	public String toString ()
	{
		return "ForEach [type=" + type + ", variableName=" + variableName + ", body=" + body + "]";
	}
}
