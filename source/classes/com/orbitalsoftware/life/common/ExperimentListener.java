/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common;


/**
 * For objects that want to be informed when experiment events occur. The
 * <code>Experiment</code> reference passed to the methods below are final
 * so the state of the current <code>Experiment</code> isn't disturbed.
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */
public interface ExperimentListener
{
  // Methods
  //

  /**
   * Called whenever a round of an <code>Experiment</code> object starts.
   *
   * @param experiment Gets a reference to the state of the current
   *   <code>Experiment</code>.
   */
  public void roundStarted( final Experiment experiment );

  /**
   * Called whenever a round of an <code>Experiment</code> object is finished.
   *
   * @param experiment Gets a reference to the state of the current
   *   <code>Experiment</code>.
   */
  public void roundEnded( final Experiment experiment );
}
