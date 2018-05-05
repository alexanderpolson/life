/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import junit.framework.*;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class LawConditionOperatorManagerTest
  extends TestCase
{
  // Methods
  //

  protected void setUp()
  {
    
  }

  // BEGIN Tests
  //

  public void testGetOperator()
  {
    try
    {
      Assert.assertEquals(
        LawConditionOperatorManager.getOperator(
          LawConditionOperator.EQUALS_NAME
          ),
        LawConditionOperator.EQUALS
        );
      Assert.assertEquals(
        LawConditionOperatorManager.getOperator(
          LawConditionOperator.LESSTHAN_NAME
          ),
        LawConditionOperator.LESSTHAN
        );
      Assert.assertEquals(
        LawConditionOperatorManager.getOperator(
          LawConditionOperator.GREATERTHAN_NAME
          ),
        LawConditionOperator.GREATERTHAN
        );
      Assert.assertEquals(
        LawConditionOperatorManager.getOperator(
          LawConditionOperator.LESSTHANOREQUALTO_NAME
          ),
        LawConditionOperator.LESSTHANOREQUALTO
        );
      Assert.assertEquals(
        LawConditionOperatorManager.getOperator(
          LawConditionOperator.GREATERTHANOREQUALTO_NAME
          ),
        LawConditionOperator.GREATERTHANOREQUALTO
        );
    }
    catch( NoSuchLawConditionOperatorException nslcoe )
    {
      Assert.fail
        ( "NoSuchLawConditionOperatorException should not have been thrown.\n" +
          nslcoe.getMessage() );
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
}