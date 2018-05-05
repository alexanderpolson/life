/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class ExperimentException
  extends LifeException
{
  // Constructors
  //
  public ExperimentException()
  {
    super();
  }

  public ExperimentException( String message )
  {
    super( message );
  }

  public ExperimentException( String message, Throwable cause )
  {
    super( message, cause );
  }
}