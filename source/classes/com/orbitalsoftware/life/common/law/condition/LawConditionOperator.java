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

public final class LawConditionOperator
  extends AbstractLifeObject
{
  // Constructors
  //

  private LawConditionOperator( String name, String description )
  {
    super( null, name, description );
  }

  // Methods
  //
  
  public void parse( String state )
  {
    // TODO: I'm not sure if I'll need this.
  }

  // Statics
  //
  public  static final String EQUALS_NAME        = "=";
  private static final String EQUALS_DESCRIPTION = "equals";
  public  static final String LESSTHAN_NAME     = "<";
  private static final String LESSTHAN_DESCRIPTION = "less than";
  public  static final String GREATERTHAN_NAME = ">";
  private static final String GREATERTHAN_DESCRIPTION = "greater than";
  public  static final String LESSTHANOREQUALTO_NAME = "<=";
  private static final String LESSTHANOREQUALTO_DESCRIPTION
    = "less than or equal to";
  public  static final String GREATERTHANOREQUALTO_NAME = ">=";
  private static final String GREATERTHANOREQUALTO_DESCRIPTION
    = "greater than or equal to";
  
  public static final LawConditionOperator EQUALS =
    new LawConditionOperator( EQUALS_NAME, EQUALS_DESCRIPTION );
  public static final LawConditionOperator LESSTHAN =
    new LawConditionOperator( LESSTHAN_NAME, LESSTHAN_DESCRIPTION );
  public static final LawConditionOperator GREATERTHAN =
    new LawConditionOperator( GREATERTHAN_NAME, GREATERTHAN_DESCRIPTION );
  public static final LawConditionOperator LESSTHANOREQUALTO =
    new LawConditionOperator(
      LESSTHANOREQUALTO_NAME, LESSTHANOREQUALTO_DESCRIPTION
      );
  public static final LawConditionOperator GREATERTHANOREQUALTO =
    new LawConditionOperator(
      GREATERTHANOREQUALTO_NAME, GREATERTHANOREQUALTO_DESCRIPTION
      );
}