/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.life.common.law;

import junit.framework.*;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class LawTest
  extends TestCase
{
  protected void setUp()
  {
    law0 = new Law( LAW0_NAME );
    law1 = new Law( LAW1_NAME, LAW1_DESCRIPTION );
  }

  // BEGIN Tests
  //

  public void testAddCondition()
  {
    
  }

  public void testRemoveCondition()
  {
    Assert.fail( "This test needs to be written." );
  }

  public void testClearConditions()
  {
    Assert.fail( "This test needs to be written." );
  }

  public void testGetNumConditions()
  {
    Assert.fail( "This test needs to be written." );
  }

  public void testAddCommand()
  {
    Assert.fail( "This test needs to be written." );
  }

  public void testRemoveCommand()
  {
    Assert.fail( "This test needs to be written." );
  }

  public void testClearCommands()
  {
    Assert.fail( "This test needs to be written." );
  }

  public void testGetNumCommands()
  {
    Assert.fail( "This test needs to be written." );
  }

  public void testProcessCell()
  {
    Assert.fail( "This test needs to be written." );
  }

  //
  // END Tests

  protected void tearDown()
  {
    
  }

  // Statics
  //
  private static final String LAW0_NAME = "Law 0 Name";
  private static final String LAW1_NAME = "Law 1 Name";
  private static final String LAW1_DESCRIPTION = "Law 1 Description";

  // Private Attributes
  //
  private Law law0;
  private Law law1;
}