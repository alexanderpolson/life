/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.CellState;
import com.orbitalsoftware.life.common.LifeInteger;
import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.common.law.Law;

import com.orbitalsoftware.life.common.law.condition.LawCondition;
import com.orbitalsoftware.life.common.law.condition.NumberOfNeighbors;
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
public class NumberOfNeighborsPanel
    extends LawConditionPanel
{

  /**
   * @param parent
   * @param style
   * @param lawCondition
   */
  public NumberOfNeighborsPanel( Composite parent, int style,
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
    
    numNeighborsLabel = new Label( this, SWT.NONE );
    numNeighborsLabel.setText( "Number of neighbors" );
    
    numNeighborsText = new Text( this, SWT.BORDER );
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    numNeighborsText.setLayoutData( layoutData );
    
    neighborDistanceLabel = new Label( this, SWT.NONE );
    neighborDistanceLabel.setText( "Distance:" );
    
    neighborDistanceText = new Text( this, SWT.BORDER );
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    neighborDistanceText.setLayoutData( layoutData );
    
    statesLabel = new Label( this, SWT.NONE );
    statesLabel.setText( "Cell State:" );
    
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    statesCombo =
      new CellStateCombo(
          this, SWT.DROP_DOWN | SWT.READ_ONLY,
          (World)lawCondition.getOwner().getOwner() );
    statesCombo.setLayoutData( layoutData );
  }

  /* (non-Javadoc)
   * @see com.orbitalsoftware.life.gui.panel.LawConditionPanel#commit()
   */
  public void commit()
  {
    try
    {
      CellState state = statesCombo.getCellState();
      
      lawCondition.setProperty(
          NumberOfNeighbors.PROPERTY_DISTANCE,
          new LifeInteger(
              lawCondition, Integer.parseInt( neighborDistanceText.getText() )
              )
          );
      lawCondition.setProperty(
          NumberOfNeighbors.PROPERTY_NUMNEIGHBORS,
          new LifeInteger(
              lawCondition, Integer.parseInt( numNeighborsText.getText() )
              )
          );
      lawCondition.setProperty( NumberOfNeighbors.PROPERTY_STATE, state );
      lawCondition.setName(
          numNeighborsText.getText() + " neighbors " +
          neighborDistanceText.getText() + " spaces away in the " +
          state.getName() + " state." );
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
  private Label numNeighborsLabel;
  private Text numNeighborsText;
  private Label neighborDistanceLabel;
  private Text neighborDistanceText;
  private Label statesLabel;
  private CellStateCombo statesCombo;
}
