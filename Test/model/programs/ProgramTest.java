package model.programs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Variable;
import model.programs.parsing.language.expression.constant.ConstantExpression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;
import model.programs.parsing.language.statement.Assignment;
import model.programs.parsing.language.statement.Print;
import model.programs.parsing.language.statement.Sequence;
import model.programs.parsing.language.statement.Statement;
import model.programs.parsing.language.statement.action.Fire;
import model.programs.parsing.language.statement.action.Skip;

import org.antlr.runtime.RecognitionException;
import org.junit.Before;
import org.junit.Test;

import world.entity.ship.Ship;

@SuppressWarnings ("javadoc")
public class ProgramTest
{
	@Before
	public void setUpMutableTestFixture_Program () throws ProgramException, RecognitionException
	{
		testGlobals = new HashMap <String, Type>();
		testGlobals.put("a", Type.TYPE_BOOLEAN);
		testGlobals.put("b", Type.TYPE_DOUBLE);
		testGlobals.put("c", Type.TYPE_ENTITY);
		testStatements = new ArrayList <Statement>();
		testStatements.add(new Assignment(0, 0, new Variable(0, 0, "a"), new BooleanLiteral(0, 0, true)));
		testStatements.add(new Print(1, 0, new Variable(1, 0, "b")));
		testStatements.add(new Skip(2, 0));
		testStatements.add(new Skip(3, 0));
		testStatement = new Sequence(0, 0, testStatements);
		testProgram = new Program(testGlobals, testStatement);
	}

	private static Map <String, Type>	testGlobals;
	private static List <Statement>		testStatements;
	private static Statement			testStatement;
	private static Program				testProgram;

	@Test
	public void constructorTest_perfectParams () throws ProgramException, RecognitionException
	{
		Map <String, Type> g = new HashMap <String, Type>();
		g.put("a", Type.TYPE_BOOLEAN);
		g.put("b", Type.TYPE_DOUBLE);
		g.put("c", Type.TYPE_ENTITY);
		Statement s = new Fire(0, 0);
		Program program = new Program(g, s);

		assertEquals(g, program.getGlobalTypes());
		Map <String, ConstantExpression <?>> gv = new HashMap <String, ConstantExpression <?>>();
		gv.put("a", new BooleanLiteral(0, 0, false));
		gv.put("b", new DoubleLiteral(1, 0, 0.0));
		gv.put("c", new EntityLiteral(2, 0, null));
		assertEquals(gv, program.getGlobalValues());
		assertEquals(s, program.getStatement());
	}

	@Test (expected = RecognitionException.class)
	public void constructorTest_nullMap () throws ProgramException, RecognitionException
	{
		Statement s = new Fire(0, 0);
		Program program = new Program(null, s);
	}

	@Test (expected = RecognitionException.class)
	public void constructorTest_nullStatement () throws RecognitionException
	{
		Map <String, Type> g = new HashMap <String, Type>();
		g.put("a", Type.TYPE_BOOLEAN);
		g.put("b", Type.TYPE_DOUBLE);
		g.put("c", Type.TYPE_ENTITY);
		Program program = new Program(g, null);
	}

	@Test
	public void setGlobalTypesTest_perfectParams () throws RecognitionException
	{
		Map <String, Type> g = new HashMap <String, Type>();
		g.put("a", Type.TYPE_BOOLEAN);
		testProgram.setGlobalTypes(g);
		assertEquals(g, testProgram.getGlobalTypes());
	}

	@Test (expected = RecognitionException.class)
	public void setGlobalTypesTest_nullMap () throws RecognitionException
	{
		testProgram.setGlobalTypes(null);
	}

	@Test
	public void setGlobalValuesTest_perfectParams () throws RecognitionException, ProgramException
	{
		Map <String, Type> g = new HashMap <String, Type>();
		g.put("d", Type.TYPE_BOOLEAN);
		g.put("e", Type.TYPE_DOUBLE);
		g.put("f", Type.TYPE_ENTITY);
		testProgram.setGlobalValues(g);

		assertEquals(new BooleanLiteral(0, 0, false), testProgram.getGlobalValues().get("d"));
		assertEquals(new DoubleLiteral(0, 0, 0.0), testProgram.getGlobalValues().get("e"));
		assertEquals(new EntityLiteral(0, 0, null), testProgram.getGlobalValues().get("f"));
	}

	@Test (expected = RecognitionException.class)
	public void setGlobalValuesTest_nullMap () throws RecognitionException
	{
		testProgram.setGlobalValues(null);
	}

	@Test
	public void canHaveAsGlobalsTest ()
	{
		Map <String, Type> g = new HashMap <String, Type>();
		assertTrue(testProgram.canHaveAsGlobals(g));
		assertFalse(testProgram.canHaveAsGlobals(null));
	}

	@Test
	public void canHaveAsStatementTest () throws ProgramException
	{
		Statement s = new Fire(0, 0);
		assertTrue(testProgram.canHaveAsStatement(s));
		assertFalse(testProgram.canHaveAsStatement(null));
	}

	@Test
	public void setStatementTest_perfectParams () throws ProgramException, RecognitionException
	{
		Statement s = new Fire(0, 0);
		testProgram.setStatement(s);
		assertEquals(s, testProgram.getStatement());
		assertEquals(testProgram, s.getParentProgram());
	}

	@Test (expected = RecognitionException.class)
	public void setStatementTest_nullParams () throws RecognitionException
	{
		testProgram.setStatement(null);
	}

	@Test
	public void canHaveAsOwnerTest ()
	{
		Ship testOwner = new Ship();
		assertTrue(testProgram.canHaveAsOwner(testOwner));
		assertFalse(testProgram.canHaveAsOwner(null));
	}

	@Test
	public void setOwnerTest_PerfectParameters ()
	{
		Ship testOwner = new Ship();
		testProgram.setOwner(testOwner);
		assertEquals(testOwner, testProgram.getOwner());
	}

	@Test (expected = IllegalArgumentException.class)
	public void setOwnerTest_NullParameters ()
	{
		testProgram.setOwner(null);
	}

	@Test
	public void getVariableNamedTest_perfectParams () throws ProgramException
	{
		assertEquals(new BooleanLiteral(0, 0, false), testProgram.getVariableNamed("a"));
	}

	@Test (expected = IllegalArgumentException.class)
	public void getVariableNamedTest_nullParams ()
	{
		testProgram.getVariableNamed(null);
	}

	@Test
	public void setVariableValueTest_perfectParams () throws IllegalArgumentException, ProgramException
	{
		testProgram.setVariableValue("a", new BooleanLiteral(2, 0, true));
		assertEquals(new BooleanLiteral(2, 0, true), testProgram.getVariableNamed("a"));
	}

	@Test (expected = IllegalArgumentException.class)
	public void setVariableValueTest_nullValue ()
	{
		testProgram.setVariableValue("a", null);
	}

	@Test (expected = IllegalArgumentException.class)
	public void setVariableValueTest_nullName () throws IllegalArgumentException, ProgramException
	{
		testProgram.setVariableValue(null, new BooleanLiteral(2, 0, true));
	}

	@Test
	public void executeUntilAfterNextActionTest ()
	{
		testProgram.executeUntilAfterNextAction();
	}

	//TODO istypesafetest

	@Test
	public void toStringTest ()
	{
		testProgram.toString();
	}
}
