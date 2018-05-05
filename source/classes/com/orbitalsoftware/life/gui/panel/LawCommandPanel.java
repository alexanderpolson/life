/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.law.command.LawCommand;

import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;

/**
 * A panel used to either add or edit <code>Experiment</code>s.
 *
 * @author  $Author$
 * @version $Revision$
 */
public abstract class LawCommandPanel
  extends Composite
{
  public LawCommandPanel( Composite parent, int style, LawCommand lawCommand )
  {
    super( parent, style );
    this.lawCommand = lawCommand;
  }
  
  public abstract void commit();
  
  private void init( LawCommand lawCommand )
  {
    
  }
  
  // Private Attributes
  //
  protected LawCommand lawCommand;
  protected GridLayout layout;
}
