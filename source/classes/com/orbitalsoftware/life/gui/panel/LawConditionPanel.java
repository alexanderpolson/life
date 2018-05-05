/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.law.condition.LawCondition;

import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;

/**
 * A panel used to either add or edit <code>Experiment</code>s.
 *
 * @author  $Author$
 * @version $Revision$
 */
public abstract class LawConditionPanel
  extends Composite
{
  public LawConditionPanel( Composite parent, int style, LawCondition lawCondition )
  {
    super( parent, style );
    this.lawCondition = lawCondition;
  }
  
  public abstract void commit();
  
  private void init( LawCondition lawCondition )
  {
    
  }
  
  // Private Attributes
  //
  protected LawCondition lawCondition;
  protected GridLayout layout;
}
