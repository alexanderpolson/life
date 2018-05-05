/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui;


import com.orbitalsoftware.life.common.World;

import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.FillLayout;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import java.util.Iterator;

/**
 * [Descripition here]
 *
 * @author  $Author$
 * @version $Revision$
 */
public class CellStateCombo
    extends Composite
{
  public CellStateCombo( Composite parent, int style, World world )
  {
    super( parent, SWT.NONE );
    this.world = world;
    setLayout( new FillLayout() );
    
    cellStates = new Combo( this, style );
    init();
    cellStates.select( 0 );
  }
  
  private void init()
  {
    int index = 0;
    Iterator it = world.cellStateIterator();
    String[] stateNames = new String[world.getNumCellStates()];
    
    while( it.hasNext() )
    {
      stateNames[index] = ((CellStateWidget)it.next()).getName();
      index++;
    }
    
    cellStates.setItems( stateNames );
  }
  
  public CellStateWidget getCellState()
  {
    String item = cellStates.getItem( cellStates.getSelectionIndex() );
    CellStateWidget state = null;
    Iterator it = world.cellStateIterator();
    
    while( it.hasNext() )
    {
      state = (CellStateWidget)it.next();
      if( state.getName().equals( item ) )
      {
        return state;
      }
    }
    
    return null;
  }
  
  // Private Attributes
  //
  private World world;
  private Combo cellStates;
}
