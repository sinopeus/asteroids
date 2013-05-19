package model.programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Variable;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.statement.Assignment;
import model.programs.parsing.language.statement.Print;
import model.programs.parsing.language.statement.Sequence;
import model.programs.parsing.language.statement.Statement;
import model.programs.parsing.language.statement.action.Fire;
import model.programs.parsing.language.statement.action.Skip;

import org.antlr.runtime.RecognitionException;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings ("javadoc")
public class ProgramTest
{
	@Before
	public void setUpTestFixture () throws ProgramException, RecognitionException
	{
		testGlobals = new HashMap <String, Type>();
		testGlobals.put("a", Type.TYPE_BOOLEAN);
		testGlobals.put("b", Type.TYPE_DOUBLE);
		testGlobals.put("c", Type.TYPE_ENTITY);
		testStatements = new ArrayList<Statement>();
		testStatements.add(new Assignment(0, 0, new Variable(0, 0, "a"), new BooleanLiteral(0, 0, true)));
		testStatements.add(new Print(1, 0, new Variable(1,0,"b")));
		testStatements.add(new Fire(2,0));
		testStatements.add(new Skip(3, 0));
		testStatement = new Sequence(0, 0, testStatements);
		testProgram = new Program(testGlobals, testStatement);
	}

	private static Map <String, Type>	testGlobals;
	private static List<Statement> testStatements;
	private static Statement testStatement;
	private static Program testProgram;

	@Test
	public void constructorTest_perfectParams ()
	{
		System.out.println("untested");
	}

	@Test
	public void constructorTest_nullMap ()
	{
		System.out.println("untested");
	}

	@Test
	public void constructorTest_nullStatement ()
	{
		System.out.println("untested");
	}

	@Test
	public void setGlobalTypesTest_perfectParams ()
	{
		System.out.println("untested");
	}

	@Test
	public void setGlobalTypesTest_nullMap ()
	{
		System.out.println("untested");
	}

	@Test
	public void setGlobalValuesTest_perfectParams ()
	{
		System.out.println("untested");
	}

	@Test
	public void setGlobalValuesTest_nullMap ()
	{
		System.out.println("untested");
	}

	@Test
	public void canHaveAsGlobalsTest ()
	{
		System.out.println("untested");
	}

	@Test
	public void canHaveAsStatementTest ()
	{
		System.out.println("untested");
	}

	@Test
	public void setStatementTest_perfectParams ()
	{
		System.out.println("untested");
	}

	@Test
	public void setStatementTest_nullParams ()
	{
		System.out.println("untested");
	}

	@Test
	public void canHaveAsOwnerTest ()
	{
		System.out.println("untested");
	}

	@Test
	public void setOwnerTest ()
	{
		System.out.println("untested");
	}

	@Test
	public void getVariableNamedTest_perfectParams ()
	{
		System.out.println("untested");
	}

	@Test
	public void getVariableNamedTest_nullParams ()
	{
		System.out.println("untested");
	}

	@Test
	public void setVariableValueTest_perfectParams ()
	{
		System.out.println("untested");
	}

	@Test
	public void setVariableValueTest_nullValue ()
	{
		System.out.println("untested");
	}

	@Test
	public void setVariableValueTest_nullName ()
	{
		System.out.println("untested");
	}

	@Test
	public void executeUntilAfterNextActionTest ()
	{
		System.out.println("untested");
	}
	
	@Test
	public void toStringTest ()
	{
		testProgram.toString();
	}
}
