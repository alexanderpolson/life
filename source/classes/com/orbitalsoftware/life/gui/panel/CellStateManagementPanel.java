/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import com.orbitalsoftware.life.common.CellState;
import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.gui.CellStateWidget;

import com.orbitalsoftware.life.gui.dialog.CellStateDialog;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.graphics.RGB;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;

import java.util.Iterator;

/**
 * Used for modifying a list of <code>CellState</code> objects.
 *
 * @author  $Author$
 * @version $Revision$
 */
public class CellStateManagementPanel
  extends Composite
  implements SelectionListener
{
  public CellStateManagementPanel( Composite parent, int style, World world )
  {
    super( parent, style );
    init( world );
  }
  
  private void init( World world )
  {
    this.world = world;
    GridData layoutData = null;
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    
    setLayout( layout );
    
    // Title
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.horizontalSpan = 2;
    title = new Label( this, SWT.NONE );
    title.setText( "Cell States:" );
    title.setLayoutData( layoutData );
    
    // CellState List
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.verticalAlignment = GridData.FILL;
    stateList = new List( this, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL );
    stateList.setLayoutData( layoutData );
    refreshList();
    
    // Create Button group
    buttonPanel = new CellStateManagementButtonPanel( this, SWT.NONE );
    
    // Add Listeners
    buttonPanel.addSelectionListener( this );
  }
  
  private void refreshList()
  {
    stateList.removeAll();
    
    Iterator it = world.cellStateIterator();

    CellStateWidget state = null;
    
    while( it.hasNext() )
    {
      state = (CellStateWidget) it.next();
      
      if( state == ( (World)state.getOwner() ).getDefaultCellState() )
      {
        stateList.add( state.getName() + " *" );
      }
      else
      {
        stateList.add( state.getName() );
      }
    }
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  public void widgetSelected( SelectionEvent e )
  {
    if( e.getSource() == buttonPanel.getAddButton() )
    {
      dialog =
        new CellStateDialog( getShell(),
            new CellStateWidget( world, "", "", DEFAULT_CELLSTATE_COLOR ) );
      dialog.setText( "Add a Cell State" );
      dialog.getOkCancel().getOk().addSelectionListener( this );
      dialog.open();
    }
    else if( e.getSource() == buttonPanel.getEditButton() )
    {
      int index = stateList.getSelectionIndex();
      
      if( index != -1 )
      {
        dialog = new CellStateDialog( getShell(),
            (CellStateWidget)world.getCellState( index ) );
        dialog.setText( "Edit Cell State" );
        dialog.getOkCancel().getOk().addSelectionListener( this );
        dialog.open();
      }
    }
    else if( e.getSource() == buttonPanel.getRemoveButton() )
    {
      int index = stateList.getSelectionIndex();
      
      if( index != -1 )
      {
        CellState state = world.getCellState( index );
        MessageBox mb =
          new MessageBox( getShell(), SWT.YES | SWT.NO );
        mb.setText( "Confirm Deletion" );
        mb.setMessage( "Are you sure you want to delete the " + state.getName() +
            " state?" );
        
        if( mb.open() == SWT.YES )
        {
          // Delete the state.
          world.removeCellState( index );
          refreshList();
        }
      }
    }
    else if( e.getSource() == buttonPanel.getDefaultButton() )
    {
      int index = stateList.getSelectionIndex();
      
      if( index != -1 )
      {
        world.setDefaultCellState( world.getCellState( index ) );
        refreshList();
        stateList.select( index );
      }
    }
    else if( e.getSource() == dialog.getOkCancel().getOk() )
    {
      int index = stateList.getSelectionIndex();
      
      refreshList();
      
      if( index != -1 )
      {
        stateList.select( index );
      }
    }
  }
  
  // Statics
  //
  
  public static final RGB DEFAULT_CELLSTATE_COLOR = new RGB( 255, 255, 255 );
  
  // Private Atributes
  //
  private World world;
  private Label title;
  private List stateList;
  private CellStateDialog dialog;
  private CellStateManagementButtonPanel buttonPanel;
  
  private class CellStateManagementButtonPanel
    extends Composite
  {
    public CellStateManagementButtonPanel( Composite parent, int style )
    {
      super( parent, style );
      init();
    }
    
    private void init()
    {
      RowLayout groupLayout = new RowLayout();
      groupLayout.type = SWT.VERTICAL;
      groupLayout.fill = true;
      groupLayout.pack = false;
      setLayout( groupLayout );
      addButton = new Button( this, SWT.PUSH );
      addButton.setText( "Add" );
      editButton = new Button( this, SWT.PUSH );
      editButton.setText( "Edit" );
      removeButton = new Button( this, SWT.PUSH );
      removeButton.setText( "Remove" );
      defaultButton = new Button( this, SWT.PUSH);
      defaultButton.setText( "Make Default" );
    }
    
    public Button getAddButton()
    {
      return addButton;
    }
    
    public Button getEditButton()
    {
      return editButton;
    }
    
    public Button getRemoveButton()
    {
      return removeButton;
    }
    
    public Button getDefaultButton()
    {
      return defaultButton;
    }
    
    public void addSelectionListener( SelectionListener listener )
    {
      addButton.addSelectionListener( listener );
      editButton.addSelectionListener( listener );
      removeButton.addSelectionListener( listener );
      defaultButton.addSelectionListener( listener );
    }
    
    // Private Attributes
    //
    private Button addButton;
    private Button editButton;
    private Button removeButton;
    private Button defaultButton;
  }
}
