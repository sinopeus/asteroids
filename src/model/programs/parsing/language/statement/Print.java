package model.programs.parsing.language.statement;

import model.programs.parsing.language.expression.Expression;

public class Print extends Statement
{
	public Print (int line, int column, Expression textExpression)
	{
		super(line, column);
		setTextExpression(textExpression);
	}

	private Expression	textExpression;

	private Expression getTextExpression ()
	{
		return textExpression;
	}

	private boolean canHaveAsTextExpression (Expression textExpression)
	{
		return (textExpression != null); //TODO more checking?
	}

	public void setTextExpression (Expression textExpression)
	{
		if (!canHaveAsTextExpression(textExpression)) throw new IllegalArgumentException("Invalid expression for print statement."); //TODO other message and/or exception?
		this.textExpression = textExpression;
	}

	private String	text;

	public String getText ()
	{
		return text;
	}

	public boolean canHaveAsText (String text)
	{
		return (text != null);//TODO more checking?
	}

	public void setText (String text)
	{
		if (!canHaveAsText(text)) throw new IllegalArgumentException("Invalid text provided for print statement.");
		this.text = text;
	}

	@Override
	public boolean execute ()
	{
		super.execute();
		setText(getTextExpression().evaluate().toString());
		System.out.println(getText());
		finish();
		return false;
	}
}
