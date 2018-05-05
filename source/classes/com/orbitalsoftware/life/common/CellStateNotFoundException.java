/*
 * Copyright (C) 2004 Orbital Software. All Rights Reserved.
 * Overview:
 *   
 * $Id: ExceptionTemplate.java,v 1.1 2004/05/14 18:59:54 apolson Exp $
 */

package com.orbitalsoftware.life.common;


/**
 * <code>CellStateNotFoundException</code>
 *
 * @author   apolson
 * @version  $Revision$
 */

public class CellStateNotFoundException
  extends LifeException
{
  public CellStateNotFoundException( String msg )
  {
    super( msg );
  }

  public CellStateNotFoundException( String msg, Throwable t )
  {
    super( msg, t );
  }

  // Statics
  //
  private static final String DEFAULT_MSG =
    "The requested CellState does not exist.";
}
