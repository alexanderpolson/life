/*
 * Copyright (c) 2004 Orbital Software
 * $Id: $
 */
package com.orbitalsoftware.life.gui.dialog;


import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author  Alex Polson
 * @version $Revision$
 */
public final class AboutDialog
{
  // Constructors
  //
  
  public AboutDialog( Shell shell )
  {
    dialog = new Shell( shell, SWT.DIALOG_TRIM );
    dialog.setText( DIALOG_TITLE );
    dialog.setSize( WIDTH, HEIGHT );
    
    Label aboutLabel = new Label( dialog, SWT.CENTER );
    aboutLabel.setText( "Advanced Life\nCopyright (C) 2004 Orbital Software" );
    aboutLabel.setSize( WIDTH, 50 );
    
    final Button okButton = new Button( dialog, SWT.PUSH | SWT.CENTER );
    okButton.setText( "OK" );
    okButton.setSelection( true );
    okButton.setBounds( 50, 50, 50, 25 );
    okButton.addSelectionListener(
        new SelectionAdapter()
        {
          public void widgetSelected( SelectionEvent e )
          {
            dialog.dispose();
          }
        }
        );
    dialog.open();
  }
  
  // Methods
  //
  
  // Statics
  //
  private static final String DIALOG_TITLE = "About...";
  private static final int WIDTH = 200;
  private static final int HEIGHT = 100;
  
  // Private Attributes
  //
  private Shell dialog;
}
