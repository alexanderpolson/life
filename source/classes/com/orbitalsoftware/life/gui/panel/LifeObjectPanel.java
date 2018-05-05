/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.LifeObject;

import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Encapsulates basic <code>LifeObject</code> information in a GUI widget.
 *
 * @author  $Author$
 * @version $Revision$
 */
public class LifeObjectPanel
  extends Composite
{
  // Constructors
  //
  
  public LifeObjectPanel( Composite parent, int style, LifeObject lifeObject )
  {
    super( parent, style );
    
    this.lifeObject = lifeObject;
    
    GridData layoutData = null;
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    setLayout( layout );
    
    nameLabel = new Label( this, SWT.LEFT );
    nameLabel.setText( "Name:" );
    nameText = new Text( this, SWT.BORDER );
    nameText.setText( lifeObject.getName() );
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    nameText.setLayoutData( layoutData );
    
    descriptionLabel = new Label( this, SWT.LEFT );
    descriptionLabel.setText( "Description:" );
    descriptionText = new Text( this, SWT.BORDER );
    descriptionText.setText( lifeObject.getDescription() );
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    descriptionText.setLayoutData( layoutData );
  }
  
  // Methods
  //
  
  public void commit()
  {
    lifeObject.setName( nameText.getText() );
    lifeObject.setDescription( descriptionText.getText() );
  }
  
  // Private Attributes
  //
  private LifeObject lifeObject;
  private Label      nameLabel;
  private Text       nameText;
  private Label      descriptionLabel;
  private Text       descriptionText;
}