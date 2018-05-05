/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.command;


import com.orbitalsoftware.life.common.Cell;
import com.orbitalsoftware.life.common.LifeObject;

import com.orbitalsoftware.life.common.law.command.NoSuchLawCommandParameterException;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */
public interface LawCommand
  extends LifeObject
{
  // Methods
  //
  
  public LifeObject setParameter( String parameter, LifeObject value )
      throws NoSuchLawCommandParameterException, IllegalArgumentException;
  
  public LifeObject getParameter( String parameter )
    throws NoSuchLawCommandParameterException;

  /**
   * Applies the <code>LawCommand</code> to the provided <code>Cell</code>.
   *
   * @param cell The <code>Cell</code> to apply the command to.
   */
  public void execute( Cell cell );
}