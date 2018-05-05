/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.law.Law;


import com.orbitalsoftware.common.text.ParseException;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class LawConditionManager
{
  // Constructors
  //
  private LawConditionManager()
  {
    conditions = new HashMap();
  }

  // Methods
  //
  
  public static LawConditionManager getInstance()
  {
    if( instance == null )
    {
      instance = new LawConditionManager();
    }
    
    return instance;
  }

  /**
   * Registers the <code>LawCondition</code> with the
   * <code>LawConditionManager</code>.
   *
   * @param specification The specification to register with the manager.
   * @throw <code>LawConditionExistsException</code> if the specification that
   *   is trying to be registered has already been registered.
   */
  public void register( LawConditionSpecification specification )
    throws LawConditionExistsException
  {
    conditions.put(
      specification.getName(), specification );
  }
  
  public static String[] keys()
  {
    return (String[]) getInstance().conditions.keySet().toArray(new String[1]);
  }

  /**
   * Attempts to get the <code>LawConditionSpecification</code> for the
   * <code>LawCondition</code> specified by the condition's name.
   *
   * @param condition The name of the <code>LawCondition</code> that is being
   *   requested.
   * @return The <code>LawConditionSpecification</code> that is associated
   *   with the named <code>LawCondition</code>.
   */
  public LawConditionSpecification getLawConditionSpecification(
      String condition )
    throws NoSuchLawConditionException
  {
    LawConditionSpecification specification =
      (LawConditionSpecification) conditions.get( condition );

    if( specification != null )
    {
      return specification;
    }
    else
    {
      throw new NoSuchLawConditionException(
        "The requested LawCondition doesn't exist" );
    }
  }

  /**
   * Gets an instance of the <code>LawCondition</code> that is named.
   *
   * @param name The name of the <code>LawCondition</code> to get an instance
   *   of.
   * @throw <code>NoSuchLawConditionException</code> if the requested
   *   <code>LawCondition</code> hasn't been defined.
   * @return The <code>LawCondition</code> that was named.
   */
  public LawCondition getLawConditionInstance( Law law, String condition )
    throws NoSuchLawConditionException, IllegalAccessException,
    InstantiationException
  {
    return getLawConditionSpecification( condition ).getLawConditionInstance( law );
  }

  /**
   * Gets an instance of the <code>LawCondition</code> that is named and
   * initializes it with the <code>String</code> representation of the object's
   * state .
   *
   * @param name The name of the <code>LawCondition</code> to get an instance
   *   of.
   * @param state The initial state that the <code>LawCondition</code> should
   *   be given.
   * @throw <code>NoSuchLawConditionException</code> if the requested
   *   <code>LawCondition</code> hasn't been defined.
   * @throw <code>ParseException</code> if the state <code>String</code>
   *   provided is invalid for the requested <code>LawCondition</code>.
   * @return The <code>LawCondition</code> that was named.
   */
  public LawCondition getLawConditionInstance( Law law, String condition, String state )
    throws NoSuchLawConditionException, ParseException, IllegalAccessException,
    InstantiationException
  {
    LawCondition conditionInstance = getLawConditionInstance( law, condition );
    conditionInstance.parse( state );
    
    return conditionInstance;
  }
  
  // Statics
  //
  
  private static LawConditionManager instance;

  // Private Attributes
  //
  private Map conditions;
}