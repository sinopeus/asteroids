package model.programs.parsing.language.statement;

import java.util.List;

import model.programs.Program;
import model.programs.ProgramException;

public class Sequence extends Statement
{

	public Sequence (int line, int column, List <Statement> sequence) throws ProgramException
	{
		super(line, column);
		setSequence(sequence);
	}

	private List <Statement>	sequence;

	protected List <Statement> getSequence ()
	{
		return sequence;
	}

	protected boolean canHaveAsSequence (List <Statement> sequence)
	{
		return (sequence != null);//TODO more checking?
	}

	protected void setSequence (List <Statement> sequence)
	{
		if (!canHaveAsSequence(sequence)) throw new IllegalArgumentException("Invalid sequence of statements.");//TODO other message and/or exception.
		this.sequence = sequence;
	}

	private int	selectedIndex;

	protected int getSelectedIndex ()
	{
		return selectedIndex;
	}

	protected boolean canHaveAsSelectedIndex (int selectedIndex)
	{
		if(getSequence().size()==0) return true;
		return ( (selectedIndex >= 0) && (selectedIndex < getSequence().size()));
	}

	protected void setSelectedIndex (int selectedIndex)
	{
		if (!canHaveAsSelectedIndex(selectedIndex)) throw new IllegalArgumentException("Illegal selected index for sequence " + selectedIndex);
		this.selectedIndex = selectedIndex;
	}

	protected void incrementIndex ()
	{
		if (getSelectedIndex() >= getSequence().size() - 1)
		{
			finish();
			return;
		}
		setSelectedIndex(getSelectedIndex() + 1);
	}

	protected Statement getCurrentStatement ()
	{
		return getSequence().get(getSelectedIndex());
	}
	
	@Override
	public void setParentProgram (Program parrentProgram) throws ProgramException
	{
		super.setParentProgram(parrentProgram);
		for (Statement s : getSequence())
			s.setParentProgram(parrentProgram);
	}

	@Override
	protected void finish ()
	{
		for (Statement s : getSequence())
			s.finish();
		super.finish();
	}

	@Override
	public void unfinish ()
	{
		for (Statement s : getSequence())
			s.unfinish();
		super.unfinish();
		setSelectedIndex(0);
	}

	@Override
	public boolean execute ()
	{
		super.execute();
		if (getSequence().isEmpty())
		{
			finish();
			return false;
		}
		while (!getCurrentStatement().isFinished() && !this.isFinished())
		{
			if (getCurrentStatement().execute())
			{
				if (getCurrentStatement().isFinished())
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
		}
		finish();
		return false;
	}

	@Override
	public boolean isTypeSafe ()
	{
		for (Statement s : getSequence())
			if(!s.isTypeSafe())
				return false;
		return true;
	}
	
	@Override
	public boolean containsAction ()
	{
		for (Statement s : getSequence())
			if(s.containsAction())
				return true;
		return false;
	}
	
	@Override
	public String toString ()
	{
		return "Sequence [sequence=" + sequence + ", selectedIndex=" + selectedIndex + ", isFinished()=" + isFinished() + "]";
	}
}
