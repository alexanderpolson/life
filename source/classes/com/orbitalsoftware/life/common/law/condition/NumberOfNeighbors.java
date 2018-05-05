/*
 * Created on Nov 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.Cell;
import com.orbitalsoftware.life.common.LifeInteger;
import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.common.law.Law;

import com.orbitalsoftware.common.graphics.SimplePoint;

import com.orbitalsoftware.common.text.ParseException;

/**
 * @author  $Author$
 * @version $Revision$
 *
 * Whether or not there is a neighboring cell at a particular distance
 * with a particular state.
 */
public class NumberOfNeighbors
  extends AbstractLawCondition
{
  // Constructors
  //
  
  public NumberOfNeighbors( Law law )
  {
    super( law );
    init();
  }
  
  protected void init()
  {
    super.init();
    initProperty( PROPERTY_DISTANCE, new LifeInteger( this ) );
    initProperty( PROPERTY_NUMNEIGHBORS, new LifeInteger( this ) );
    initProperty( PROPERTY_STATE, ( (World)getOwner().getOwner() ).getDefaultCellState() );
  }

  public boolean appliesToCell( Cell cell )
  {
    World world = (World)cell.getOwner();
    SimplePoint cellPoint = world.findCellPoint( cell );
    int numNeighbors = 0;
    
    try
    {
      Cell[] neighbors =
        world.getCellNeighbors( cellPoint,
            ((LifeInteger)getProperty( PROPERTY_DISTANCE )).getValue() );
      
      for( int i = 0; i < neighbors.length; ++i )
      {
        if( neighbors[i].getState() == getProperty( PROPERTY_STATE ) )
        {
          numNeighbors++;
        }
      }
      
      if( numNeighbors == ((LifeInteger)getProperty( PROPERTY_NUMNEIGHBORS )).getValue() )
      {
        return true;
      }
    }
    catch( NoSuchLawConditionPropertyException nslcpe )
    {
      System.err.println( "For some reason the " + PROPERTY_DISTANCE +
          "property doesn't exist." );
    }
    
    return false;
  }

  public void parse( String condition )
    throws ParseException
  {
    
  }
  
  // Statics
  //
  public static final String PROPERTY_DISTANCE = "distance";
  public static final String PROPERTY_NUMNEIGHBORS = "numNeighbors";
  public static final String PROPERTY_STATE = "state";
}
