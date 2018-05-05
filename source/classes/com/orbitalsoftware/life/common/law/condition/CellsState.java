/*
 * Created on Nov 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.Cell;
import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.common.law.Law;

import com.orbitalsoftware.common.text.ParseException;

/**
 * @author  $Author$
 * @version $Revision$
 *
 * Whether or not there is a neighboring cell at a particular distance
 * with a particular state.
 */
public class CellsState
  extends AbstractLawCondition
{
  // Constructors
  //
  
  public CellsState( Law law )
  {
    super( law );
    init();
  }
  
  protected void init()
  {
    super.init();
    initProperty( PROPERTY_STATE, ( (World)getOwner().getOwner() ).getDefaultCellState() );
  }

  public boolean appliesToCell( Cell cell )
  {
    try
    {
      if( cell.getState() == getProperty( PROPERTY_STATE ) )
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    catch( NoSuchLawConditionPropertyException nslcpe )
    {
      System.err.println( "For some reason the property doesn't exist." );
    }
    
    return false;
  }

  public void parse( String condition )
    throws ParseException
  {
    
  }
  
  // Statics
  //
  public static final String PROPERTY_STATE = "state";
}
