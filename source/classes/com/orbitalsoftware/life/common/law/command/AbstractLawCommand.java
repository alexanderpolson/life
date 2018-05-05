/*
 * Created on Nov 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.orbitalsoftware.life.common.law.command;

import java.util.HashMap;
import java.util.Map;

import com.orbitalsoftware.life.common.AbstractLifeObject;
import com.orbitalsoftware.life.common.Cell;
import com.orbitalsoftware.life.common.LifeObject;

import com.orbitalsoftware.life.common.law.Law;
import com.orbitalsoftware.life.common.law.condition.NoSuchLawConditionPropertyException;

/**
 * @author apolson
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class AbstractLawCommand
  extends AbstractLifeObject
  implements LawCommand
{
  // Constructors
  //
  
  protected AbstractLawCommand( Law law )
  {
    super( law );
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
    parameters = new HashMap();
  }
  
  protected void initParameter( String name, LifeObject operand )
  {
    parameters.put( name, operand );
  }
  
  public LifeObject setParameter( String parameter, LifeObject value )
    throws NoSuchLawCommandParameterException, IllegalArgumentException
  {
    if( parameters.containsKey( parameter ) )
    {
      LifeObject old =
        (LifeObject)parameters.get( parameter );

      if( value != null &&
          value.getClass().getName().equals( old.getClass().getName() ) )
      {
        return (LifeObject)parameters.put( parameter, value );
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
      throw new NoSuchLawCommandParameterException(
        "Requested property is invalid."
        );
    }
  }

  public LifeObject getParameter( String parameter )
    throws NoSuchLawCommandParameterException
  {
    if( parameters.containsKey( parameter ) )
    {
      return (LifeObject)parameters.get( parameter );
    }
    else
    {
      throw new NoSuchLawCommandParameterException(
        "Requested property is invalid."
        );
    }
  }

  public abstract void execute( Cell cell );

  // Private Attributes
  //
  private Map parameters;
  private Law law;
}
