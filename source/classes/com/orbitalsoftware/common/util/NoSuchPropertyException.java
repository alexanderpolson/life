/*
 * Copyright (C) 2004 Orbital Software. All Rights Reserved.
 * $Id: $
 */

package com.orbitalsoftware.common.util;


/**
 * This exception is thrown when a requested property does not exist.
 *
 * @author   Alex Polson
 * @version  $Revision$
 */
public class NoSuchPropertyException
  extends Exception
{
  public NoSuchPropertyException()
  {
    super();
  }
  
  public NoSuchPropertyException( String message )
  {
    super( message );
  }

  public NoSuchPropertyException( String message, Throwable cause )
  {
    super( message, cause );
  }

  public NoSuchPropertyException( Throwable cause )
  {
    super( cause );
  }
}
