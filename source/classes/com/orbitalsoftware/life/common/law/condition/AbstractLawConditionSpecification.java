/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import com.orbitalsoftware.life.common.AbstractLifeObject;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public abstract class AbstractLawConditionSpecification
  extends AbstractLifeObject
  implements LawConditionSpecification
{
  // Constructors
  //
  protected AbstractLawConditionSpecification
    ( String name, String description )
    throws LawConditionExistsException
  {
    super( null, name, description );
  }
}