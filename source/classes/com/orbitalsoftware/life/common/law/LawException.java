/*
 * Copyright (C) 2004 Orbital Software. All Rights Reserved.
 * $Id: $
 */

package com.orbitalsoftware.life.common.law;


import com.orbitalsoftware.life.common.LifeException;

/**
 * A general exception having to do with <code>Law</code> and related classes.
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */
public class LawException
  extends LifeException
{
  public LawException()
  {
    super();
  }
  
  public LawException( String message )
  {
    super( message );
  }

  public LawException( String message, Throwable cause )
  {
    super( message, cause );
  }

  public LawException( Throwable cause )
  {
    super( cause );
  }
}
