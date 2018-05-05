/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * [Descripition here]
 *
 * @author  $Author$
 * @version $Revision$
 */
public class ColorChooserPanel
  extends Composite
  implements SelectionListener
{
  public ColorChooserPanel( Composite parent, int style )
  {
    super( parent, style );
    init();
  }

  private void init()
  {
    layout = new GridLayout();
    layout.numColumns = 2;
    
    setLayout( layout );
    
    // Add widgets
    GridData layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    colorLabel = new Label( this, SWT.BORDER );
    colorLabel.setLayoutData( layoutData );
    colorButton = new Button( this, SWT.PUSH );
    colorButton.setText( "..." );
    colorButton.addSelectionListener( this );
  }
  
  public void setColor( RGB color )
  {
    this.color = color;
    colorLabel.setBackground( new Color( getDisplay(), color ) );
  }
  
  public RGB getColor()
  {
    return color;
  }
  
  // SelectionListener implementation
  public void widgetSelected( SelectionEvent e )
  {
    dialog = new ColorDialog( getShell() );
    
    if( color != null )
    {
      dialog.setRGB( color );
    }
    
    color = dialog.open();
    if( color != null )
    {
      // Prevents overuse of 
      Color tempColor = colorLabel.getBackground();
      colorLabel.setBackground( new Color( getDisplay(), color ) );
      tempColor.dispose();
    }
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  // Private Attributes
  //
  private ColorDialog dialog;
  private RGB color;
  private Label colorLabel;
  private Button colorButton;
  private GridLayout layout;
}
