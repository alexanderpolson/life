/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition.specification;


import com.orbitalsoftware.life.common.LifeObject;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public interface LawConditionSpecification
  extends LifeObject
{
  // Methods
  //

  /**
   * @return The the <code>LawCondition</code> <code>Class</code> associated
   * with this <code>LawConditionSpecification</code>.
   */
  public Class getLawConditionClass();
}