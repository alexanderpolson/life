/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common;

import com.orbitalsoftware.common.text.ParseException;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */
public class LifeInteger
  extends AbstractLifeObject
{
  // Constructors
  //
  
  public LifeInteger( LifeObject owner )
  {
    super( owner );
  }
  
  public LifeInteger( LifeObject owner, int value )
  {
    super( owner );
    this.value = value;
  }
  
  public LifeInteger( LifeObject owner, String name )
  {
    super( owner, name );
  }

  public LifeInteger( LifeObject owner, String name, String description )
  {
    super( owner, name, description );
    value = DEFAULT_STATE;
  }

  public LifeInteger( LifeObject owner, String name, String description, String state )
    throws ParseException
  {
    super( owner, name, description, state );
  }

  // Methods
  //
  
  public int getValue()
  {
    return value;
  }

  public void parse( String state )
    throws ParseException
  {
    try
    {
      value = Integer.parseInt( state );
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
  
  public static int DEFAULT_STATE = 0;

  // Private Attributes
  //

  private int value;
}