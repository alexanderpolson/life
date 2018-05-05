/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.Cell;
import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.common.law.condition.operand.StringOperand;

/**
 * Used to test base functionality of <code>AbstractLawCondition</code>.
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */
public class AbstractLawConditionImpl
  extends AbstractLawCondition
{
  // Constructors
  //
  public AbstractLawConditionImpl()
    throws LawConditionExistsException
  {
    properties.put( TEST_PROPERTY1, TEST_VALUE1 );
    properties.put( TEST_PROPERTY2, TEST_VALUE2 );
    properties.put( TEST_PROPERTY3, TEST_VALUE3 );
  }

  // Methods
  //

  public boolean appliesToCell( World world, Cell cell )
  {
    return true;
  }

  public void parse( String condition )
  {
    return;
  }

  // Statics
  //
  public static final String TEST_PROPERTY1 = "testProperty1";
  public static StringOperand TEST_VALUE1 = new StringOperand();
  public static final String TEST_PROPERTY2 = "testProperty2";
  public static StringOperand TEST_VALUE2 = new StringOperand();
  public static final String TEST_PROPERTY3 = "testProperty3";
  public static StringOperand TEST_VALUE3 = new StringOperand( "testValue" );

  // Private Attributes
  //
}