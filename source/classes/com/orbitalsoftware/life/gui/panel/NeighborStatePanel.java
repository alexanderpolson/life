/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.LifeInteger;
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
import org.eclipse.swt.widgets.Text;

/**
 * [Descripition here]
 *
 * @author  $Author$
 * @version $Revision$
 */
public class NeighborStatePanel
    extends LawConditionPanel
{

  /**
   * @param parent
   * @param style
   * @param lawCondition
   */
  public NeighborStatePanel( Composite parent, int style,
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
    
    neighborDistanceLabel = new Label( this, SWT.NONE );
    neighborDistanceLabel.setText( "Neighbor Distance:" );
    
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    neighborDistanceText = new Text( this, SWT.BORDER );
    neighborDistanceText.setLayoutData( layoutData );
    
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
          NeighborState.PROPERTY_DISTANCE,
          new LifeInteger(
              lawCondition, Integer.parseInt( neighborDistanceText.getText() )
              )
          );
      lawCondition.setProperty(
          NeighborState.PROPERTY_STATE,
          cellStates.getCellState()
          );
      lawCondition.setName(
          "Neighbor is " + cellStates.getCellState().getName() +
          " and is " + neighborDistanceText.getText() + " spaces away." );
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
  private Label neighborDistanceLabel;
  private Text neighborDistanceText;
  private Label cellStateLabel;
  private CellStateCombo cellStates;
}
