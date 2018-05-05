/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law.condition;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public final class LawConditionOperatorManager
{
  // Constructor
  //

  private LawConditionOperatorManager()
  {
    operators = new ArrayList();

    register( LawConditionOperator.EQUALS );
    register( LawConditionOperator.LESSTHAN );
    register( LawConditionOperator.GREATERTHAN );
    register( LawConditionOperator.LESSTHANOREQUALTO );
    register( LawConditionOperator.GREATERTHANOREQUALTO );
  }
  
  // Methods
  //

  private void register( LawConditionOperator operator )
  {
    operators.add( operator );
  }
  
  public LawConditionOperatorManager getInstance()
  {
    if( instance == null )
    {
      instance = new LawConditionOperatorManager();
    }
    
    return instance;
  }

  public LawConditionOperator getOperator( String name )
    throws NoSuchLawConditionOperatorException
  {
    Iterator i = operators.iterator();
    LawConditionOperator operator = null;
    
    while( i.hasNext() )
    {
      operator = (LawConditionOperator)i.next();

      if( operator.getName().equals( name ) )
      {
        return operator;  
      }
    }

    throw new NoSuchLawConditionOperatorException
      ( "The requested operator (" + name + ") doesn't exist." );
  }

  // Statics
  //

  private static LawConditionOperatorManager instance;

  // Private Attributes
  //
  
  private List operators;
}