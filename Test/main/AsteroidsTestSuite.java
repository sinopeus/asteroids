package main;

import model.FacadeTest;
import model.IFacadeTest;
import model.IShipTest;
import model.programs.ProgramTest;
import model.programs.parsing.AsteroidsParserBaseListenerTest;
import model.programs.parsing.AsteroidsParserLexerTest;
import model.programs.parsing.AsteroidsParserListenerTest;
import model.programs.parsing.AsteroidsParserMyListenerTest;
import model.programs.parsing.AsteroidsParserParserTest;
import model.programs.parsing.MyFactoryTest;
import model.programs.parsing.ProcessorTest;
import model.programs.parsing.ProgramFactoryTest;
import model.programs.parsing.ProgramParserTest;
import model.programs.parsing.language.ProgramPartTest;
import model.programs.parsing.language.TypeTest;
import model.programs.parsing.language.expression.ExpressionTest;
import model.programs.parsing.language.expression.VariableTest;
import model.programs.parsing.language.expression.constant.ConstantExpressionTest;
import model.programs.parsing.language.expression.constant.FalseTest;
import model.programs.parsing.language.expression.constant.NullTest;
import model.programs.parsing.language.expression.constant.SelfTest;
import model.programs.parsing.language.expression.constant.TrueTest;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteralTest;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteralTest;
import model.programs.parsing.language.expression.constant.literal.EntityLiteralTest;
import model.programs.parsing.language.expression.first_order.CosineTest;
import model.programs.parsing.language.expression.first_order.FirstOrderExpressionOfBooleanToBooleanTest;
import model.programs.parsing.language.expression.first_order.FirstOrderExpressionOfEntityToEntityTest;
import model.programs.parsing.language.expression.first_order.FirstOrderExpressionOfEntityToNumberTest;
import model.programs.parsing.language.expression.first_order.FirstOrderExpressionOfNumberToNumberTest;
import model.programs.parsing.language.expression.first_order.FirstOrderExpressionTest;
import model.programs.parsing.language.expression.first_order.GetDirectionTest;
import model.programs.parsing.language.expression.first_order.GetRadiusTest;
import model.programs.parsing.language.expression.first_order.GetVXTest;
import model.programs.parsing.language.expression.first_order.GetVYTest;
import model.programs.parsing.language.expression.first_order.GetXTest;
import model.programs.parsing.language.expression.first_order.GetYTest;
import model.programs.parsing.language.expression.first_order.NotTest;
import model.programs.parsing.language.expression.first_order.SineTest;
import model.programs.parsing.language.expression.first_order.SquareRootTest;
import model.programs.parsing.language.expression.second_order.AdditionTest;
import model.programs.parsing.language.expression.second_order.AndTest;
import model.programs.parsing.language.expression.second_order.DivisionTest;
import model.programs.parsing.language.expression.second_order.EqualsTest;
import model.programs.parsing.language.expression.second_order.GETest;
import model.programs.parsing.language.expression.second_order.GTTest;
import model.programs.parsing.language.expression.second_order.LETest;
import model.programs.parsing.language.expression.second_order.LTTest;
import model.programs.parsing.language.expression.second_order.MultiplicationTest;
import model.programs.parsing.language.expression.second_order.NotEqualsTest;
import model.programs.parsing.language.expression.second_order.OrTest;
import model.programs.parsing.language.expression.second_order.SecondOrderExpressionOfBooleansToBooleanTest;
import model.programs.parsing.language.expression.second_order.SecondOrderExpressionOfNumbersToBooleanTest;
import model.programs.parsing.language.expression.second_order.SecondOrderExpressionOfNumbersToNumberTest;
import model.programs.parsing.language.expression.second_order.SecondOrderExpressionTest;
import model.programs.parsing.language.expression.second_order.SubstractionTest;
import model.programs.parsing.language.statement.AssignmentTest;
import model.programs.parsing.language.statement.ForEachTest;
import model.programs.parsing.language.statement.IfTest;
import model.programs.parsing.language.statement.PrintTest;
import model.programs.parsing.language.statement.SequenceTest;
import model.programs.parsing.language.statement.StatementTest;
import model.programs.parsing.language.statement.WhileTest;
import model.programs.parsing.language.statement.action.ActionTest;
import model.programs.parsing.language.statement.action.FireTest;
import model.programs.parsing.language.statement.action.SkipTest;
import model.programs.parsing.language.statement.action.ThrustOffTest;
import model.programs.parsing.language.statement.action.ThrustOnTest;
import model.programs.parsing.language.statement.action.TurnTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import world.WorldTest;
import world.entity.AsteroidTest;
import world.entity.BulletTest;
import world.entity.EntityTest;
import world.entity.ship.ShipTest;
import world.entity.ship.ThrusterTest;
import world.physics.MassTest;
import world.physics.MechanicsTest;
import world.physics.collision.BorderCollisionTest;
import world.physics.collision.BorderTest;
import world.physics.collision.CollisionTest;
import world.physics.collision.EntityCollisionTest;
import world.physics.geometry.AngleTest;
import world.physics.geometry.CircleShapeTest;
import world.physics.vector.AccelerationTest;
import world.physics.vector.DirectionTest;
import world.physics.vector.ForceTest;
import world.physics.vector.PositionTest;
import world.physics.vector.QuadrantTest;
import world.physics.vector.VectorTest;
import world.physics.vector.VelocityTest;

