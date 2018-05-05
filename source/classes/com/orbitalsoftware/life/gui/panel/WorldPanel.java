/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.World;


import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * A panel used to either add or edit <code>Experiment</code>s.
 *
 * @author  $Author$
 * @version $Revision$
 */
public class WorldPanel
  extends Composite
{
  public WorldPanel( Composite parent, int style, World world )
  {
    super( parent, style );
    init( world );
  }
  
  private void init( World world )
  {
    this.world = world;
    GridData layoutData = null;
    layout = new GridLayout();
    layout.numColumns = 1;
    
    setLayout( layout );
    
    // Create LifeObjectWidget
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.widthHint = 250;
    low = new LifeObjectPanel( this, SWT.NONE, world );
    low.setLayoutData( layoutData );
    
    // Create World specific properties
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    wsp = new WorldSizePanel( this, SWT.NONE, world );
    wsp.setLayoutData( layoutData );
    
    // Create CellStateManagementPanel
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    csmp = new CellStateManagementPanel( this, SWT.NONE, world );
    csmp.setLayoutData( layoutData );
    
    // Create LawManagementPanel
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    lmp = new LawManagementPanel( this, SWT.NONE, world );
    lmp.setLayoutData( layoutData );
  }
  
  public void commit()
  {
    low.commit();
    wsp.commit();
  }
  
  // Private Attributes
  //
  private World world;
  private GridLayout layout;
  private LifeObjectPanel low;
  private WorldSizePanel wsp;
  private CellStateManagementPanel csmp;
  private LawManagementPanel lmp;
  
  private class WorldSizePanel
    extends Composite
  {
    // Constructors
    //
    public WorldSizePanel( Composite parent, int style, World world )
    {
      super( parent, style );
      
      this.world = world;
      
      GridLayout layout = new GridLayout();
      GridData layoutData = null;
      layout.numColumns = 4;
      setLayout( layout );
      
      // Width Controls
      widthLabel = new Label( this, SWT.NONE );
      widthLabel.setText( "Width:" );
      widthText = new Text( this, SWT.BORDER );
      layoutData = new GridData();
      layoutData.grabExcessHorizontalSpace = true;
      layoutData.horizontalAlignment = GridData.FILL;
      widthText.setLayoutData( layoutData );
      widthText.setText( ( new Integer( world.getWidth() ) ).toString() );
      
      // Height Controls
      heightLabel = new Label( this, SWT.NONE );
      heightLabel.setText( "Height:" );
      heightText = new Text( this, SWT.BORDER );
      layoutData = new GridData();
      layoutData.grabExcessHorizontalSpace = true;
      layoutData.horizontalAlignment = GridData.FILL;
      heightText.setLayoutData( layoutData );
      heightText.setText( ( new Integer( world.getHeight() ) ).toString() );
    }
    
    // Methods
    //
    
    public void commit()
    {
//    TODO: This needs to be changed so bad input can't get out.
      try
      {
        world.setWidth( Integer.parseInt( widthText.getText() ) );
        world.setHeight( Integer.parseInt( heightText.getText() ) );
      }
      catch( NumberFormatException nfe )
      {
        world.setWidth( World.DEFAULT_WIDTH );
        world.setHeight( World.DEFAULT_HEIGHT );
      }
    }
    
    // Private Attributes
    //
    private World world;
    private Label widthLabel;
    private Text widthText;
    private Label heightLabel;
    private Text heightText;
  }
}
