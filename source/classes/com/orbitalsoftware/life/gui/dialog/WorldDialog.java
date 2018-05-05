/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.dialog;


import com.orbitalsoftware.common.gui.OkCancelPanel;

import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.gui.panel.WorldPanel;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

//import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

/**
 * Allows the creation/editing of <code>Experiment</code>s.
 *
 * @author  $Author$
 * @version $Revision$
 */
public final class WorldDialog
  extends AbstractLifeDialog
  implements SelectionListener
{
  // Constructors
  //
  
  public WorldDialog( Shell parent, World world )
  {
    super( parent );
    init( world );
  }
  
  private void init( World world )
  {
    GridData layoutData = null;
    layout = new GridLayout();
    layout.numColumns = 1;
    
    dialog.setLayout( layout );
    
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.widthHint = 400;
    worldPanel = new WorldPanel( dialog, SWT.NONE, world );
    worldPanel.setLayoutData( layoutData );
    
    layoutData = new GridData();
    layoutData.horizontalAlignment = GridData.FILL;
    okCancel = new OkCancelPanel( dialog, SWT.NONE );
    okCancel.setLayoutData( layoutData );
    
    dialog.setDefaultButton( okCancel.getOk() );
    
    // Install Listeners
    okCancel.getOk().addSelectionListener( this );
  }
  
  public OkCancelPanel getOkCancel()
  {
    return okCancel;
  }
  
  // SelectionListener implementation
  
  public void widgetSelected( SelectionEvent e )
  {
    if( e.getSource() == okCancel.getOk() )
    {
      worldPanel.commit();
      dialog.dispose();
    }
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  // Private Attributes
  //
  private World world;
  private GridLayout layout;
  private WorldPanel worldPanel;
  private OkCancelPanel okCancel;
}
