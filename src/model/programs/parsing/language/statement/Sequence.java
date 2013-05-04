package model.programs.parsing.language.statement;

import java.util.List;

public class Sequence extends Statement
{

	public Sequence (int line, int column, List <Statement> sequence)
	{
		super(line, column);
		setSequence(sequence);
	}

	private List <Statement>	sequence;

	private List <Statement> getSequence ()
	{
		return sequence;
	}

	private boolean canHaveAsSequence (List <Statement> sequence)
	{
		return (sequence != null);//TODO more checking?
	}

	private void setSequence (List <Statement> sequence)
	{
		if (!canHaveAsSequence(sequence)) throw new IllegalArgumentException("Invalid sequence of statements.");//TODO other message and/or exception.
		this.sequence = sequence;
	}
	
	public boolean executeUntilAction ()
	{
		return false; //TODO
	}
}
