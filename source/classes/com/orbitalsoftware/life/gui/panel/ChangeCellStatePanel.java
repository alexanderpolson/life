/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.common.law.Law;

import com.orbitalsoftware.life.common.law.command.ChangeCellStateCommand;
import com.orbitalsoftware.life.common.law.command.LawCommand;
import com.orbitalsoftware.life.common.law.command.NoSuchLawCommandParameterException;

import com.orbitalsoftware.life.gui.CellStateCombo;

import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.Iterator;

/**
 * [Descripition here]
 *
 * @author  $Author$
 * @version $Revision$
 */
public class ChangeCellStatePanel
    extends LawCommandPanel
{

  /**
   * @param parent
   * @param style
   * @param lawCondition
   */
  public ChangeCellStatePanel( Composite parent, int style,
      LawCommand lawCommand )
  {
    super( parent, style, lawCommand );
    init();
  }
  
  private void init()
  {
    GridData layoutData = null;
    layout = new GridLayout();
    layout.numColumns = 2;
    
    setLayout( layout );
    
    cellStateLabel = new Label( this, SWT.NONE );
    cellStateLabel.setText( "Cell State:" );
    
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    cellStateCombo =
      new CellStateCombo(
          this, SWT.DROP_DOWN | SWT.READ_ONLY,
          (World)lawCommand.getOwner().getOwner() );
    cellStateCombo.setLayoutData( layoutData );
  }

  /* (non-Javadoc)
   * @see com.orbitalsoftware.life.gui.panel.LawConditionPanel#commit()
   */
  public void commit()
  {
    try
    {
      lawCommand.setParameter(
        ChangeCellStateCommand.PARAMETER_STATE, cellStateCombo.getCellState() );
      ((Law)lawCommand.getOwner()).addCommand( lawCommand );
      lawCommand.setName(
          "Change cell state to " + cellStateCombo.getCellState().getName() );
    }
    catch( NoSuchLawCommandParameterException nslcpe )
    {
      
    }
  }
  
  // Private Attributes
  //
  private Label cellStateLabel;
  private CellStateCombo cellStateCombo;
}