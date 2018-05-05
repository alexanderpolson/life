/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common;


/**
 * Represents a possible state of a <code>Cell</code>.
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */
public class CellState
  extends AbstractLifeObject
{
  // Constructors
  //

  /**
   * Creates a <code>CellState</code> with the provided name and a blank
   * description.
   *
   * @param world The <code>World</code> that owns this <code>CellState</code>.
   * @param name The name given to the <code>CellState</code>.
   */
  public CellState( World world, String name )
  {
    super( world, name );
  }
  
  /**
   * Creates a <code>CellState</code> with the provided name and description.
   *
   * @param world The <code>World</code> that owns this <code>Cell</code>.
   * @param name The name given to the <code>CellState</code>.
   * @param description The description given to the <code>CellState</code>.
   */
  public CellState( World world, String name, String description )
  {
    super( world, name, description );
  }
  
  // Methods
  //
  
  public void parse( String state )
  {
    // TODO: Implement this later.
  }
}