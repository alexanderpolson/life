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

public class StringOperandTest
  extends TestCase
{
  // Methods
  //

  protected void setUp()
  {
    so1 = new StringOperand();
    so2 = new StringOperand( SO2_INIT_VALUE);
  }

  // BEGIN Tests
  //

  public void testSetValue()
  {
    so1.setValue( SO1_NEW_VALUE );
    Assert.assertEquals( so1.getValue(), SO1_NEW_VALUE );

    so2.setValue( SO2_NEW_VALUE );
    Assert.assertEquals( so2.getValue(), SO2_NEW_VALUE );
  }

  public void testGetValue()
  {
    Assert.assertEquals( "", so1.getValue() );
    Assert.assertEquals( SO2_INIT_VALUE, so2.getValue() );
  }

  public void testParse()
  {
    try
    {
      so1.parse( STRING );
      Assert.assertEquals( so1.getValue(), STRING );
    }
    catch( ParseException pe )
    {
      Assert.fail( "ParseException should not have been thrown." );
    }
  }

  public void testToString()
  {
    Assert.assertEquals( so1.toString(), "" );
    Assert.assertEquals( so2.toString(), SO2_INIT_VALUE );
  }

  //
  // END   Tests

  protected void tearDown()
  {
    
  }

  // Statics
  //
  private static final String SO2_INIT_VALUE = "Test String 2";

  private static final String SO1_NEW_VALUE = "New Test String 1";
  private static final String SO2_NEW_VALUE = "New Test String 2";
  
  private static final String STRING = "String";

  // Private Attributes
  //
  private StringOperand so1;
  private StringOperand so2;
}