/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.AbstractLifeObject;
import com.orbitalsoftware.life.common.Cell;
import com.orbitalsoftware.life.common.LifeObject;

import com.orbitalsoftware.life.common.law.Law;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public abstract class AbstractLawCondition
  extends AbstractLifeObject
  implements LawCondition
{
  // Constructors
  //
  protected AbstractLawCondition( Law law )
  {
    super( law );
    init();
  }
  
  protected AbstractLawCondition( Law law, String name )
  {
    super( law, name );
    init();
  }
  
  protected AbstractLawCondition( Law law, String name, String description )
  {
    super( law, name, description );
    init();
  }

  // Methods
  //
  
  /**
   * Used for <code>AbstractLawCondition</code> initialization that is common
   * to all constructors.
   */
  protected void init()
  {
    properties = new HashMap();
  }
  
  protected void initProperty( String name, LifeObject operand )
  {
    properties.put( name, operand );
  }
  
  public Law getLaw()
  {
    return law;
  }
  
  public abstract boolean appliesToCell( Cell cell );

  public LifeObject setProperty(
    String property, LifeObject value
    )
    throws NoSuchLawConditionPropertyException, IllegalArgumentException
  {
    if( properties.containsKey( property ) )
    {
      LifeObject old =
        (LifeObject)properties.get( property );
      
      if( value != null &&
          value.getClass().getName().equals( old.getClass().getName() ) )
      {
        return (LifeObject)properties.put( property, value );
      }
      else
      {
        throw new IllegalArgumentException(
          "Either the value passed is null or its class is not correct."
          );
      }   
    }
    else
    {
      throw new NoSuchLawConditionPropertyException(
        "Requested property is invalid."
        );
    }
  }

  public LifeObject getProperty( String property )
    throws NoSuchLawConditionPropertyException
  {
    if( properties.containsKey( property ) )
    {
      return (LifeObject)properties.get( property );
    }
    else
    {
      throw new NoSuchLawConditionPropertyException(
        "Requested property is invalid."
        );
    }
  }

  // Statics
  //

  // Private Attributes
  //
  private Law law;
  protected Map properties;
}