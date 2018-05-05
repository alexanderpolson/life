/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition.operand;


import com.orbitalsoftware.life.common.law.condition.LawCondition;

import com.orbitalsoftware.common.text.Parseable;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public interface LawConditionOperand
  extends Parseable
{
  // Methods
  //

  /**
   * Determines whether or not this operand is equal to that operand.
   *
   * @param that The <code>LawConditionOperand</code> to compare this to.
   * @throw <code>UnsupportedOperationException</code> if this operation isn't
   *   supported.
   * @return true if the two are equal, false otherwise.
   */
  public boolean equals( LawConditionOperand that )
    throws UnsupportedOperationException;

  /**
   * Determines whether or not this operand is less than that operand.
   *
   * @param that The <code>LawConditionOperand</code> to compare this to.
   * @throw <code>UnsupportedOperationException</code> if this operation isn't
   *   supported.
   * @return true if this is less than that, false otherwise.
   */
  public boolean lessThan( LawConditionOperand that )
    throws UnsupportedOperationException;

  /**
   * Determines whether or not this operand is greater than that operand.
   *
   * @param that The <code>LawConditionOperand</code> to compare this to.
   * @throw <code>UnsupportedOperationException</code> if this operation isn't
   *   supported.
   * @return true if this is greater than that, false otherwise.
   */
  public boolean greaterThan( LawConditionOperand that )
    throws UnsupportedOperationException;

  /**
   * Determines whether or not this operand is less than or equal to that
   * operand.
   *
   * @param that The <code>LawConditionOperand</code> to compare this to.
   * @throw <code>UnsupportedOperationException</code> if this operation isn't
   *   supported.
   * @return true if this is less than or equal to that, false otherwise.
   */
  public boolean lessThanOrEqualTo( LawConditionOperand that )
    throws UnsupportedOperationException;

  /**
   * Determines whether or not this operand is greater than or equal to that
   * operand.
   *
   * @param that The <code>LawConditionOperand</code> to compare this to.
   * @throw <code>UnsupportedOperationException</code> if this operation isn't
   *   supported.
   * @return true if this is greater or equal to than that, false otherwise.
   */
  public boolean greaterThanOrEqualTo( LawConditionOperand that )
    throws UnsupportedOperationException;
}