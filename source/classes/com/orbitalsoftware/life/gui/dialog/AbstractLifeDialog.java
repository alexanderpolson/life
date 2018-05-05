/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.dialog;


import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.widgets.Shell;

/**
 * Implementation of basic <code>LifeDialog</code> functionality.
 *
 * @author  $Author$
 * @version $Revision$
 */
public abstract class AbstractLifeDialog
  implements LifeDialog
{
  public AbstractLifeDialog( Shell parent )
  {
    this( parent, "" );
  }
  
  public AbstractLifeDialog( Shell parent, String text )
  {
    dialog = new Shell( parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL );
    setText( text );
  }
  
  public void open()
  {
    dialog.pack();
    dialog.open();
  }
  
  public void setText( String text )
  {
    dialog.setText( text );
  }
  
  public void addSelectionListener( SelectionListener listener )
  {
    
  }
  
  public Shell getShell()
  {
    return dialog;
  }
  
  // Private Attributes
  //
  protected Shell dialog;
}
