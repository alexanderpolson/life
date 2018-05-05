/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law;


import com.orbitalsoftware.life.common.AbstractLifeObject;
import com.orbitalsoftware.life.common.Cell;
import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.common.law.condition.LawCondition;

import com.orbitalsoftware.life.common.law.command.LawCommand;

import com.orbitalsoftware.common.graphics.SimplePoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class Law
  extends AbstractLifeObject
{
  // Constructors
  //
  
  /**
   * Creates a new <code>Law</code> with provided name that is owned by the
   * provided <code>World</code>.
   * 
   * @param world The <code>World</code> that owns this <code>Law</code>.
   * @param name The unique name given to this <code>Law</code>
   */
  public Law( World world, String name )
  {
    super( world, name );
    init();
  }

  /**
   * Creates a new <code>Law</code> with provided name that is owned by the
   * provided <code>World</code>.
   * 
   * @param world The <code>World</code> that owns this <code>Law</code>.
   * @param name The unique name given to this <code>Law</code>
   * @param description A description given to the <code>Law</code>.
   */
  public Law( World world, String name, String description )
  {
    super( world, name, description );
    init();
  }

  // Methods
  //
  
  /**
   * Performs common object initialization.
   */
  private void init()
  {
    conditions = new ArrayList();
    commands = new ArrayList();
  }
  
  /**
   * Adds a condition for the current <code>Law</code>.
   * 
   * @param condition The <code>LawCondition</code> to add to the
   * <code>Law</code>.
   */
  public void addCondition( LawCondition condition )
  {
    conditions.add( condition );
  }
  
  public void addCondition( int index, LawCondition condition )
  {
    conditions.add( index, condition );
  }
  
  public Iterator conditionIterator()
  {
    return conditions.iterator();
  }
  
  public LawCondition getCondition( int index )
  {
    return (LawCondition)conditions.get( index );
  }

  public boolean removeCondition( LawCondition condition )
  {
    return conditions.remove( condition );
  }
  
  public boolean removeCondition( int index )
  {
    return conditions.remove( conditions.get( index ) );
  }

  public void clearConditions()
  {
    conditions.clear();
  }

  public int getNumConditions()
  {
    return conditions.size();
  }

  public void addCommand( LawCommand command )
  {
    commands.add( command );
  }
  
  public void addCommand( int index, LawCommand command )
  {
    commands.add( index, command );
  }
  
  public Iterator commandIterator()
  {
    return commands.iterator();
  }
  
  public LawCommand getCommand( int index )
  {
    return (LawCommand)commands.get( index );
  }

  public boolean removeCommand( LawCommand command )
  {
    return commands.remove( command );
  }
  
  public boolean removeCommand( int index )
  {
    return commands.remove( commands.get( index ) );
  }

  public void clearCommands()
  {
    commands.clear();
  }

  public int getNumCommands()
  {
    return commands.size();
  }
  
  public boolean applies( SimplePoint point )
  {
    if( point.getX() < 0 || point.getY() < 0 ||
        point.getX() > ((World)getOwner()).getWidth() ||
        point.getY() > ((World)getOwner()).getHeight() )
    {
      throw new IndexOutOfBoundsException(
        "The point (" + point.getX() + ", " + point.getY() + ") is invalid."
        );
    }
    else
    {
      Iterator i = conditions.iterator();
      LawCondition condition;
      Cell cell = ((World)getOwner()).getCell( point );
  
      while( i.hasNext() )
      {
        condition = (LawCondition) i.next();
  
        if( !condition.appliesToCell( cell ) )
        {
          return false;
        }
      }  
    }
    
    // If execution got this far than the Law applies to the Cell.
    return true;
  }
  
  public void applyCommands( Cell cell )
  {
    Iterator i = commands.iterator();
    LawCommand command;

    while( i.hasNext() )
    {
      command = (LawCommand) i.next();

      command.execute( cell );
    }
  }
  
  public void parse( String state )
  {
    // TODO: Implement this later on.
  }
  
  // Statics
  //

  // Private Attributes
  //
  private List conditions;
  private List commands;
}