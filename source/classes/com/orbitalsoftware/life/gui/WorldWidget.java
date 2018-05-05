/*
 * Copyright (c) 2004 Orbital Software
 * $Id: $
 */
package com.orbitalsoftware.life.gui;


import com.orbitalsoftware.common.graphics.SimplePoint;

import com.orbitalsoftware.life.common.Cell;
import com.orbitalsoftware.life.common.Experiment;
import com.orbitalsoftware.life.common.ExperimentListener;
import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.gui.CellStateWidget;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import java.io.Serializable;

/**
 * Graphical version of a <code>World</code>. Draws the <code>World</code>'s
 * state to a <code>Canvas</code> and reacts to mouse clicks.
 * 
 * @author  $Author$
 * @version $Revision$
 */
public class WorldWidget
  extends Canvas
  implements PaintListener, MouseListener, ExperimentListener, Serializable,
    Runnable
{
  // Constructors
  //
  
  public WorldWidget( Composite parent, int style )
  {
    this( parent, style, null );
  }
  
  public WorldWidget( Composite parent, int style, World world )
  {
    super( parent, style );
    init( world );
  }
  
  // Methods
  //
  
  private void initListeners()
  {
    addPaintListener( this );
    addMouseListener( this );
  }
  
  private void init( World world )
  {
    this.world = world;
    monitorClicks = false;
    initListeners();
  }
  
  public void setWorld( World world )
  {
    this.world = world;
  }
  
  public World getWorld()
  {
    return world;
  }
  
  public void addCellStateWidget( CellStateWidget cellState )
  {
    world.addCellState( cellState );
  }
  
  public boolean removeCellStateWidget( CellStateWidget cellState )
  {
    return world.removeCellState( cellState );
  }
  
  public void setDefaultCellStateWidget( CellStateWidget cellState )
  {
    world.setDefaultCellState( cellState );
  }
  
  public void setCellSize( Point cellSize )
  {
    this.cellSize = cellSize;
  }
  
  public int getCellWidth()
  {
    return cellSize.x;
  }
  
  public int getCellHeight()
  {
    return cellSize.y;
  }
  
  public void setShowGrid( boolean showGrid )
  {
    this.showGrid = showGrid;
  }
  
  public boolean getShowGrid()
  {
    return showGrid;
  }
  
  public void run()
  {
    redraw();
  }
  
  public void paintControl( PaintEvent e )
  {
    if( getVisible() )
    {
      Point canvasSize = getSize();
      cellSize = new Point( canvasSize.x / world.getWidth(),
          canvasSize.y / world.getHeight() );
      
      // Draw everything on an image to avoid too much flicker.
      Image image = new Image( getDisplay(), canvasSize.x, canvasSize.y );
      image.setBackground( new Color( getDisplay(), new RGB( 255, 255, 255 ) ) );
      
      drawCellStates( image );
      
      if( showGrid )
      {
        drawGrid( image );
      }
      
      // Copy the image to the canvas.
      GC gc = new GC( this );
      gc.drawImage( image, 0, 0 );
      gc.dispose();
      
      image.dispose();
      monitorClicks = true;
    }
  }
  
  private void drawCellStates( Image image )
  {
    GC gc = new GC( image );
    SimplePoint cellPoint = null;
    CellStateWidget cellState = null;
    Color tempColor = null;
    int xPos = 0;
    int yPos = 0;
    
    for( int i = 0; i < world.getWidth(); ++i )
    {
      for( int j = 0; j < world.getHeight(); ++j )
      {
        cellPoint = new SimplePoint( i, j );
        cellState = (CellStateWidget) world.getCell( cellPoint ).getState();
        tempColor = gc.getBackground();
        gc.setBackground( new Color( getDisplay(), cellState.getColor() ) );
        tempColor.dispose();
        
        xPos = i * getCellWidth();
        yPos = j * getCellHeight();
        gc.fillRectangle( xPos, yPos, xPos + getCellWidth(), yPos + getCellHeight() );
      }
    }
    
    gc.dispose();
  }
  
  private void drawGrid( Image image )
  {
    GC gc = new GC( image );
    Point canvasSize = getSize();
    int xPos = 0;
    int yPos = 0;
    
    // Vertical lines.
    for( int x = 1; x < world.getWidth(); ++x )
    {
      xPos = x * getCellWidth();
      gc.drawLine( xPos, 0, xPos, canvasSize.y );
    }
    
    // Horizontal lines.
    for( int y = 1; y < world.getHeight(); ++y )
    {
      yPos = y * getCellHeight();
      gc.drawLine( 0, yPos, canvasSize.x, yPos );
    }
    
    gc.dispose();
  }

  /**
   * Gets the default <code>CellStateWidget</code> of the <code>World</code>.
   *
   * @return the <code>CellStateWidget</code> that is the default for this
   *   <code>World</code>.
   */
  public CellStateWidget getDefaultCellStateWidget()
  {
    return (CellStateWidget) world.getDefaultCellState();
  }
  
  /**
   * Determines the <code>Cell</code> that is referenced by a particular
   * (x, y) pixel coordinate.
   *  
   * @param x the x-th pixel from the origin.
   * @param y the y-th pixel from the origin.
   * @return the <code>Cell</code> that is referenced.
   */
  private Cell getCellCoordinate( int x, int y )
  {
    SimplePoint point =
      new SimplePoint( x / getCellWidth(), y / getCellHeight() );
    return world.getCell( point );
  }
  
  // ExperimentListener implementation
  public void roundStarted( Experiment experiment )
  {
  }
  
  public void roundEnded( Experiment experiment )
  {
    this.getDisplay().syncExec( this );
  }
  
  // Mouse Listener Implementation
  
  public void mouseUp( MouseEvent e )
  {
    if( monitorClicks )
    {
      switch( e.button )
      {
        // Left click
        case( 1 ):
          Cell nextCell = getCellCoordinate( e.x, e.y );
          nextCell.setState( world.getNextCellState( nextCell.getState() ) );
          redraw();
          break;
        // Right click
        case( 2 ):
          break;
      }
    }
  }
  
  public void mouseDown( MouseEvent e )
  {}
  
  public void mouseDoubleClick( MouseEvent e )
  {}
  
  // Private Attributes
  //
  private Thread redrawThread;
  private boolean monitorClicks;
  private World world;
  private Point cellSize;
  private boolean showGrid = true;
  private RGB gridColor;
}
