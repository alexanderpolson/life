/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import java.util.Iterator;

import com.orbitalsoftware.life.common.CellState;
import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.common.law.Law;

import com.orbitalsoftware.life.common.law.command.LawCommand;
import com.orbitalsoftware.life.common.law.command.ChangeCellStateCommand;

import com.orbitalsoftware.life.gui.CellStateWidget;
import com.orbitalsoftware.life.gui.dialog.LawCommandDialog;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
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
public class LawCommandManagementPanel
  extends Composite
  implements SelectionListener
{
  public LawCommandManagementPanel( Composite parent, int style, Law law )
  {
    super( parent, style );
    init( law );
  }
  
  private void init( Law law )
  {
    this.law = law;
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
    title.setText( "Commands:" );
    title.setLayoutData( layoutData );
    
    // CellState List
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.verticalAlignment = GridData.FILL;
    commandList = new List( this, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL );
    commandList.setLayoutData( layoutData );
    refreshList();
    
    // Create Button group
    buttonPanel = new LawCommandManagementButtonPanel( this, 0 );
    buttonPanel.addSelectionListener( this );
  }
  
  public void refreshList()
  {
    commandList.removeAll();
    
    Iterator it = law.commandIterator();

    LawCommand command = null;
    
    while( it.hasNext() )
    {
      command = (LawCommand) it.next();
      commandList.add( command.getName() );
    }
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  public void widgetSelected( SelectionEvent e )
  {
    if( e.getSource() == buttonPanel.getAddButton() )
    {
      int index = buttonPanel.getConditionCombo().getSelectionIndex(); 
      
      commandDialog = new LawCommandDialog( getShell() );
      commandDialog.setText( "New Command" );
      
      switch( index )
      {
        case( 0 ):
          if( ( (World)law.getOwner() ).getNumCellStates() > 0 )
          {
            commandDialog.init(
                new ChangeCellStatePanel( commandDialog.getShell(), SWT.NONE,
                    new ChangeCellStateCommand( law ) ) );
            commandDialog.getOkCancel().getOk().addSelectionListener( this );
          }
          else
          {
            MessageBox message = new MessageBox( getShell(), SWT.ICON_ERROR | SWT.OK );
            message.setText( "No CellStates defined." );
            message.setMessage( "You must first define at least one CellState." );
            message.open();
            return;
          }
          break;
      }
      
      commandDialog.open();
    }
    else if( e.getSource() == buttonPanel.getEditButton() )
    {
      MessageBox mb = new MessageBox( getShell(), SWT.OK );
      mb.setText( "Unimplemented feature." );
      mb.setMessage( "This feature has not yet been implemented." );
      mb.open();
    }
    else if( e.getSource() == buttonPanel.getRemoveButton() )
    {
      int index = commandList.getSelectionIndex();
      
      if( index != -1 )
      {
        LawCommand command = law.getCommand( index );
        MessageBox mb =
          new MessageBox( getShell(), SWT.YES | SWT.NO );
        mb.setText( "Confirm Deletion" );
        mb.setMessage( "Are you sure you want to delete the " + command.getName() +
            " command?" );
        
        if( mb.open() == SWT.YES )
        {
          // Delete the state.
          law.removeCommand( index );
          refreshList();
        }
      }
    }
    else if( e.getSource() == buttonPanel.getUpButton() )
    {
      System.out.println( "Move Up" );
    }
    else if( e.getSource() == buttonPanel.getDownButton() )
    {
      System.out.println( "Move Down" );
    }
    else if( e.getSource() == commandDialog.getOkCancel().getOk() )
    {
      refreshList();
    }
  }
  
  // Private Atributes
  //
  private Law law;
  private Label title;
  private List commandList;
  private LawCommandDialog commandDialog;
  private LawCommandManagementButtonPanel buttonPanel;
  
  private class LawCommandManagementButtonPanel
    extends Composite
  {
    public LawCommandManagementButtonPanel( Composite parent, int style )
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
      conditionCombo = new Combo( this, SWT.DROP_DOWN | SWT.READ_ONLY );
      conditionCombo.setItems( 
          new String[] { "Change Cell State" } );
      conditionCombo.select( 0 );
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
    
    public Combo getConditionCombo()
    {
      return conditionCombo;
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
    private Combo conditionCombo;
    private Button editButton;
    private Button removeButton;
    private Button upButton;
    private Button downButton;
  }
}
