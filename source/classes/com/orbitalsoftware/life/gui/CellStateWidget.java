/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 *  Overview:
 *    The GUI version of a Cell
 */

package com.orbitalsoftware.life.gui;

import com.orbitalsoftware.life.common.CellState;
import com.orbitalsoftware.life.common.World;

import org.eclipse.swt.graphics.RGB;

/**
 * Encapsulates a <code>CellState</code> and associates a color with it.
 *
 * @author   $Author$
 * @version  $Revision$
 */
public class CellStateWidget
  extends CellState
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
  public CellStateWidget( World world, String name, RGB color )
  {
    super( world, name );
    setColor( color );
  }
  
  /**
   * Creates a <code>CellState</code> with the provided name and description.
   *
   * @param world The <code>World</code> that owns this <code>Cell</code>.
   * @param name The name given to the <code>CellState</code>.
   * @param description The description given to the <code>CellState</code>.
   */
  public CellStateWidget( World world, String name, String description, RGB color )
  {
    super( world, name, description );
    setColor( color );
  }

  // Methods
  //
  
  /**
   * Sets the <code>RGB</code> color that this <code>CellStateWidget</code>
   * should be.
   * 
   * @param color the <code>RGB</code> color that this
   *   <code>CellStateWidget</code> should be.
   */
  public void setColor( RGB color )
  {
    this.color = color;
  }
  
  /**
   * Gets the <code>RGB</code> color for this <code>CellStateWidget</code>.
   * 
   * @return the <code>RGB</code> color for this <code>CellStateWidget</code>.
   */
  public RGB getColor()
  {
    return color;
  }
  
  public void parse( String state )
  {
    // TODO: Implement this later.
  }

  // Statics
  //
  public static final RGB DEFAULT_COLOR = new RGB( 0, 0, 0 );

  // Private Attributes
  //
  private RGB color;
}