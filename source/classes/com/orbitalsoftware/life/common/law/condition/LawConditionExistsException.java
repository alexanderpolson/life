/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.LifeException;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class LawConditionExistsException
  extends LifeException
{
  // Constructors
  //

  public LawConditionExistsException()
  {
    super();
  }
  
  public LawConditionExistsException( String message )
  {
    super( message );
  }

  public LawConditionExistsException( String message, Throwable cause )
  {
    super( message, cause );
  }

  public LawConditionExistsException( Throwable cause )
  {
    super( cause );
  }
}