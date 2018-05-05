/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common;


import com.orbitalsoftware.common.text.Parseable;

/**
 * Interface for all common methods used by the different objects in the
 * Life application.
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public interface LifeObject
  extends Parseable
{
  // Methods
  //

  /**
   * @param name The name to give this <code>LifeObject</code>.
   */
  public void setName( String name );

  /**
   * @returns The name that was previously given to this
   *   <code>LifeObject</code>.
   */
  public String getName();

  /**
   * @param description The description to give to this
   *   <code>LifeObject</code>.
   */
  public void setDescription( String description );

  /**
   * @returns The description that was previously given to this
   *   <code>LifeObject</code>.
   */
  public String getDescription();
  
  /**
   * Defines who the owner of this <code>LifeObject</code> is. it is valid for
   * this value to be null.
   * 
   * @param owner the owner of this <code>LifeObject</code>.
   */
  public void setOwner( LifeObject owner );
  
  /**
   * Gets the owner of this <code>LifeObject</code>.
   * 
   * @return the owner of this <code>LifeObject</code>.
   */
  public LifeObject getOwner();
  
  /**
   * Determines whether or not this object is equal to that operand.
   *
   * @throw <code>UnsupportedOperationException</code> if this operation isn't
   *   supported.
   * @return true if the two are equal, false otherwise.
   */
  public boolean equals( LifeObject that )
    throws UnsupportedOperationException;

  /**
   * Determines whether or not this operand is less than that operand.
   *
   * @param that The <code>LifeOperand</code> to compare this to.
   * @throw <code>UnsupportedOperationException</code> if this operation isn't
   *   supported.
   * @return true if this is less than that, false otherwise.
   */
  public boolean lessThan( LifeObject that )
    throws UnsupportedOperationException;

  /**
   * Determines whether or not this operand is greater than that operand.
   *
   * @param that The <code>LifeOperand</code> to compare this to.
   * @throw <code>UnsupportedOperationException</code> if this operation isn't
   *   supported.
   * @return true if this is greater than that, false otherwise.
   */
  public boolean greaterThan( LifeObject that )
    throws UnsupportedOperationException;

  /**
   * Determines whether or not this operand is less than or equal to that
   * operand.
   *
   * @param that The <code>LifeOperand</code> to compare this to.
   * @throw <code>UnsupportedOperationException</code> if this operation isn't
   *   supported.
   * @return true if this is less than or equal to that, false otherwise.
   */
  public boolean lessThanOrEqualTo( LifeObject that )
    throws UnsupportedOperationException;

  /**
   * Determines whether or not this operand is greater than or equal to that
   * operand.
   *
   * @param that The <code>LifeOperand</code> to compare this to.
   * @throw <code>UnsupportedOperationException</code> if this operation isn't
   *   supported.
   * @return true if this is greater or equal to than that, false otherwise.
   */
  public boolean greaterThanOrEqualTo( LifeObject that )
    throws UnsupportedOperationException;
}