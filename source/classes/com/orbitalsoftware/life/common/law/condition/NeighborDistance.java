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
public class NeighborDistance
  extends AbstractLawCondition
{
  // Constructors
  //
  
  public NeighborDistance( Law law )
  {
    super( law );
    init();
  }
  
  protected void init()
  {
    super.init();
    initProperty( PROPERTY_DISTANCE, new LifeInteger( this ) );
  }

  public boolean appliesToCell( Cell cell )
  {
    World world = (World)cell.getOwner();
    SimplePoint cellPoint = world.findCellPoint( cell );
    
    try
    {
      Cell[] neighbors =
        world.getCellNeighbors( cellPoint,
            ((LifeInteger)getProperty( PROPERTY_DISTANCE )).getValue() );
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
  private static final String PROPERTY_DISTANCE = "distance";
}
