/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.dialog;


import com.orbitalsoftware.common.gui.OkCancelPanel;

import com.orbitalsoftware.life.gui.panel.LawCommandPanel;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.SelectionEvent;

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
public final class LawCommandDialog
  extends AbstractLifeDialog
  implements SelectionListener
{
  // Constructors
  //
  
  public LawCommandDialog( Shell parent )
  {
    super( parent );
    layout = new GridLayout();
    layout.numColumns = 1;
    
    dialog.setLayout( layout );
  }
  
  public void commit()
  {
    panel.commit();
  }
  
  public void init( LawCommandPanel panel )
  {
    this.panel = panel;
    
    // Add LawCommandPanel
    GridData layoutData = null;
    
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.widthHint = 400;
    this.panel.setLayoutData( layoutData );
    
    layoutData = new GridData();
    layoutData.horizontalAlignment = GridData.FILL;
    okCancel = new OkCancelPanel( dialog, SWT.NONE );
    okCancel.setLayoutData( layoutData );
    
    okCancel.getOk().addSelectionListener( this );
  }
  
  public OkCancelPanel getOkCancel()
  {
    return okCancel;
  }
  
  // SelectionListener Implementation
  public void widgetSelected( SelectionEvent e )
  {
    commit();
    dialog.dispose();
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  // Private Attributes
  //
  private GridLayout layout;
  private LawCommandPanel panel;
  private OkCancelPanel okCancel;
}
