/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.dialog;


import com.orbitalsoftware.common.gui.OkCancelPanel;

import com.orbitalsoftware.life.common.law.Law;

import com.orbitalsoftware.life.gui.panel.LawPanel;

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
public final class LawDialog
  extends AbstractLifeDialog
  implements SelectionListener
{
  // Constructors
  //
  
  public LawDialog( Shell parent, Law law )
  {
    super( parent );
    init( law );
  }
  
  private void init( Law law )
  {
    GridData layoutData = null;
    layout = new GridLayout();
    layout.numColumns = 1;
    
    dialog.setLayout( layout );
    
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.widthHint = 400;
    lawPanel = new LawPanel( dialog, SWT.NONE, law );
    lawPanel.setLayoutData( layoutData );
    
    layoutData = new GridData();
    layoutData.horizontalAlignment = GridData.FILL;
    okCancel = new OkCancelPanel( dialog, SWT.NONE );
    okCancel.setLayoutData( layoutData );
    
    // Add Listeners
    okCancel.getOk().addSelectionListener( this );
  }
  
  public OkCancelPanel getOkCancel()
  {
    return okCancel;
  }
  
  public void commit()
  {
    lawPanel.commit();
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
  private LawPanel lawPanel;
  private OkCancelPanel okCancel;
}
