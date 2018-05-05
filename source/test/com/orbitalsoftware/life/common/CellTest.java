/*
 * Copyright (C) 2004 Orbital Software
 * $Id: $
 */

package com.orbitalsoftware.life.common;


import junit.framework.*;

/**
 * <code>CellTest</code>
 *
 * @author   Alex Polson
 * @version  $Revision$
 */

public class CellTest
  extends TestCase
{
  protected void setUp()
  {
    cell0 = new Cell( CELL0_NAME, CELL0_STATE );
    cell1 = new Cell( CELL1_NAME, CELL1_DESCRIPTION, CELL1_STATE );
    cell2 = new Cell( cell0 );
  }

  // BEGIN Tests
  //

  public void testSetState()
  {
    cell0.setState( CELL1_STATE );
    Assert.assertSame( CELL1_STATE, cell0.getState() );

    cell1.setState( CELL0_STATE );
    Assert.assertSame( CELL0_STATE, cell1.getState() );

    cell2.setState( CELL1_STATE );
    Assert.assertSame( CELL1_STATE, cell2.getState() );
  }

  public void testGetState()
  {
    Assert.assertSame( CELL0_STATE, cell0.getState() );
    Assert.assertSame( CELL1_STATE, cell1.getState() );
    Assert.assertSame( CELL0_STATE, cell2.getState() );
  }

  public void testEquals()
  {
    Assert.assertFalse( cell0.equals( new CellState( "", "" ) ) );
    Assert.assertEquals( cell0, cell2 );
    Assert.assertFalse( cell0.equals( cell1 ) );
    cell2.setState( new CellState( "", "" ) );
    Assert.assertFalse( cell0.equals( cell2 ) );
  }

  //
  // END Tests

  protected void tearDown()
  {
    
  }

  // Statics
  //
  private static final String CELL0_NAME = "Cell 0 Name";
  private static final String CELL0_STATE_NAME = "Cell 0 State Name";
  private static final String CELL0_STATE_DESCRIPTION =
    "Cell 0 State Description";
  private static final CellState CELL0_STATE =
    new CellState( CELL0_STATE_NAME, CELL0_STATE_DESCRIPTION );
  private static final String CELL1_NAME = "Cell 1 Name";
  private static final String CELL1_DESCRIPTION = "Cell 1 Description";
  private static final String CELL1_STATE_NAME = "Cell 1 State Name";
  private static final String CELL1_STATE_DESCRIPTION =
    "Cell 1 State Description";
  private static final CellState CELL1_STATE =
    new CellState( CELL1_STATE_NAME, CELL1_STATE_DESCRIPTION );

  // Private Attributes
  //
  private Cell cell0;
  private Cell cell1;
  private Cell cell2;
}
