/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.dialog;


import com.orbitalsoftware.life.common.Experiment;

import com.orbitalsoftware.common.gui.OkCancelPanel;

import com.orbitalsoftware.life.gui.panel.ExperimentPanel;
import com.orbitalsoftware.life.gui.panel.WorldChooserPanel;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Shell;

/**
 * Allows the creation/editing of <code>Experiment</code>s.
 *
 * @author  $Author$
 * @version $Revision$
 */
public final class ExperimentDialog
  extends AbstractLifeDialog
  implements SelectionListener
{
  // Constructors
  //
  
  public ExperimentDialog( Shell parent, Experiment experiment )
  {
    super( parent );
    init( experiment );
  }
  
  private void init( Experiment experiment )
  {
    this.experiment = experiment;
    GridData layoutData = null;
    layout = new GridLayout();
    layout.numColumns = 1;
    
    dialog.setLayout( layout );
    
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    experimentPanel = new ExperimentPanel( dialog, SWT.NONE, experiment );
    experimentPanel.setLayoutData( layoutData );
    
    layoutData = new GridData();
    layoutData.horizontalAlignment = GridData.FILL;
    okCancel = new OkCancelPanel( dialog, SWT.NONE );
    okCancel.setLayoutData( layoutData );
    
    dialog.setDefaultButton( okCancel.getOk() );
    
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
      experimentPanel.commit();
      dialog.dispose();
    }
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  // Private Attributes
  //
  private Experiment experiment;
  private GridLayout layout;
  private ExperimentPanel experimentPanel;
  private WorldChooserPanel worldChooser;
  private OkCancelPanel okCancel;
}
