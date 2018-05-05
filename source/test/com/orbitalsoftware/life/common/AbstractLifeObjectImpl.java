/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common;

/**
 * A concrete implementation of <code>AbstractLifeObject</code> that
 * facilitates testing of the methods that are implemented.
 *
 * @author   Alex Polson
 * @version  $Revision$
 */
public class AbstractLifeObjectImpl
  extends AbstractLifeObject
{
  // Constructors
  //
  public AbstractLifeObjectImpl()
  {
    super();
  }
  
  public AbstractLifeObjectImpl( String name )
  {
    super( name );
  }

  public AbstractLifeObjectImpl( String name, String description )
  {
    super( name, description );
  }
}