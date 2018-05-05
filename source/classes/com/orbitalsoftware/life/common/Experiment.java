/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an experiment that is performed.
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class Experiment
  extends AbstractLifeObject
  implements Runnable, Cloneable
{
  // Constructors
  //

  /**
   * Creates a new <code>Experiment</code> object with the provided name
   * and description.
   *
   * @param name The name to give to this <code>Experiment</code>.
   * @param description The description to give to this
   *   <code>Experiment</code>.
   */
  public Experiment( String name, String description )
  {
    super( null, name, description );
    initialize();
  }

  /**
   * Creates a new <code>Experiment</code> object with the provided name.
   *
   * @param name The name to give to this <code>Experiment</code>.
   */
  public Experiment( String name )
  {
    super( null, name );
    initialize();
  }

  /**
   * Copies an instance of an <code>Experiment</code>.
   *
   * @param experiment The <code>Experiment</code> to copy.
   */
  public Experiment( Experiment experiment )
  {
    super( null, experiment.getName(), experiment.getDescription() );
    
    // Copy primitives
    runningState = experiment.runningState;
    runIndefinitely = experiment.runIndefinitely;
    numRounds = experiment.numRounds;
    currentRound = experiment.currentRound;
    world = experiment.world;

    initialize();

    Iterator i = experiment.worldStates.iterator();

    while( i.hasNext() )
    {
      worldStates.add( new WorldCells( (WorldCells)i.next() ) );
    }
    
    finalState = (WorldCells)i.next();

    i = experiment.experimentListeners.iterator();

    // This process is only gone through to ensure that when new
    // ExperimentListeners are added to the original Experiment the copy won't
    // be affected.
    while( i.hasNext() )
    {
      experimentListeners.add( i.next() );
    }
    
    initialState = new WorldCells( experiment.initialState );
  }

  // Methods
  //

  /**
   * Helps initialize a new <code>Experiment</code> object.
   */
  private void initialize()
  {
    world = new World( this );
    initialState = new WorldCells( world );
    worldStates = new ArrayList();
    experimentListeners = new ArrayList();
  }
  
  public void setInitialState( WorldCells world )
  {
    initialState = world;
  }

  /**
   * @return The initial state of the <code>Experiment</code>'s
   *   <code>World</code>.
   * @throws InitialStateNotDefinedException If the initial <code>World</code>
   *   state has not been set yet.
   */
  public WorldCells getInitialState()
  {
    return initialState;
  }

  /**
   * @return The final state of the <code>Experiment</code>'s
   *   <code>World</code>.
   */
  public WorldCells getFinalState()
    throws ExperimentNotRunException
  {
    if( finalState != null )
    {
      return finalState;
    }
    else
    {
      throw new ExperimentNotRunException();
    }
  }

  /**
   * @return The current state of the <code>Experiment</code>'s
   *   <code>World</code>. Unless the <code>Experiment</code> is being
   *   &quot;replayed&quot; this method will return the final state of the
   *   <code>Experiment</code>.
   * @throws ExperimentNotRunningException If the <code>Experiment</code> isn't
   *   running (since it relies on the fact that it's running to get the data
   *   requested.
   */
  public WorldCells getCurrentState()
    throws ExperimentNotRunningException
  {
    if( currentState != null )
    {
      return currentState;
    }
    else
    {
      throw new ExperimentNotRunningException();
    }
  }

  /**
   * Sets the number of rounds that the <code>Experiment</code> will be
   * performed for.
   * <p>
   * <b>Note:</b> If the <code>Experiment</code> is set to run indefinitely
   * then this value will be ignored.
   *
   * @param rounds The number of rounds that the <code>Experiment</code> will
   *   be performed for.
   */
  public void setNumRounds( int rounds )
  {
    this.numRounds = rounds;
  }

  /**
   * @return The number of rounds that the <code>Experiment</code> will be
   *   performed for.
   */
  public int getNumRounds()
  {
    return numRounds;
  }

  /**
   * @return The current round that the <code>Experiment</code> is in.
   */
  public int getCurrentRound()
  {
    return currentRound;
  }

  /**
   * Starts the <code>Experiment</code> and runs it for the specified number
   * of rounds or until it is stopped.
   */
  public void start()
  {
    switch( runningState )
    {
      case( STOPPED ):
        runningState = RUNNING;
        currentState = initialState;
        worldStates.add( currentState );
        
        if( experimentThread == null )
        {
          experimentThread = new Thread( this, "Experiment" );
          experimentThread.start();       
        }
        break;
      case( PAUSED ):
        runningState = RUNNING;
        break;
      case( RUNNING ):
        break;
      default:
        break;
    }
  }

  /**
   * Gets the current running state of the <code>Experiment</code>.
   *
   * @return An integer representing the running state of the
   *   <code>Experiemnt</code>:
   *   <ul>
   *     <li>STOPPED</li>
   *     <li>RUNNING</li>
   *     <li>PAUSED</li>
   *   </ul>
   */
  public int getRunningState()
  {
    return runningState;
  }

  /**
   * Gets whether or not the <code>Experiment</code> is running. An
   * <code>Experiment</code> is considered running if it is either in the
   * running or paused state.
   *
   * @return true if the <code>Experiment</code> is running, false otherwise.
   */
  public boolean isRunning()
  {
    if( runningState == STOPPED )
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  /**
   * <code>Runnable</code> implementation. Starts the <code>Experiment</code>
   * and continues the until either the number of rounds that it is set to run
   * for has been reached or it is manually stopped.
   */
  public void run()
  {
    while( runningState != STOPPED )
    {
      if( runningState == PAUSED )
      {
        while( runningState == PAUSED )
        {
          // No Op
          // Wait for running state to change.
        }
      }
      else
      {
        // Experiment is running.
        startRound();
        currentState = world.applyRules();
        worldStates.add( currentState );
        endRound();
        
        try
        {
          Thread.sleep( 250 );
        }
        catch( InterruptedException ie )
        {
          
        }
      }
    }
    experimentThread = null;
  }

  /**
   * Temporarily pauses the <code>Experiment</code>.
   */
  public void pause()
  {
    if( runningState == RUNNING )
    {
      runningState = PAUSED;
    }
  }

  /**
   * Stops the currently running <code>Experiment</code>.
   */
  public void stop()
  {
    runningState = STOPPED;
  }

  /**
   * Adds a single <code>ExperimentListener</code> to the
   * <code>Experiment</code>.
   *
   * @param listener An <code>ExperimentListener</code> to be added to the
   *   <code>Experiment</code>.
   */
  public void addExperimentListener( ExperimentListener listener )
  {
    experimentListeners.add( listener );
  }
  
  /**
   * Removes the provided <code>ExperimentListener</code> from the Experiment.
   *
   * @param listener The <code>ExperimentListner</code> attempting to be
   *   removed from the <code>Experiment</code>.
   * @return true if the <code>ExperimentListener</code> was successfully
   *   removed, false otherwise.
   */
  public boolean removeExperimentListener( ExperimentListener listener )
  {
    return experimentListeners.remove( listener );
  }

  /**
   * Removes all <code>ExperimentListeners</code> from the
   * <code>Experiment</code>.
   */
  public void clearListeners()
  {
    experimentListeners.clear();
  }

  /**
   * A helper method for startRound and endRound.
   */
  private void roundEventHelper( int roundEvent )
  {
    Iterator i = experimentListeners.iterator();
    ExperimentListener listener = null;

    while( i.hasNext() )
    {
      listener = (ExperimentListener) i.next();

      switch( roundEvent )
      {
        case( ROUND_START ):
          listener.roundStarted( null ); //new Experiment( this ) );
          break;
        case( ROUND_END ):
          listener.roundEnded( null ); //new Experiment( this ) );
          break;
        default:
          // TBD: Do nothing or throw Exception?
          break;
      }
      
    }
  }

  /**
   * Informs all the registered <code>ExperimentListener</code> objects that
   * a round of the <code>Experiment</code> has begun. This event is
   * triggered before any changes have been made. A copy of the current
   * <code>Experiment</code> is passed to each of the listeners.
   */
  private void startRound()
  {
    roundEventHelper( ROUND_START );
  }

  /**
   * Informs all the registered <code>ExperimentListener</code> objects that
   * a round of the <code>Experiment</code> has ended. This event is
   * triggered after all changes have been made. A copy of the current
   * <code>Experiment</code> is passed to each of the listeners.
   */
  private void endRound()
  {
    roundEventHelper( ROUND_END );
  }
  
  public void setWorld( World world )
  {
    this.world = world;
  }
  
  public World getWorld()
  {
    return world;
  }
  
  public void parse( String state )
  {
    // TODO: implement this later.
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return new Experiment( this );
  }

  // Statics
  //
  public static final int STOPPED     = 0;
  public static final int RUNNING     = 1;
  public static final int PAUSED      = 2;

  private static final int ROUND_START = 0;
  private static final int ROUND_END   = 1;

  // Private Attributes
  //
  private int runningState;
  private boolean runIndefinitely;
  private int numRounds;
  private int currentRound;
  private World world;
  private WorldCells initialState;
  private WorldCells currentState;
  private WorldCells finalState;
  private List worldStates;
  private List experimentListeners;
  private Thread experimentThread;
}
