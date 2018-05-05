/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.LifeObject;
import com.orbitalsoftware.life.common.Cell;


/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public interface LawCondition
  extends LifeObject
{
  // Methods
  //

  /**
   * Returns true if the <code>LawCondition</code> applies to the provided
   * <code>Cell</code>.
   *
   * @param cell The <code>Cell</code> to check.
   * @return true if the the <code>LawCondition</code> applies.
   */
  public boolean appliesToCell( Cell cell );

  /**
   * Sets the specified property to the specified value.
   *
   * @param property The property name to set.
   * @param value The value to give the named property.
   * @throw <code>NoSuchLawConditionPropertyException</code> if the named
   *   property doesn't exist.
   * @throw <code>IllegalArgumentException</code> if the value type is not
   *   consistent with the property type or the value is null.
   * @return The old value of the named property.
   */
  public LifeObject setProperty( String property, LifeObject value )
    throws NoSuchLawConditionPropertyException, IllegalArgumentException;

  /**
   * Gets the value of the specified property. If the property isn't set then
   * the method returns null.
   *
   * @param property The name of the property to retrieve.
   * @throw <code>NoSuchLawConditionPropertyException</code> if the requested
   *   property doesn't exist for this <code>LawCondition</code>.
   * @return The <code>LifeObject</code> referred to by the property
   *   name.
   */
  public LifeObject getProperty( String property )
    throws NoSuchLawConditionPropertyException;
}