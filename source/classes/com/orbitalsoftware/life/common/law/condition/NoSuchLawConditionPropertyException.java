/*
 * Copyright (C) 2004 Orbital Software. All Rights Reserved.
 * $Id: ExceptionTemplate.java,v 1.1 2004/05/14 18:59:54 apolson Exp $
 */

package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.law.LawException;

/**
 * This exception is thrown when a requested <code>LawCondition</code> property
 * is invalid.
 *
 * @author   Alex Polson
 * @version  $Revision$
 */
public class NoSuchLawConditionPropertyException
  extends LawException
{
  public NoSuchLawConditionPropertyException()
  {
    super();
  }
  
  public NoSuchLawConditionPropertyException( String message )
  {
    super( message );
  }

  public NoSuchLawConditionPropertyException( String message, Throwable cause )
  {
    super( message, cause );
  }

  public NoSuchLawConditionPropertyException( Throwable cause )
  {
    super( cause );
  }
}
