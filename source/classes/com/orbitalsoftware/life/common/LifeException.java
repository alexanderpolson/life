/*
 * Copyright (C) 2004 Orbital Software. All Rights Reserved.
 * $Id: ExceptionTemplate.java,v 1.1 2004/05/14 18:59:54 apolson Exp $
 */

package com.orbitalsoftware.life.common;


/**
 * A general Life only <code>Exception</code> that is intended to be extended
 * by more specific <code>Exception</code>s.
 *
 * @author   Alex Polson
 * @version  $Revision$
 */
public class LifeException
  extends Exception
{
  public LifeException()
  {
    super();
  }
  
  public LifeException( String message )
  {
    super( message );
  }

  public LifeException( String message, Throwable cause )
  {
    super( message, cause );
  }

  public LifeException( Throwable cause )
  {
    super( cause );
  }
}
