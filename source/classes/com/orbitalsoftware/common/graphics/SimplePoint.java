/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 */

package com.orbitalsoftware.common.graphics;


/**
 *
 * @author   Alex Polson
 * @version  $Revision$
 * @since    1.0
 */

public class SimplePoint
{
  // Constructors
  //
  public SimplePoint()
  {
    setX( DEFAULT_X );
    setY( DEFAULT_Y );
  }

  public SimplePoint( int x, int y )
  {
    setX( x );
    setY( y );
  }

  public SimplePoint( SimplePoint point )
  {
    setX( point.getX() );
    setY( point.getY() );
  }

  // Methods
  //

  public void setX( int x )
  {
    this.x = x;
  }

  public int getX()
  {
    return x;
  }

  public void setY( int y )
  {
    this.y = y;
  }

  public int getY()
  {
    return y;
  }

  // Statics
  //
  public static int DEFAULT_X = 0;
  public static int DEFAULT_Y = 0;

  // Private Attributes
  //
  private int x;
  private int y;
}