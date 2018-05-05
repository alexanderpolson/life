/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.common.gui;


import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * A simple widget with an OK and Cancel <code>Button</code> on it.
 *
 * @author  $Author$
 * @version $Revision$
 */
public class OkCancelPanel
    extends Composite
    implements SelectionListener
{
  // Constructors
  //
  
  public OkCancelPanel( Composite parent, int style )
  {
    super( parent, style );
    init();
  }
  
  // Methods
  //
  
  private void init()
  {
    GridData layoutData = null;
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    layout.makeColumnsEqualWidth = true;
    
    setLayout( layout );
    
    layoutData = new GridData();
    ok = new Button( this, SWT.PUSH );
    ok.setText( "OK" );
    layoutData.horizontalAlignment = SWT.CENTER;
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.widthHint = 80;
    ok.setLayoutData( layoutData );

    layoutData = new GridData();
    cancel = new Button( this, SWT.PUSH );
    cancel.setText( "Cancel" );
    layoutData.horizontalAlignment = SWT.CENTER;
    layoutData.widthHint = 80;
    layoutData.grabExcessHorizontalSpace = true;
    cancel.setLayoutData( layoutData );
    
    addSelectionListener( this );
  }
  
  public void addSelectionListener( SelectionListener listener )
  {
    ok.addSelectionListener( listener );
    cancel.addSelectionListener( listener );
  }
  
  public Button getOk()
  {
    return ok;
  }
  
  public Button getCancel()
  {
    return cancel;
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  public void widgetSelected( SelectionEvent e )
  {
    if( e.getSource() == cancel )
    {
      getShell().dispose();
    }
  }
  
  // Private Attributes
  //
  private Button ok;
  private Button cancel;
}
