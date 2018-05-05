/*
 * Copyright (C) 2004 Orbital Software
 * $Id: $
 */

package com.orbitalsoftware.life.common;


import junit.framework.*;

/**
 * <code>CellStateTest</code>
 *
 * @author   Alex Polson
 * @version  $Revision$
 */

public class CellStateTest
  extends TestCase
{
  protected void setUp()
  {
    cellState0 = new CellState( CELLSTATE_NAME );
    cellState1 = new CellState( CELLSTATE_NAME, CELLSTATE_DESCRIPTION );
  }

  // BEGIN Tests
  //

  public void testEquals()
  {
    Assert.assertTrue( cellState0.equals( cellState0 ) );
    Assert.assertNotSame( cellState0, cellState1 );
  }

  //
  // END Tests

  protected void tearDown()
  {
    
  }

  // Statics
  //
  private static final String CELLSTATE_NAME = "Cell State Name";
  private static final String CELLSTATE_DESCRIPTION = "Cell State Description";

  // Private Attributes
  //
  private CellState cellState0;
  private CellState cellState1;
}
