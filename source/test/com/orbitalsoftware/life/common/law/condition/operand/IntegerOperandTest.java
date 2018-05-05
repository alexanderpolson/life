/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition.operand;


import com.orbitalsoftware.common.text.ParseException;

import junit.framework.*;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class IntegerOperandTest
  extends TestCase
{
  // Methods
  //

  protected void setUp()
  {
    io1 = new IntegerOperand();
    io2 = new IntegerOperand( IO2_INIT_VALUE );
  }

  // BEGIN Tests
  //

  public void testSetValue()
  {
    io1.setValue( IO1_NEW_VALUE );
    Assert.assertEquals( io1.getValue(), IO1_NEW_VALUE );

    io2.setValue( IO2_NEW_VALUE );
    Assert.assertEquals( io2.getValue(), IO2_NEW_VALUE );
  }

  public void testGetValue()
  {
    Assert.assertEquals( io1.getValue(), 0 );
    Assert.assertEquals( io2.getValue(), IO2_INIT_VALUE );
  }

  public void testParse()
  {
    try
    {
      io1.parse( INT_STRING );
      Assert.assertEquals( io1.getValue(), INT_VALUE );
    }
    catch( ParseException pe )
    {
      Assert.fail( "A ParseException should not have been thrown." );
    }

    try
    {
      io2.parse( INT_STRING_BAD );
      Assert.fail( "A ParseException should have been thrown." );
    }
    catch( ParseException pe )
    {
      // No Op. Execution should reach this point.
    }
  }

  public void testToString()
  {
    Assert.assertEquals( io1.toString(), "0" );
    Assert.assertEquals( io2.toString(), IO2_INIT_VALUE_STRING );
  }

  //
  // END   Tests

  protected void tearDown()
  {
    
  }

  // Statics
  //
  private static final int IO2_INIT_VALUE = 10;
  private static final String IO2_INIT_VALUE_STRING = "10";

  private static final int IO1_NEW_VALUE = 185;
  private static final int IO2_NEW_VALUE = 914;

  private static final String INT_STRING = "903";
  private static final int INT_VALUE = 903;

  private static final String INT_STRING_BAD = "This is not a number.";

  // Private Attributes
  //
  private IntegerOperand io1;
  private IntegerOperand io2;
}