@SuppressWarnings ("javadoc")
@RunWith (Suite.class)
@SuiteClasses (
//{ BorderCollisionTest.class, CollisionTest.class, EntityCollisionTest.class, MassTest.class, ShipTest.class, ThrusterTest.class, AngleTest.class, AsteroidTest.class, BulletTest.class, CircleShapeTest.class, EntityTest.class, MechanicsTest.class, AccelerationTest.class, DirectionTest.class, ForceTest.class, PositionTest.class, VectorTest.class, VelocityTest.class, WorldTest.class })
{ UtilTest.class, BulletTest.class, ShipTest.class, ThrusterTest.class, EntityTest.class, AsteroidTest.class, MassTest.class, VectorTest.class, PositionTest.class, QuadrantTest.class, ForceTest.class, AccelerationTest.class, VelocityTest.class, DirectionTest.class, MechanicsTest.class, AngleTest.class, CircleShapeTest.class, BorderTest.class, CollisionTest.class, BorderCollisionTest.class, EntityCollisionTest.class, WorldTest.class, AsteroidsMenuTest.class, SoundTest.class, NullSoundTest.class, DrawableTest.class, ModelExceptionTest.class, CollisionListenerTest.class, AsteroidsTest.class, UtilTest.class, FileSoundManagerTest.class, WorldViewTest.class, ExplosionTest.class, FacadeTest.class, IFacadeTest.class, ProgramTest.class, AsteroidsParserMyListenerTest.class, ProgramPartTest.class, TypeTest.class, ExpressionTest.class, FalseTest.class, TrueTest.class, NullTest.class, ConstantExpressionTest.class, SelfTest.class, BooleanLiteralTest.class, EntityLiteralTest.class, DoubleLiteralTest.class, NotTest.class, GetDirectionTest.class, FirstOrderExpressionTest.class, GetVXTest.class, GetVYTest.class, FirstOrderExpressionOfEntityToEntityTest.class, GetRadiusTest.class, FirstOrderExpressionOfEntityToNumberTest.class, FirstOrderExpressionOfNumberToNumberTest.class, SineTest.class, FirstOrderExpressionOfBooleanToBooleanTest.class, SquareRootTest.class, GetXTest.class, CosineTest.class, GetYTest.class, DivisionTest.class, AdditionTest.class, SecondOrderExpressionOfNumbersToNumberTest.class, MultiplicationTest.class, GTTest.class, LTTest.class, SecondOrderExpressionOfNumbersToBooleanTest.class, GETest.class, EqualsTest.class, SubstractionTest.class, OrTest.class, AndTest.class, SecondOrderExpressionOfBooleansToBooleanTest.class, NotEqualsTest.class, LETest.class, SecondOrderExpressionTest.class, VariableTest.class, SequenceTest.class, IfTest.class, AssignmentTest.class, ForEachTest.class, WhileTest.class, TurnTest.class, ThrustOffTest.class, ActionTest.class, SkipTest.class, ThrustOnTest.class, FireTest.class, StatementTest.class, PrintTest.class, ProcessorTest.class, AsteroidsParserBaseListenerTest.class, AsteroidsParserLexerTest.class, ProgramParserTest.class, AsteroidsParserListenerTest.class, AsteroidsParserParserTest.class, MyFactoryTest.class, ProgramFactoryTest.class, ModelExceptionTest.class, IShipTest.class })
public class AsteroidsTestSuite
{

}