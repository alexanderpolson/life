/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common;


import com.orbitalsoftware.common.graphics.SimplePoint;

import com.orbitalsoftware.life.common.law.Law;
import com.orbitalsoftware.life.common.law.command.LawCommand;
import com.orbitalsoftware.life.common.law.condition.LawCondition;
import com.orbitalsoftware.life.common.law.condition.LawConditionManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Represents a &quot;living&quot; world with <code>Laws</code> and citizens
 * (<code>Cell</code> objects).
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class World
  extends AbstractLifeObject
{
  // Constructors
  //
  
  public World( Experiment experiment )
  {
    super( experiment, "", "" );
    cells = new WorldCells( this );
    setWidth( DEFAULT_WIDTH );
    setHeight( DEFAULT_HEIGHT );
    base();
  }

  /**
   * Creates a <code>World</code> object with the specified defaultCellState.
   *
   * @param The <code>Experiment</code> that owns this <code>World</code>.
   * @param defaultCellState a <code>CellState</code> type.
   */
  public World( Experiment experiment, CellState defaultCellState )
  {
    this( experiment, defaultCellState, DEFAULT_WIDTH, DEFAULT_HEIGHT );
  }

  /**
   * Creates a <code>World</code> object with the specified defaultCellState,
   * width and height
   *
   * @param The <code>Experiment</code> that owns this <code>World</code>.
   * @param defaultCellState a <code>CellState</code> type.
   * @param width an int type.
   * @param height an int type.
   */
  public World( Experiment experiment, CellState defaultCellState, int width, int height )
  {
    this( experiment, defaultCellState, width, height, "" );
  }

  /**
   * Creates a <code>World</code> object with the specified defaultCellState,
   * width and height
   *
   * @param The <code>Experiment</code> that owns this <code>World</code>.
   * @param defaultCellState a <code>CellState</code> type.
   * @param width an int type.
   * @param height an int type.
   */
  public World( Experiment experiment, CellState defaultCellState, int width, int height,
      String name )
  {
    this( experiment, defaultCellState, width, height, name, "" );
  }

  /**
   * Creates a <code>World</code> object with the specified defaultCellState,
   * width and height
   *
   * @param The <code>Experiment</code> that owns this <code>World</code>.
   * @param defaultCellState a <code>CellState</code> type.
   * @param width an int type.
   * @param height an int type.
   */
  public World( Experiment experiment, CellState defaultCellState, int width, int height,
      String name, String description )
  {
    super( experiment, name, description );
    cells = new WorldCells( this, defaultCellState );
    setWidth( width );
    setHeight( height );
    base();
    setDefaultCellState( defaultCellState );
    cells.init();
  }
  
  private void base()
  {
    cellStates = new ArrayList();
    laws = new ArrayList();
  }

  // Methods
  //

  /**
   * Adds a <code>Law</code> to the set of <code>Law</code> objects that
   * govern this <code>World</code>.
   */
  public void addLaw( Law law )
  {
    if( law != null )
    {
      laws.add( law );
    }
  }
  
  public void addLaw( int index, Law law )
  {
    if( law != null )
    {
      laws.add( index, law );
    }
  }
  
  public Law getLaw( int index )
  {
    return (Law)laws.get( index );
  }

  /**
   * Removes a <code>Law</code> from the set of <code>Law</code> objects.
   *
   * @param law a <code>Law</code> type.
   */
  public boolean removeLaw( Law law )
  {
    return laws.remove( law );
  }
  
  // TODO: This seems like it could be dangerous when doing multiple deletions.
  public boolean removeLaw( int index )
  {
    return laws.remove( laws.get( index ) );
  }
  
  public Iterator lawIterator()
  {
    return laws.iterator();
  }
  
  public int getNumLaws()
  {
    return laws.size();
  }

  /**
   * Adds a state to the set of <code>CellState</code> objects that the
   * <code>World</code> supports.
   *
   * @param cellState
   */
  public void addCellState( CellState cellState )
  {
    if( cellState != null && !cellStates.contains( cellState ) )
    {
      if( defaultCellState == null )
      {
        defaultCellState = cellState;
      }
      cellStates.add( cellState );
    }
  }

  /**
   * Removes the provided <code>CellState</code> from the <code>World</code>.
   *
   * @param cellState a <code>CellState</code> type.
   * @return a boolean type.
   */
  public boolean removeCellState( CellState cellState )
  {
    return cellStates.remove( cellState );
  }
  
  public boolean removeCellState( int index )
  {
    return cellStates.remove( cellStates.get( index ) );
  }

  /**
   * Sets the default <code>CellState</code> of the <code>World</code>.
   *
   * @param cellState a <code>CellState</code> type.
   */
  public void setDefaultCellState( CellState cellState )
  {
    if( !cellStates.contains( cellState ) )
    {
      addCellState( cellState );
    }
    
    defaultCellState = cellState;
  }
  
  public CellState getCellState( int index )
  {
    return (CellState)cellStates.get( index );
  }

  /**
   * Gets the default <code>CellState</code> of the <code>World</code>.
   *
   * @return a <code>CellState</code> type.
   */
  public CellState getDefaultCellState()
  {
    return defaultCellState;
  }
  
  public int getNumCellStates()
  {
    return cellStates.size();
  }
  
  public Iterator cellStateIterator()
  {
    return  cellStates.iterator();
  }
  
  public CellState getNextCellState( CellState state )
  {
    // Just in case we need the one after the last.
    Iterator it = cellStates.iterator();
    
    CellState first = null;
    CellState current = null;
    
    while( it.hasNext() )
    {
      current = (CellState)it.next();
      if( first == null )
      {
        first = current;
      }
      
      if( state == current )
      {
        if( it.hasNext() )
        {
          return (CellState)it.next();
        }
        else
        {
          return first;
        }
      }
    }
    
    return null;
  }

  public WorldCells getCells()
  {
    return cells;
  }

  /**
   * Sets the width of the <code>World</code>.
   *
   * @param width The width that the <code>World</code> should be.
   */
  public void setWidth( int width )
  {
    cells.setWidth( width );
  }
  
  /**
   * Gets the width of the <code>World</code>.
   *
   * @return The width of the <code>World</code>.
   */
  public int getWidth()
  {
    return cells.getWidth();
  }

  /**
   * Sets the height of the <code>World</code>.
   *
   * @param height The height the <code>World</code> should be.
   */
  public void setHeight( int height )
  {
    cells.setHeight( height );
  }

  /**
   * Gets the height of the <code>World</code>.
   *
   * @return The height of the <code>World</code>.
   */
  public int getHeight()
  {
    return cells.getHeight();
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
    return cells.findCellPoint( cell );
  }

  public Cell getCell( SimplePoint point )
    throws IndexOutOfBoundsException
  {
    return cells.getCell( point );
  }
  
  /**
   * Determines the distance between to <code>Cell</code> points. Does not use
   * the standard distance formula. Instead it deals in whole numbers.
   * 
   * @param point1 the origin.
   * @param point2 the destination.
   * @return the distance between the two points.
   */
  public int getDistance( SimplePoint point1, SimplePoint point2 )
  {
    int distance1 = Math.abs( point1.getX() - point2.getX() );
    int distance2 = Math.abs( point1.getY() - point2.getY() );
    
    return (distance1 > distance2 ? distance1: distance2 );
  }
  
  /**
   * Creates an array of neighboring <code>Cell</code>s based on the provided
   * distance.
   * 
   * @param point the point where the <code>Cell</code> who's neighbors will be
   *   found.
   * @param distance the distance the neighbors are away from the
   *   <code>Cell</code> in question. Must be greater than 0. If not null will
   *   be returned.
   * @return an array of <code>Cell</code>s that are the neighbors of the
   *   <code>Cell</code> in question.
   * @throws IndexOutOfBoundsException when the provided
   *   <code>SimplePoint</code> is out of range.
   */
  public Cell[] getCellNeighbors( SimplePoint point, int distance )
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
      if( distance < 1 )
      {
        return null;
      }
      else
      {
        List neighbors = new ArrayList();
        
        // TODO: Improve this crappy method.
        for( int i = point.getX() - distance; i <= point.getX() + distance &&
          i < getWidth(); ++i )
        {
          for( int j = point.getY() - distance; j <= point.getY() + distance &&
            j < getHeight(); ++j )
          {
            if( getDistance( point, new SimplePoint( i, j ) ) == distance )
            {
              if( i >= 0 && i < getWidth() )
              {
                if( j >= 0 && j < getWidth() )
                {
                  neighbors.add( cells.getCell( new SimplePoint( i, j ) ) );
                }
              }
            }
          }
        }
        
        return (Cell[])neighbors.toArray( new Cell[1] );
      }
    }
  }
  
  public Cell processCell( SimplePoint point )
    throws IndexOutOfBoundsException
  {
    // Used to go through the Laws.
    Law law = null;
    Iterator it = laws.iterator();
    Cell cell = new Cell( getCell( point ) );
    
    while( it.hasNext() )
    {
      law = (Law)it.next();
      if( law.applies( point ) )
      {
        law.applyCommands( cell );
      }
    }
    
    return cell;
  }
  
  public WorldCells applyRules()
  {
    // Take a snapshot to use for analysis.
    WorldCells worldState = new WorldCells( cells );
    SimplePoint coords = null;
    
    for( int i = 0; i < worldState.getWidth(); ++i )
    {
      for( int j = 0; j < worldState.getHeight(); ++j )
      {
        coords = new SimplePoint( i, j );
        worldState.setCell( coords, processCell( coords ) );
      }
    }
    
    cells = worldState;
    
    return new WorldCells( cells );
  }
  
  public void parse( String state )
  {
    // TODO: Implement this later.
  }

  // Statics
  //

  /**
   * The default width of a <code>World</code>.
   */
  public static final int DEFAULT_WIDTH = 10;

  /**
   * The default height of a <code>World</code>.
   */
  public static final int DEFAULT_HEIGHT = 10;

  // Private Attributes
  //

  /**
   * Possible states that each <code>Cell</code> can have.
   */
  protected List cellStates;

  /**
   * The default <code>CellState</code> used by the <code>World</code>.
   */
  protected CellState defaultCellState;

  /**
   * <code>Laws</code> that apply to the <code>World</code>.
   */
  private List laws;

  private WorldCells cells;
}
