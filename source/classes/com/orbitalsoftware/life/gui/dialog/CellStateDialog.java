/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.dialog;


import com.orbitalsoftware.common.gui.OkCancelPanel;

import com.orbitalsoftware.life.gui.CellStateWidget;

import com.orbitalsoftware.life.gui.panel.CellStatePanel;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Shell;

/**
 * Used to create new or edit previously defined <code>CellState</code>s.
 *
 * @author  $Author$
 * @version $Revision$
 */
public final class CellStateDialog
  extends AbstractLifeDialog
  implements SelectionListener
{
  public CellStateDialog( Shell parent, CellStateWidget cellState )
  {
    super( parent );
    init( cellState );
  }
  
  private void init( CellStateWidget cellState )
  {
    GridData layoutData = null;
    layout = new GridLayout();
    layout.numColumns = 1;
    
    dialog.setLayout( layout );
    
    layoutData = new GridData();
    layoutData.horizontalAlignment = GridData.FILL;
    statePanel = new CellStatePanel( dialog, SWT.NONE, cellState );
    statePanel.setLayoutData( layoutData );
    
    layoutData = new GridData();
    layoutData.horizontalAlignment = GridData.FILL;
    okCancel = new OkCancelPanel( dialog, SWT.NONE );
    okCancel.setLayoutData( layoutData );
    
    // Add listeners
    okCancel.getOk().addSelectionListener( this );
  }
  
  public OkCancelPanel getOkCancel()
  {
    return okCancel;
  }
  
  public void commit()
  {
    statePanel.commit();
  }
  
  // SelectionListener implementation
  
  public void widgetSelected( SelectionEvent e )
  {
    if( e.getSource() == okCancel.getOk() )
    {
      commit();
      dialog.dispose();
    }
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  // Private Attributes
  //
  private GridLayout layout;
  private CellStatePanel statePanel;
  private OkCancelPanel okCancel;
}
