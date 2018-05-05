/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.common;

import java.util.Iterator;

import com.orbitalsoftware.common.graphics.SimplePoint;

/**
 * [Descripition here]
 *
 * @author  $Author$
 * @version $Revision$
 */
public class WorldCells
  extends AbstractLifeObject
{
  public WorldCells( World owner )
  {
    this( owner, null );
  }
  
  public WorldCells( World owner, CellState defaultCellState )
  {
    super( owner );
    this.defaultCellState = defaultCellState;
  }
  
  /**
   * Creates a copy of a <code>World</code>.
   *
   * @param world a <code>World</code> type.
   */
  public WorldCells( WorldCells world )
  {
    super( world.getOwner(), world.getName(), world.getDescription() );
    setHeight( world.getHeight() );
    setWidth( world.getWidth() );
    defaultCellState = world.getDefaultCellState();
    
    // Copies the cells.
    cells = new Cell[world.getWidth()][world.getHeight()];
    
    for( int i = 0; i < getWidth(); ++i )
    {
      for( int j = 0; j < getHeight(); ++j )
      {
        cells[i][j] = new Cell( world.cells[i][j], (World)this.getOwner() );
      }
    }
  }
  
  // Methods
  //
  
  /**
   * Sets the width of the <code>World</code>.
   *
   * @param width The width that the <code>World</code> should be.
   */
  public void setWidth( int width )
  {
    this.width = width;
  }
  
  /**
   * Gets the width of the <code>World</code>.
   *
   * @return The width of the <code>World</code>.
   */
  public int getWidth()
  {
    return width;
  }
  
  /**
   * Sets the height of the <code>World</code>.
   *
   * @param height The height the <code>World</code> should be.
   */
  public void setHeight( int height )
  {
    this.height = height;
  }

  /**
   * Gets the height of the <code>World</code>.
   *
   * @return The height of the <code>World</code>.
   */
  public int getHeight()
  {
    return height;
  }
  
  public void setDefaultCellState( CellState state )
  {
    defaultCellState = state;
  }
  
  public CellState getDefaultCellState()
  {
    return defaultCellState;
  }
  
  /**
   * Determines the point at which a <code>Cell</code> resides.
   *
   * @param cell The <code>Cell</code> that is being searched for.
   * @return A <code>SimplePoint</code> object that contains the (x, y)
   *   coordinates where the <code>Cell</code> lies in the <code>World</code>.
   *   If the <code>Cell</code> doesn't exist than <code>null</code> is returned.
   */
  public SimplePoint findCellPoint( Cell cell )
  {
    for( int i = 0; i < width; ++i )
    {
      for( int j = 0; j < height; ++j )
      {
        if( cells[ i ][ j ] == cell )
        {
          return new SimplePoint( i, j );
        }
      }
    }

    return null;
  }
  
  public Cell getCell( SimplePoint point )
    throws IndexOutOfBoundsException
  {
    if( point.getX() < 0 || point.getY() < 0 || point.getX() > getWidth()
        || point.getY() > getHeight() )
    {
      throw new IndexOutOfBoundsException(
        "The point (" + point.getX() + ", " + point.getY() + ") is invalid."
        );
    }
    else
    {
      return cells[ point.getX() ][ point.getY() ];
    }
  }
  
  public void setCell( SimplePoint point, Cell cell )
  {
    cells[point.getX()][point.getY()] = cell;
  }
  
  /**
   * Initialize the size of the <code>World</code>.
   */
  public void init()
  {
    int oldWidth = 0;
    int oldHeight = 0;
    Cell[][] oldCells = null;
    
    if( cells != null )
    {
      oldWidth = cells.length;
      oldHeight = cells[0].length;
      oldCells = cells;
    }
    
    World owner = (World)getOwner();
    
    cells = new Cell[ width ][ height ];

    for( int i = 0; i < width; ++i )
    {
      for( int j = 0; j < height; ++j )
      {
        // Copy old Cells if they exist.
        if( i < oldWidth && j < oldHeight )
        {
          cells[ i ][ j ] = oldCells[ i ][ j ];
        }
        else
        {
          cells[ i ][ j ] =
            new Cell( owner, "(" + i + ", " + j + ")", owner.getDefaultCellState() );
        }
      }
    }
  }
  
  public void parse( String state )
  {
    
  }
  
  // Private attributes
  //
  private int width;
  private int height;
  
  /**
   * Represents the state of the <code>World</code>.
   */
  private Cell[][] cells;
  private CellState defaultCellState;
}
