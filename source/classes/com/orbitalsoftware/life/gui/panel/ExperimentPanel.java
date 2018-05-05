/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.Experiment;


import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;

/**
 * A panel used to either add or edit <code>Experiment</code>s.
 *
 * @author  $Author$
 * @version $Revision$
 */
public class ExperimentPanel
  extends Composite
{
  public ExperimentPanel( Composite parent, int style, Experiment experiment )
  {
    super( parent, style );
    init( experiment );
  }
  
  private void init( Experiment experiment )
  {
    this.experiment = experiment;
    GridData layoutData = null;
    layout = new GridLayout();
    layout.numColumns = 1;
    
    setLayout( layout );
    
    // Create LifeObjectWidget
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.widthHint = 250;
    low = new LifeObjectPanel( this, SWT.NONE, experiment );
    low.setLayoutData( layoutData );
    
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    worldChooser = new WorldChooserPanel( getParent(), SWT.NONE, experiment.getWorld() );
    worldChooser.setLayoutData( layoutData );
  }
  
  public void commit()
  {
    low.commit();
  }
  
  // Private Attributes
  //
  private Experiment experiment;
  private GridLayout layout;
  private LifeObjectPanel low;
  private WorldChooserPanel worldChooser;
}
