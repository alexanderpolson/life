/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.common.text;


import com.orbitalsoftware.common.text.ParseException;

import java.io.Serializable;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 */

public interface Parseable
  extends Serializable
{
  // Methods
  //

  /**
   * Takes a <code>String</code> representation of the object state and gives
   * the current object that state.
   *
   * @param state The <code>String</code> representation of the object state.
   */
  public void parse( String state )
    throws ParseException;

  /**
   * Returns a <code>String</code> representation of this
   * <code>Parseable</code> object such that the <code>parse( String )</code>
   * method can read the full state of the object. This is just meant to ensure
   * that the default implementation of <code>toString()</code> in
   * <code>Object</code> is not used.
   *
   * @return A <code>String</code> representation of this
   *   <code>Parseable</code> object.
   */
  public String toString();
}