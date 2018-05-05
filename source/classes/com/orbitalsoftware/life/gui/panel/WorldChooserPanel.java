/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.gui.dialog.WorldDialog;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * [Descripition here]
 *
 * @author  $Author$
 * @version $Revision$
 */
public class WorldChooserPanel
  extends Composite
  implements SelectionListener
{
  public WorldChooserPanel( Composite parent, int style, World world )
  {
    super( parent, style );
    init( world );
  }

  private void init( World world )
  {
    this.world = world;
    layout = new GridLayout();
    layout.numColumns = 2;
    
    setLayout( layout );
    
    // Add widgets
    GridData layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    worldLabel = new Label( this, SWT.BORDER );
    worldLabel.setLayoutData( layoutData );
    worldButton = new Button( this, SWT.PUSH );
    worldButton.setText( "..." );
    worldButton.addSelectionListener( this );
    
    worldLabel.setText( world.getName() );
  }
  
  public void widgetSelected( SelectionEvent e )
  {
    if( e.getSource() == worldButton )
    {
      dialog = new WorldDialog( getShell(), world );
      dialog.setText( "Create new World..." );
      dialog.getOkCancel().getOk().addSelectionListener( this );
      dialog.open();
    }
    else if( dialog != null && e.getSource() == dialog.getOkCancel().getOk() )
    {
      worldLabel.setText( world.getName() );
      world.getCells().init();
    }
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  // Private Attributes
  //
  private World world;
  private Label worldLabel;
  private Button worldButton;
  private GridLayout layout;
  private WorldDialog dialog;
}
