package model.programs.parsing.language.statement;

public class Print extends Statement
{
	public Print (int line, int column)
	{
		super(line, column);
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
	public void executeStep ()
	{
		super.executeStep();
		System.out.println(getText());
		finish();
	}
}
