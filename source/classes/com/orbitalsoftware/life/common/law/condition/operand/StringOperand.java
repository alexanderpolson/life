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

public class StringOperand
  extends AbstractLawConditionOperand
{
  // Constructors
  //

  public StringOperand()
  {
    setValue( "" );
  }

  public StringOperand( String value )
  {
    setValue( value );
  }

  // Methods
  //

  public void setValue( String value )
  {
    this.value = value;
  }

  public String getValue()
  {
    return value;
  }

  public void parse( String state )
    throws ParseException
  {
    setValue( state );
  }

  public String toString()
  {
    return value.toString();
  }

  // Statics
  //

  // Private Attributes
  //

  private String value;
}