/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.common.law.Law;

import com.orbitalsoftware.life.common.law.condition.LawCondition;
import com.orbitalsoftware.life.common.law.condition.NeighborState;
import com.orbitalsoftware.life.common.law.condition.NoSuchLawConditionPropertyException;

import com.orbitalsoftware.life.gui.CellStateCombo;

import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * [Descripition here]
 *
 * @author  $Author$
 * @version $Revision$
 */
public class CellsStatePanel
    extends LawConditionPanel
{

  /**
   * @param parent
   * @param style
   * @param lawCondition
   */
  public CellsStatePanel( Composite parent, int style,
      LawCondition lawCondition )
  {
    super( parent, style, lawCondition );
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
    cellStates =
      new CellStateCombo(
          this, SWT.DROP_DOWN | SWT.READ_ONLY,
          (World)lawCondition.getOwner().getOwner() );
    cellStates.setLayoutData( layoutData );
  }

  /* (non-Javadoc)
   * @see com.orbitalsoftware.life.gui.panel.LawConditionPanel#commit()
   */
  public void commit()
  {
    try
    {
      lawCondition.setProperty(
          NeighborState.PROPERTY_STATE,
          cellStates.getCellState()
          );
      lawCondition.setName(
          "Cell is in " + cellStates.getCellState().getName() +
          " state." );
      ((Law)lawCondition.getOwner()).addCondition( lawCondition );
    }
    catch( NoSuchLawConditionPropertyException nsclcpe )
    {
      
    }
    catch( NumberFormatException nfe )
    {
      
    }
  }
  
  // Private Attributes
  //
  private Label cellStateLabel;
  private CellStateCombo cellStates;
}
