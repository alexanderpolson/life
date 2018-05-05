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
public class LifeString
  extends AbstractLifeObject
{
  // Constructors
  //

  public LifeString( LifeObject owner, String name )
  {
    super( owner, name );
  }

  public LifeString( LifeObject owner, String name, String description )
  {
    super( owner, name, description );
  }
  
  public LifeString( LifeObject owner, String name, String description, String state )
    throws ParseException
  {
    super( owner, name, description, state );
  }

  // Methods
  //
  
  public void setDefaultState()
  {
    value = DEFAULT_STATE;
  }

  public void parse( String state )
    throws ParseException
  {
    value = state;
  }

  public String toString()
  {
    return value.toString();
  }

  // Statics
  //
  
  public static String DEFAULT_STATE = "";

  // Private Attributes
  //

  private String value;
}