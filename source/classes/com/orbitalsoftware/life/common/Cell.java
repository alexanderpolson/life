/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common;


/**
 * Represents a citizen of a <code>World</code> object or a cell in the
 * <code>World</code>.
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class Cell
  extends AbstractLifeObject
{
  // Constructors
  //

  /**
   * Creates a <code>Cell</code> with the provided name and the provided
   * initial <code>CellState</code>.
   *
   * @param world The <code>World</code> that owns this <code>Cell</code>.
   * @param name The name given to the <code>Cell</code>.
   * @param state The initial state of the <code>Cell</code>.
   */
  public Cell( World world, String name, CellState state )
  {
    super( world, name );
    this.world = world;
    setState( state );
  }

  /**
   * Creates a new <code>Cell</code> with the provided name, description and
   * <code>CellState</code>.
   *
   * @param world The <code>World</code> that owns this <code>Cell</code>.
   * @param name The name to give the new <code>Cell</code>.
   * @param description The description to give the new <code>Cell</code>.
   * @param state The initial state of the <code>Cell</code>.
   */
  public Cell( World world, String name, String description, CellState state )
  {
    super( world, name, description );
    this.world = world;
    setState( state );
  }

  /**
   * Makes a copy of a <code>Cell</code>.
   *
   * @param cell The <code>Cell</code> to copy.
   */
  public Cell( Cell cell )
  {
    this( cell, (World)cell.getOwner() );
  }
  
  public Cell( Cell cell, World owner )
  {
    super( owner, cell.getName(), cell.getDescription() );
    setState( cell.getState() );
  }

  // Methods
  //

  /**
   * Sets the state of the current <code>Cell</code>.
   *
   * @param state The <code>CellState</code> to give the <code>Cell</code>.
   */
  public void setState( CellState state )
  {
    this.state = state;
  }

  /**
   * Gets the state of the current <code>Cell</code>.
   *
   * @return The state of the current <code>Cell</code>.
   */
  public CellState getState()
  {
    return state;
  }
  
  public void parse( String state )
  {
    // TODO: Implement this later.
  }

  public boolean equals( LifeObject that )
  {
    if( !super.equals( that ) )
    {
      return false;
    }
    else
    {
      if( !( that instanceof Cell) && this.state != ((Cell) that).state )
      {
        return false;
      }
      else
      {
        return true;
      }
    }
  }

  // Private Attributes
  //

  /**
   * The state that the <code>Cell</code> is in.
   */
  private CellState state;
  
  /**
   * The owning <code>World</code>.
   */
  private World world;
}
