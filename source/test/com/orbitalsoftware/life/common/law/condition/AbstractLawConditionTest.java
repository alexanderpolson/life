/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.law.condition.operand.StringOperand;
import com.orbitalsoftware.life.common.law.condition.operand.IntegerOperand;

import junit.framework.*;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class AbstractLawConditionTest
  extends TestCase
{
  // Methods
  //

  protected void setUp()
  {
    try
    {
      lawCondition = new AbstractLawConditionImpl();
    }
    catch( LawConditionExistsException lcee )
    {
      // Shouldn't throw this.
    }
  }

  // BEGIN Tests
  //

  public void testSetProperty()
  {
    try
    {
      lawCondition.setProperty(
        AbstractLawConditionImpl.TEST_PROPERTY1, new StringOperand()
        );
      lawCondition.setProperty(
        AbstractLawConditionImpl.TEST_PROPERTY2, new StringOperand()
        );
    }
    catch( NoSuchLawConditionPropertyException nslcpe )
    {
      Assert.fail(
        "NoSuchLawConditionPropertyException should not have been thrown."
        );
    }
    catch( IllegalArgumentException iae )
    {
      Assert.fail(
        "IllegalArgumentException should not have been thrown."
        );
    }

    try
    {
      lawCondition.setProperty( "badKey", new StringOperand() );
      Assert.fail(
        "NoSuchLawConditionPropertyException should have been thrown."
        );
    }
    catch( NoSuchLawConditionPropertyException nslcpe )
    {
      // This was supposed to happen.
    }
    catch( IllegalArgumentException iae )
    {
      Assert.fail(
        "IllegalArgumentException should not have been thrown."
        );
    }

    try
    {
      lawCondition.setProperty(
        AbstractLawConditionImpl.TEST_PROPERTY1, new IntegerOperand()
        );
      Assert.fail(
        "IllegalArgumentException should have been thrown."
        );
    }
    catch( NoSuchLawConditionPropertyException nslcpe )
    {
      Assert.fail(
        "NoSuchLawConditionPropertyException should not have been thrown."
        );
    }
    catch( IllegalArgumentException iae )
    {
      // This was supposed to happen.
    }
  }

  public void testGetProperty()
  {
    try
    {
      Assert.assertTrue(
        lawCondition.getProperty( AbstractLawConditionImpl.TEST_PROPERTY1 ) ==
        AbstractLawConditionImpl.TEST_VALUE1
        );
      Assert.assertTrue(
        lawCondition.getProperty( AbstractLawConditionImpl.TEST_PROPERTY2 ) ==
        AbstractLawConditionImpl.TEST_VALUE2
        );
      Assert.assertTrue(
        lawCondition.getProperty( AbstractLawConditionImpl.TEST_PROPERTY3 ) ==
        AbstractLawConditionImpl.TEST_VALUE3
        );
    }
    catch( NoSuchLawConditionPropertyException nslcpe )
    {
      Assert.fail(
        "NoSuchLawConditionPropertyException should not have been thrown."
        );
    }

    try
    {
      lawCondition.getProperty( "badKey" );
      Assert.fail(
        "NoSuchLawConditionPropertyException should have been thrown."
        );
    }
    catch( NoSuchLawConditionPropertyException nslcpe )
    {
      // This was supposed to happen.
    }
  }

  //
  // END Tests

  protected void tearDown()
  {
    
  }

  // Statics
  //

  // Private Attributes
  //
  private LawCondition lawCondition;
}