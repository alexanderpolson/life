/*
 * Copyright (c) 2004 Orbital Software
 */
package com.orbitalsoftware.life.gui.panel;


import java.util.Iterator;

import com.orbitalsoftware.life.common.World;

import com.orbitalsoftware.life.common.law.Law;

import com.orbitalsoftware.life.common.law.command.LawCommand;
import com.orbitalsoftware.life.common.law.condition.CellsState;
import com.orbitalsoftware.life.common.law.condition.LawCondition;
import com.orbitalsoftware.life.common.law.condition.NeighborState;
import com.orbitalsoftware.life.common.law.condition.NumberOfNeighbors;

import com.orbitalsoftware.life.gui.dialog.LawConditionDialog;

import com.orbitalsoftware.life.gui.panel.NumberOfNeighborsPanel;

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
public class LawConditionManagementPanel
  extends Composite
  implements SelectionListener
{
  public LawConditionManagementPanel( Composite parent, int style, Law law )
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
    title.setText( "Conditions:" );
    title.setLayoutData( layoutData );
    
    // CellState List
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.verticalAlignment = GridData.FILL;
    conditionList = new List( this, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL );
    conditionList.setLayoutData( layoutData );
    
    // Create Button group
    buttonPanel = new LawConditionManagementButtonPanel( this, 0 );
    buttonPanel.addSelectionListener( this );
  }
  
  public void refreshList()
  {
    conditionList.removeAll();
    
    Iterator it = law.conditionIterator();

    LawCondition condition = null;
    
    while( it.hasNext() )
    {
      condition = (LawCondition) it.next();
      conditionList.add( condition.getName() );
    }
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  public void widgetSelected( SelectionEvent e )
  {
    if( e.getSource() == buttonPanel.getAddButton() )
    {
      int index = buttonPanel.getConditionCombo().getSelectionIndex(); 
      
      conditionDialog = new LawConditionDialog( getShell() );
      conditionDialog.setText( "New Condition" );
      
      switch( index )
      {
        case( 0 ):
          if( ((World)law.getOwner()).getNumCellStates() > 0 )
          {
            // Show the dialog.
            conditionDialog.init(
                new CellsStatePanel( conditionDialog.getShell(), SWT.NONE,
                    new CellsState( law ) ) );
          }
          else
          {
            MessageBox message =
              new MessageBox( getShell(), SWT.ICON_ERROR | SWT.OK );
            message.setText( "No CellStates defined." );
            message.setMessage( "You must first define at least one CellState." );
            message.open();
            return;
          }
          break;
        case( 1 ):
          if( ((World)law.getOwner()).getNumCellStates() > 0 )
          {
            // Show the dialog.
            conditionDialog.init(
                new NeighborStatePanel( conditionDialog.getShell(), SWT.NONE,
                    new NeighborState( law ) ) );
          }
          else
          {
            MessageBox message =
              new MessageBox( getShell(), SWT.ICON_ERROR | SWT.OK );
            message.setText( "No CellStates defined." );
            message.setMessage( "You must first define at least one CellState." );
            message.open();
            return;
          }
          break;
        case( 2 ):
          conditionDialog.init(
              new NumberOfNeighborsPanel( conditionDialog.getShell(), SWT.NONE,
                  new NumberOfNeighbors( law ) ) );
          break;
      }

      conditionDialog.getOkCancel().getOk().addSelectionListener( this );
      conditionDialog.open();
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
      int index = conditionList.getSelectionIndex();
      
      if( index != -1 )
      {
        LawCondition condition = law.getCondition( index );
        MessageBox mb =
          new MessageBox( getShell(), SWT.YES | SWT.NO );
        mb.setText( "Confirm Deletion" );
        mb.setMessage( "Are you sure you want to delete the " + condition.getName() +
            " condition?" );
        
        if( mb.open() == SWT.YES )
        {
          // Delete the state.
          law.removeCondition( index );
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
    else if( e.getSource() == conditionDialog.getOkCancel().getOk() )
    {
      refreshList();
    }
  }
  
  // Private Atributes
  //
  private Law law;
  private Label title;
  private List conditionList;
  private LawConditionDialog conditionDialog;
  private LawConditionManagementButtonPanel buttonPanel;
  
  private class LawConditionManagementButtonPanel
    extends Composite
  {
    public LawConditionManagementButtonPanel( Composite parent, int style )
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
          new String[] { "Cell's State", "Neighbor State", "Number of Neighbors" } );
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
