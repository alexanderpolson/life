/*
 * Copyright (C) 2004 Orbital Software. All Rights Reserved.
 * $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.law.LawException;

/**
 * This exception is thrown when a requested <code>LawCondition</code> is
 * invalid.
 *
 * @author   Alex Polson
 * @version  $Revision$
 */
public class NoSuchLawConditionException
  extends LawException
{
  public NoSuchLawConditionException()
  {
    super();
  }
  
  public NoSuchLawConditionException( String message )
  {
    super( message );
  }

  public NoSuchLawConditionException( String message, Throwable cause )
  {
    super( message, cause );
  }

  public NoSuchLawConditionException( Throwable cause )
  {
    super( cause );
  }
}
