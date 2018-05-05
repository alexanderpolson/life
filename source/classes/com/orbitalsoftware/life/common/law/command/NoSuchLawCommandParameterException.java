/*
 * Copyright (C) 2004 Orbital Software. All Rights Reserved.
 * $Id: ExceptionTemplate.java,v 1.1 2004/05/14 18:59:54 apolson Exp $
 */

package com.orbitalsoftware.life.common.law.command;


import com.orbitalsoftware.life.common.law.LawException;

/**
 * This exception is thrown when a requested <code>LawCondition</code> property
 * is invalid.
 *
 * @author   Alex Polson
 * @version  $Revision$
 */
public class NoSuchLawCommandParameterException
  extends LawException
{
  public NoSuchLawCommandParameterException()
  {
    super();
  }
  
  public NoSuchLawCommandParameterException( String message )
  {
    super( message );
  }

  public NoSuchLawCommandParameterException( String message, Throwable cause )
  {
    super( message, cause );
  }

  public NoSuchLawCommandParameterException( Throwable cause )
  {
    super( cause );
  }
}
