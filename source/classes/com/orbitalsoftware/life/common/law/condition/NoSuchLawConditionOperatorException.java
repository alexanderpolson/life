/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.law.LawException;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class NoSuchLawConditionOperatorException
  extends LawException
{
  // Constructors
  //
  public NoSuchLawConditionOperatorException()
  {
    super();
  }

  public NoSuchLawConditionOperatorException( String message )
  {
    super( message );
  }

  public NoSuchLawConditionOperatorException( String message, Throwable cause )
  {
    super( message, cause );
  }

  public NoSuchLawConditionOperatorException( Throwable cause )
  {
    super( cause );
  }
}