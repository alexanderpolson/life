/*
 * Copyright (C) 2004 Orbital Software. All Rights Reserved.
 * $Id: ExceptionTemplate.java,v 1.1 2004/05/14 18:59:54 apolson Exp $
 */

package com.orbitalsoftware.life.common;


/**
 * An <code>Exception</code> that is thrown in instances when the initial
 * <code>World</code> state for an <code>Experiment</code> has not been
 * defined.
 *
 * @author   Alex Polson
 * @version  $Revision$
 */
public class InitialStateNotDefinedException
  extends LifeException
{
  public InitialStateNotDefinedException()
  {
    super();
  }

  public InitialStateNotDefinedException( String message )
  {
    super( message );
  }
}
