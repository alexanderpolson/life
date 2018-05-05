/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.common.law.Law;

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
public class LawPanel
  extends Composite
{
  public LawPanel( Composite parent, int style, Law law )
  {
    super( parent, style );
    init( law );
  }
  
  private void init( Law law )
  {
    this.law = law;
    GridData layoutData = null;
    layout = new GridLayout();
    layout.numColumns = 1;
    
    setLayout( layout );
    
    // Create LifeObjectWidget
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.widthHint = 250;
    low = new LifeObjectPanel( this, SWT.NONE, law );
    low.setLayoutData( layoutData );
    
    // Create LawManagementPanel
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    lmp = new LawConditionManagementPanel( this, SWT.NONE, law );
    lmp.setLayoutData( layoutData );
    lmp.refreshList();
    
    // Create LawCommandPanel
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    lcp = new LawCommandManagementPanel( this, SWT.NONE, law );
    lcp.setLayoutData( layoutData );
  }
  
  public void commit()
  {
    low.commit();
    // Add the completed Law to the World
    World world = (World)law.getOwner();
    world.addLaw( law );
  }
  
  // Private Attributes
  //
  private Law law;
  private GridLayout layout;
  private LifeObjectPanel low;
  private CellStateManagementPanel csmp;
  private LawConditionManagementPanel lmp;
  private LawCommandManagementPanel lcp;
}
