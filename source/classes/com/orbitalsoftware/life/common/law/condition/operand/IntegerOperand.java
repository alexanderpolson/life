/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition.operand;


import com.orbitalsoftware.common.text.ParseException;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class IntegerOperand
  extends AbstractLawConditionOperand
{
  // Constructors
  //

  public IntegerOperand()
  {
    setValue( 0 );
  }

  public IntegerOperand( int value )
  {
    setValue( value );
  }

  // Methods
  //

  public void setValue( int value )
  {
    this.value = value;
  }

  public int getValue()
  {
    return value;
  }

  public void parse( String state )
    throws ParseException
  {
    try
    {
      setValue( Integer.parseInt( state ) );
    }
    catch( NumberFormatException nfe )
    {
      throw new ParseException ( nfe.getMessage() );
    }
  }

  public String toString()
  {
    return new Integer( value ).toString();
  }

  // Statics
  //

  // Private Attributes
  //

  private int value;
}