/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.common;


import java.io.Serializable;

/**
 * [Descripition here]
 *
 * @author  $Author$
 * @version $Revision$
 */
public class ExperimentPersistenceObject
  implements Serializable
{
  public ExperimentPersistenceObject( Experiment experiment )
  {
    name = experiment.getName();
    description = experiment.getName();
    numRounds = experiment.getNumRounds();
    world = experiment.getWorld();
    world.setOwner( null );
    initialState = experiment.getInitialState();
    initialState.setOwner( null );
  }
  
  public Experiment getExperiment()
  {
    Experiment experiment = new Experiment( name, description );
    initialState.setOwner( experiment );
    world.setOwner( experiment );
    experiment.setWorld( world );
    initialState.setOwner( world );
    experiment.setInitialState( initialState );
    experiment.setNumRounds( numRounds );
    
    return experiment;
  }
  
  // Private Attributes
  private String name;
  private String description;
  private int numRounds;
  private WorldCells initialState;
  private World world;
}