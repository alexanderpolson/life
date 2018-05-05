/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import java.util.Iterator;

import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.common.law.Law;

import com.orbitalsoftware.life.gui.CellStateWidget;
import com.orbitalsoftware.life.gui.dialog.LawDialog;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;

/**
 * Used for modifying a list of <code>CellState</code> objects.
 *
 * @author  $Author$
 * @version $Revision$
 */
public class LawManagementPanel
  extends Composite
  implements SelectionListener
{
  public LawManagementPanel( Composite parent, int style, World world )
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
    
    // Title Label
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.horizontalSpan = 2;
    title = new Label( this, 0 );
    title.setText( "Laws:" );
    title.setLayoutData( layoutData );
    
    // Law List
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.verticalAlignment = GridData.FILL;
    lawList = new List( this, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL );
    lawList.setLayoutData( layoutData );
    refreshList();
    
    // Create Button group
    buttonPanel = new LawManagementButtonPanel( this, 0 );
    buttonPanel.addSelectionListener( this );
  }
  
  public void refreshList()
  {
    lawList.removeAll();
    
    Iterator it = world.lawIterator();

    Law law = null;
    
    while( it.hasNext() )
    {
      law = (Law) it.next();
      lawList.add( law.getName() );
    }
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  public void widgetSelected( SelectionEvent e )
  {
    if( e.getSource() == buttonPanel.getAddButton() )
    {
      dialog = new LawDialog( getShell(), new Law( world, "", "" ) );
      dialog.setText( "Create new Law..." );
      dialog.getOkCancel().getOk().addSelectionListener( this );
      dialog.open();
    }
    else if( e.getSource() == buttonPanel.getEditButton() )
    {
      int index = lawList.getSelectionIndex();
      
      if( index != -1 )
      {
        dialog = new LawDialog( getShell(), world.getLaw( index ) );
        dialog.setText( "Edit Law..." );
        dialog.getOkCancel().getOk().addSelectionListener( this );
        dialog.open();
      }
    }
    else if( e.getSource() == buttonPanel.getRemoveButton() )
    {
      int index = lawList.getSelectionIndex();
      
      if( index != -1 )
      {
        Law law = world.getLaw( index );
        MessageBox mb =
          new MessageBox( getShell(), SWT.YES | SWT.NO );
        mb.setText( "Confirm Deletion" );
        mb.setMessage( "Are you sure you want to delete the " + law.getName() +
            " law?" );
        
        if( mb.open() == SWT.YES )
        {
          // Delete the Law
          world.removeLaw( index );
          refreshList();
        }
      }
    }
    else if( e.getSource() == buttonPanel.getUpButton() )
    {
      int index = lawList.getSelectionIndex();
      int newIndex = index - 1;
      Law temp1 = null;
      Law temp2 = null;
      
      if( index > 0 && newIndex > 0 )
      {
        temp1 = world.getLaw( index );
        temp2 = world.getLaw( newIndex );
        world.removeLaw( temp1 );
        world.removeLaw( temp2 );
        world.addLaw( newIndex, temp1 );
        world.addLaw( index, temp2 );
        refreshList();
      }
    }
    else if( e.getSource() == buttonPanel.getDownButton() )
    {
      int index = lawList.getSelectionIndex();
      int newIndex = index + 1;
      Law temp1 = null;
      Law temp2 = null;
      
      if( index < world.getNumLaws() && newIndex < world.getNumLaws() )
      {
        temp1 = world.getLaw( index );
        temp2 = world.getLaw( newIndex );
        world.removeLaw( temp1 );
        world.removeLaw( temp2 );
        world.addLaw( index, temp2 );
        world.addLaw( newIndex, temp1 );
        refreshList();
      }
    }
    else if( e.getSource() == dialog.getOkCancel().getOk() )
    {
      refreshList();
    }
  }
  
  // Private Atributes
  //
  private LawDialog dialog;
  private World world;
  private Label title;
  private List lawList;
  private LawManagementButtonPanel buttonPanel;
  
  private class LawManagementButtonPanel
    extends Composite
  {
    public LawManagementButtonPanel( Composite parent, int style )
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
      upButton = new Button( this, SWT.PUSH);
      upButton.setText( "Move Up" );
      downButton = new Button( this, SWT.PUSH );
      downButton.setText( "Move Down" );
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
    
    public Button getUpButton()
    {
      return upButton;
    }
    
    public Button getDownButton()
    {
      return downButton;
    }
    
    public void addSelectionListener( SelectionListener listener )
    {
      addButton.addSelectionListener( listener );
      editButton.addSelectionListener( listener );
      removeButton.addSelectionListener( listener );
      upButton.addSelectionListener( listener );
      downButton.addSelectionListener( listener );
    }
    
    // Private Attributes
    //
    private Button addButton;
    private Button editButton;
    private Button removeButton;
    private Button upButton;
    private Button downButton;
  }
}
