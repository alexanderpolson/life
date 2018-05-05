/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition.specification;


import com.orbitalsoftware.life.common.AbstractLifeObject;

import com.orbitalsoftware.life.common.law.condition.LawConditionExistsException;
import com.orbitalsoftware.life.common.law.condition.LawConditionManager;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class AbstractLawConditionSpecification
  extends AbstractLifeObject
  implements LawConditionSpecification
{
  // Constructors
  //
  protected AbstractLawConditionSpecification
    ( String name, String description, Class lawConditionClass )
    throws LawConditionExistsException
  {
    super( name, description );
    this.lawConditionClass = lawConditionClass;
    LawConditionManager.register( this );
  }

  // Methods
  //
  public Class getLawConditionClass()
  {
    return lawConditionClass;
  }

  // Statics
  //

  // Private Attributes
  //

  private Class lawConditionClass;
}