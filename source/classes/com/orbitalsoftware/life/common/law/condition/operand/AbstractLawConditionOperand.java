/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition.operand;


import com.orbitalsoftware.life.common.law.condition.LawCondition;

import com.orbitalsoftware.common.text.ParseException;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public abstract class AbstractLawConditionOperand
  implements LawConditionOperand
{
  // Constructors
  //
  protected AbstractLawConditionOperand()
  {

  }

  protected AbstractLawConditionOperand( String state )
    throws ParseException
  {
    parse( state );
  }

  // Methods
  //
  public boolean equals( LawConditionOperand that )
  {
    return super.equals( that );
  }

  public boolean lessThan( LawConditionOperand that )
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException( MSG_OP_NOT_SUPPORTED );
  }

  public boolean greaterThan( LawConditionOperand that )
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException( MSG_OP_NOT_SUPPORTED );
  }

  public boolean lessThanOrEqualTo( LawConditionOperand that )
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException( MSG_OP_NOT_SUPPORTED );
  }

  public boolean greaterThanOrEqualTo( LawConditionOperand that )
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException( MSG_OP_NOT_SUPPORTED );
  }

  public abstract void parse( String state )
    throws ParseException;

  public abstract String toString();

  // Statics
  //
  private static final String MSG_OP_NOT_SUPPORTED =
    "This operation is not supported by this LawConditionOperand.";
}