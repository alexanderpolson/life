/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.common.graphics;


import junit.framework.*;

/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 */

public class SimplePointTest
  extends TestCase
{
  // Methods
  //
  protected void setUp()
  {
    point0 = new SimplePoint();
    point1 = new SimplePoint( POINT1_X, POINT1_Y );
    point2 = new SimplePoint( point1 );
  }

  // BEGIN Tests
  //

  public void testSetX()
  {
    point0.setX( 2 );
    Assert.assertEquals( point0.getX(), 2 );

    point1.setX( 7 );
    Assert.assertEquals( point1.getX(), 7 );

    point2.setX( 147 );
    Assert.assertEquals( point2.getX(), 147 );
  }

  public void testGetX()
  {
    Assert.assertEquals( point0.getX(), SimplePoint.DEFAULT_X );
    Assert.assertEquals( point1.getX(), POINT1_X );
    Assert.assertEquals( point2.getX(), point1.getX() );
  }

  public void testSetY()
  {
    point0.setY( 437 );
    Assert.assertEquals( point0.getY(), 437 );

    point1.setY( 9429 );
    Assert.assertEquals( point1.getY(), 9429 );

    point2.setY( 111 );
    Assert.assertEquals( point2.getY(), 111 );
  }

  public void testGetY()
  {
    Assert.assertEquals( point0.getY(), SimplePoint.DEFAULT_Y );
    Assert.assertEquals( point1.getY(), POINT1_Y );
    Assert.assertEquals( point2.getY(), point1.getY() );
  }
  
  //
  // END Tests

  protected void tearDown()
  {
    
  }
  
  // Statics
  //
  private static final int POINT1_X = 9;
  private static final int POINT1_Y = 27;

  // Private Attributes
  //
  private SimplePoint point0;
  private SimplePoint point1;
  private SimplePoint point2;
}