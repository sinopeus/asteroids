package model.programs.parsing.language.statement;

import model.IFacade.TypeCheckOutcome;
import model.programs.Program;
import model.programs.ProgramException;
import model.programs.parsing.language.expression.Expression;

public class Print extends Statement
{
	public Print (int line, int column, Expression textExpression) throws IllegalArgumentException
	{
		super(line, column);
		setTextExpression(textExpression);
	}

	private Expression	textExpression;

	protected Expression getTextExpression ()
	{
		return textExpression;
	}

	protected boolean canHaveAsTextExpression (Expression textExpression)
	{
		return (textExpression != null); //TODO more checking?
	}

	protected void setTextExpression (Expression textExpression)
	{
		if (!canHaveAsTextExpression(textExpression)) throw new IllegalArgumentException("Invalid expression for print statement."); //TODO other message and/or exception?
		this.textExpression = textExpression;
	}

	private String	text;

	protected String getText ()
	{
		return text;
	}

	protected boolean canHaveAsText (String text)
	{
		return (text != null);//TODO more checking?
	}

	protected void setText (String text)
	{
		if (!canHaveAsText(text)) throw new IllegalArgumentException("Invalid text provided for print statement.");
		this.text = text;
	}

	@Override
	public void setParentProgram (Program parrentProgram) throws IllegalArgumentException
	{
		super.setParentProgram(parrentProgram);
		getTextExpression().setParentProgram(parrentProgram);
	}
	
	@Override
	public boolean execute ()
	{
		super.execute();
		setText(getTextExpression().evaluate().getValue().toString());
		System.out.println(getText()); //DON'T REMOVE THIS.
		finish();
		return false;
	}
	
	@Override
	public TypeCheckOutcome isTypeSafe ()
	{
		return getTextExpression().isTypeSafe();
	}
	
	@Override
	public boolean containsAction ()
	{
		return false;
	}

	@Override
	public String toString ()
	{
		return "Print [textExpression=" + textExpression + ", text=" + text + "]";
	}

}
