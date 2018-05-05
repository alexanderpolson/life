/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.LifeObject;

import com.orbitalsoftware.life.common.law.Law;


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
   * @param law The <code>Law</code> that owns this
   *   <code>LawConditionSpecification</code>.
   * @return An instance of the <code>LawCondition</code> that is referred to
   *   by this <code>LawConditionSpecification</code>.
   */
  public LawCondition getLawConditionInstance( Law law );
}