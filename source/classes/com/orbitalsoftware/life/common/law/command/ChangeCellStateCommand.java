/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.common.law.command;

import com.orbitalsoftware.life.common.Cell;
import com.orbitalsoftware.life.common.CellState;
import com.orbitalsoftware.life.common.LifeInteger;
import com.orbitalsoftware.life.common.World;
import com.orbitalsoftware.life.common.law.Law;
import com.orbitalsoftware.life.common.law.condition.NoSuchLawConditionPropertyException;

/**
 * [Descripition here]
 *
 * @author  $Author$
 * @version $Revision$
 */
public class ChangeCellStateCommand
    extends AbstractLawCommand
{

  /**
   * @param law
   */
  public ChangeCellStateCommand( Law law )
  {
    super( law );
    
    initParameter( "state", ( (World)getOwner().getOwner() ).getDefaultCellState() );
  }

  /* (non-Javadoc)
   * @see com.orbitalsoftware.life.common.law.command.LawCommand#execute(com.orbitalsoftware.life.common.Cell)
   */
  public void execute( Cell cell ) {
    // TODO Auto-generated method stub
    try
    {
      cell.setState( (CellState) getParameter( PARAMETER_STATE ) );
    }
    catch( NoSuchLawCommandParameterException nslcpe )
    {
    }
  }

  public void parse( String state )
  {
    
  }
  
  // Statics
  //
  public static final String PARAMETER_STATE = "state";
}
