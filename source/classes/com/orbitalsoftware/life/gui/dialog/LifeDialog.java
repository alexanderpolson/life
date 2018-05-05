/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.dialog;


import org.eclipse.swt.events.SelectionListener;

/**
 * Basic behavior of a dialog in the Life application.
 *
 * @author  $Author$
 * @version $Revision$
 */
public interface LifeDialog
{
  /**
   * Opens the dialog.
   */
  public void open();
  
  /**
   * Sets the text of the dialog.
   * 
   * @param text the title to use for the dialog.
   */
  public void setText( String text );
  
  public void addSelectionListener( SelectionListener listener );
}
