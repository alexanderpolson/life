/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition.operand;


import junit.framework.*;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class AbstractLawConditionOperandTest
  extends TestCase
{
  // Methods
  //

  protected void setUp()
  {
    lco1 = new AbstractLawConditionOperandImpl();
  }

  // BEGIN Tests
  //

  public void testEquals()
  {
    AbstractLawConditionOperandImpl testLco =
      new AbstractLawConditionOperandImpl();
    Assert.assertTrue( lco1.equals( lco1 ) );
    Assert.assertFalse( lco1.equals( testLco ) );
    testLco = lco1;

    Assert.assertTrue( lco1.equals( testLco ) );
    Assert.assertTrue( testLco.equals( lco1 ) );
  }

  public void testLessThan()
  {
    try
    {
      lco1.lessThan( lco1 );
      Assert.fail( "UnsupportedOperationException should have been thrown." );
    }
    catch( UnsupportedOperationException uoe )
    {
      // This is expected.
    }
  }

  public void testGreaterThan()
  {
    try
    {
      lco1.lessThan( lco1 );
      Assert.fail( "UnsupportedOperationException should have been thrown." );
    }
    catch( UnsupportedOperationException uoe )
    {
      // This is expected.
    }
  }

  public void testLessThanOrEqualTo()
  {
    try
    {
      lco1.lessThan( lco1 );
      Assert.fail( "UnsupportedOperationException should have been thrown." );
    }
    catch( UnsupportedOperationException uoe )
    {
      // This is expected.
    }
  }

  public void testGreaterThanOrEqualTo()
  {
    try
    {
      lco1.lessThan( lco1 );
      Assert.fail( "UnsupportedOperationException should have been thrown." );
    }
    catch( UnsupportedOperationException uoe )
    {
      // This is expected.
    }
  }

  //
  // END   Tests

  protected void tearDown()
  {
    
  }

  // Statics
  //

  // Private Attributes
  //
  private AbstractLawConditionOperandImpl lco1;
}