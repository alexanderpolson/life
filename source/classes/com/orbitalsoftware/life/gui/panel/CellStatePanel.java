/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.gui.CellStateWidget;

import org.eclipse.swt.SWT;

import org.eclipse.swt.graphics.RGB;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;

/**
 * A panel used to either add or edit <code>Experiment</code>s.
 *
 * @author  $Author$
 * @version $Revision$
 */
public class CellStatePanel
  extends Composite
{
  public CellStatePanel( Composite parent, int style, CellStateWidget cellState )
  {
    super( parent, style );
    init( cellState );
  }
  
  private void init( CellStateWidget cellState )
  {
    this.cellState = cellState;
    GridData layoutData = null;
    layout = new GridLayout();
    layout.numColumns = 1;
    
    setLayout( layout );
    
    // Create LifeObjectWidget
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.widthHint = 250;
    low = new LifeObjectPanel( this, SWT.NONE, cellState );
    low.setLayoutData( layoutData );
    
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    colorChooser = new ColorChooserPanel( getParent(), SWT.NONE );
    colorChooser.setLayoutData( layoutData );
    colorChooser.setColor( cellState.getColor() );
  }
  
  // Methods
  //
  
  public RGB getColor()
  {
    return colorChooser.getColor();
  }
  
  public void commit()
  {
    // Edit the CellState fields
    low.commit();
    cellState.setColor( colorChooser.getColor() );
    
    // Add the cell state to the World
    World world = (World) cellState.getOwner();
    world.addCellState( cellState );
  }
  
  // Private Attributes
  //
  private CellStateWidget cellState;
  private GridLayout layout;
  private LifeObjectPanel low;
  private ColorChooserPanel colorChooser;
}
