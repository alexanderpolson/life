/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.common.text;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class ParseException
  extends Exception
{
  // Constructors
  //
  public ParseException()
  {
    super();
  }

  public ParseException( String message )
  {
    super( message );
  }
}