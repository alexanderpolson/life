/*
 * Copyright (C) 2004 Orbital Software. All Rights Reserved.
 * $Id: ExceptionTemplate.java,v 1.1 2004/05/14 18:59:54 apolson Exp $
 */

package com.orbitalsoftware.life.common;


/**
 * An <code>Exception</code> that is thrown in instances when an attempt is
 * made to access data from an <code>Experiment</code> that can only be
 * accessed when it is running.
 *
 * @author   Alex Polson
 * @version  $Revision$
 */
public class ExperimentNotRunningException
  extends ExperimentException
{
  public ExperimentNotRunningException()
  {
    super();
  }

  public ExperimentNotRunningException( String message )
  {
    super( message );
  }
}
