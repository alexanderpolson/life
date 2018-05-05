/*
 * Copyright (C) 2004 Orbital Software. All Rights Reserved.
 * $Id: ExceptionTemplate.java,v 1.1 2004/05/14 18:59:54 apolson Exp $
 */

package com.orbitalsoftware.life.common;


/**
 * An <code>Exception</code> that is thrown in instances when an attempt is
 * made to access data from an <code>Experiment</code> that hasn't been run
 * yet. In other words there is no data to access.
 *
 * @author   Alex Polson
 * @version  $Revision$
 */
public class ExperimentNotRunException
  extends ExperimentException
{
  public ExperimentNotRunException()
  {
    super();
  }

  public ExperimentNotRunException( String message )
  {
    super( message );
  }

  public ExperimentNotRunException( String message, Throwable cause )
  {
    super( message, cause );
  }
}
