/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common;


import com.orbitalsoftware.common.text.ParseException;

/**
 * Abstract base class for those that want don't need to rewrite the basic
 * functionality of a <code>LifeObject</code>.
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public abstract class AbstractLifeObject
  implements LifeObject
{
  // Constructors
  //
  /**
   * Creates an <code>AbstractLifeObject</code> with a blank name and
   * description.
   */
  public AbstractLifeObject( LifeObject owner )
  {
    this( owner, "", "" );
  }

  /**
   * Creaets an <code>AbstractLifeObject</code> with the specified name and
   * a blank description.
   *
   * @param name The name to give this <code>AbstractLifeObject</code>.
   */
  public AbstractLifeObject( LifeObject owner, String name )
  {
    this( owner, name, "" );
  }

  /**
   * Creates an <code>AbstractLifeObject</code> with the specified name and
   * description.
   *
   * @param name The name to give this <code>AbstractLifeObject</code>.
   * @param description The description to give this
   *   <code>AbstractLifeObject</code>.
   */
  public AbstractLifeObject( LifeObject owner, String name, String description )
  {
    setName( name );
    setDescription( description );
    setOwner( owner );
  }
  
  /**
   * Creates an <code>AbstractLifeObject</code> with the specified name,
   * description and state.
   *
   * @param name The name to give this <code>AbstractLifeObject</code>.
   * @param description The description to give this
   *   <code>AbstractLifeObject</code>.
   * @param state The <code>String</code> encoded initial state of this
   *   <code>AbstractLifeObject</code>.
   */
  public AbstractLifeObject( LifeObject owner, String name, String description, String state )
    throws ParseException
  {
    setName( name );
    setDescription( description );
    parse( state );
  }

  // Methods
  //
  public void setName( String name )
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public void setDescription( String description )
  {
    this.description = description;
  }

  public String getDescription()
  {
    return description;
  }
  
  public void setOwner( LifeObject owner )
  {
    this.owner = owner;
  }
  
  public LifeObject getOwner()
  {
    return owner;
  }

  public boolean lessThan( LifeObject that )
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException( MSG_OP_NOT_SUPPORTED );
  }

  public boolean greaterThan( LifeObject that )
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException( MSG_OP_NOT_SUPPORTED );
  }

  public boolean lessThanOrEqualTo( LifeObject that )
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException( MSG_OP_NOT_SUPPORTED );
  }

  public boolean greaterThanOrEqualTo( LifeObject that )
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException( MSG_OP_NOT_SUPPORTED );
  }

  public boolean equals( LifeObject that )
  {
    if( that == null )
    {
      return false;
    }
    
    if( this == that )
    {
      return true;
    }
    else
    {
      if( !this.getClass().getName().equals( that.getClass().getName() ) )
      {
        return false;
      }
      else
      {
        if(
          this.name.equals( ((AbstractLifeObject) that).name ) &&
          this.description.equals( ((AbstractLifeObject) that).description )
          )
        {
          return true;
        }
        else
        {
          return false;
        }
      }
    }
  }

  // Statics
  //
  private static final String MSG_OP_NOT_SUPPORTED =
    "This operation is not supported by this LawConditionOperand.";
    

  // Private Attributes
  //
  private String name;
  private String description;
  private LifeObject owner;
}