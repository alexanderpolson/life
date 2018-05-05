/*
 *  Copyright (c) 2004 Orbital Software
 *  $Id: $
 *  Overview:
 *    The GUI entry point for Life
 */

package com.orbitalsoftware.life.gui;


import com.orbitalsoftware.life.common.Experiment;
import com.orbitalsoftware.life.common.ExperimentNotRunningException;
import com.orbitalsoftware.life.common.ExperimentPersistenceObject;

import com.orbitalsoftware.life.gui.WorldWidget;
import com.orbitalsoftware.life.gui.dialog.AboutDialog;
import com.orbitalsoftware.life.gui.dialog.ExperimentDialog;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.layout.FillLayout;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <code>Main</code>
 *
 * @author   Alex Polson
 * @version  $Revision$
 */

public class Main
  implements SelectionListener
{
  private Main()
  {
    display = new Display();
    shell = new Shell( display, SWT.SHELL_TRIM );
    
    shell.setSize( 405, 445 );
    shell.setText( "Life Advanced" );
    initWindow();
    shell.open();
    
    while( !shell.isDisposed() )
    {
      if( !display.readAndDispatch() )
      {
        display.sleep();
      }
    }
    
    return;
  }
  
  private void initMenus()
  {
    menu = new Menu( shell, SWT.BAR );
    shell.setMenuBar( menu );
    
    MenuItem file = new MenuItem( menu, SWT.CASCADE );
    file.setText( "&File" );
    
    Menu fileMenu = new Menu( shell, SWT.DROP_DOWN );
    file.setMenu( fileMenu );
    
    final MenuItem newMenu = new MenuItem( fileMenu, SWT.PUSH );
    newMenu.setText( "&New..." );
    
    newMenuListener =
      new ExperimentDialogMenuListener( this, "Create new experiment...", true ); 
    newMenu.addListener( SWT.Selection, newMenuListener );
    
    final MenuItem openMenu = new MenuItem( fileMenu, SWT.PUSH );
    openMenu.setText( "&Open..." );
    openMenu.addListener( SWT.Selection,
        new Listener()
        {
          public void handleEvent( Event e )
          {
            MessageBox mb = null;
            FileDialog dialog = new FileDialog( shell, SWT.OPEN );
            dialog.setFilterNames( new String[]{ "Experiments (*.exp)" } );
            dialog.setFilterExtensions( new String[] { "*.exp" } );
            String filename = dialog.open();
            if( filename != null )
            {
              try
              {
                FileInputStream fis = new FileInputStream( filename );
                ObjectInputStream ois = new ObjectInputStream( fis );
                ExperimentPersistenceObject epo =
                  (ExperimentPersistenceObject)ois.readObject(); 
                experiment = epo.getExperiment(); 
                editExperimentMenu.setEnabled( true );
                experiment.addExperimentListener( editWorldWidget );
                refreshWindow();
              }
              catch( FileNotFoundException fnfe )
              {
                mb = new MessageBox( shell, SWT.ICON_ERROR | SWT.OK );
                mb.setText( "Error" );
                mb.setMessage( fnfe.getMessage() );
                mb.open();
              }
              catch( IOException ioe )
              {
                mb = new MessageBox( shell, SWT.ICON_ERROR | SWT.OK );
                mb.setText( "Error" );
                mb.setMessage( ioe.getMessage() );
                mb.open();
                ioe.printStackTrace();
              }
              catch( ClassNotFoundException cnfe )
              {
                mb = new MessageBox( shell, SWT.ICON_ERROR | SWT.OK );
                mb.setText( "Error" );
                mb.setMessage( cnfe.getMessage() );
                mb.open();
              }
            }
          }
        }
    );
    openMenu.setEnabled( true );
    
    // TODO: Implement persistence.
    
    MenuItem saveMenu = new MenuItem( fileMenu, SWT.PUSH );
    saveMenu.setText( "&Save" );
    saveMenu.setEnabled( false );
    saveMenu.addListener( SWT.Selection,
        new Listener()
        {
          public void handleEvent( Event e )
          {
            if( filename != null )
            {
              try
              {
                FileOutputStream fos = new FileOutputStream( filename );
                ObjectOutputStream oos = new ObjectOutputStream( fos );
                oos.writeObject( experiment );
              }
              catch( FileNotFoundException fnfe )
              {
              
              }
              catch( IOException ioe )
              {
                
              }
            }
          }
        }
    );
    
    MenuItem saveAsMenu = new MenuItem( fileMenu, SWT.PUSH );
    saveAsMenu.setText( "Save &As" );
    saveAsMenu.addListener( SWT.Selection,
        new Listener()
        {
          public void handleEvent( Event e )
          {
            FileDialog dialog = new FileDialog( shell, SWT.SAVE );
            dialog.setFilterNames( new String[]{ "Experiments" } );
            dialog.setFilterExtensions( new String[] { "*.exp" } );
            dialog.setText( "Save As" );
            dialog.open();
            
            filename = dialog.getFileName();
            
            if( filename != null )
            {
              try
              {
                FileOutputStream fos = new FileOutputStream( filename );
                ObjectOutputStream oos = new ObjectOutputStream( fos );
                ExperimentPersistenceObject epo =
                  new ExperimentPersistenceObject( experiment );
                oos.writeObject( epo );
              }
              catch( FileNotFoundException fnfe )
              {
              
              }
              catch( IOException ioe )
              {
                
              }
            }
          }
        }
    );
    saveAsMenu.setEnabled( true );
    
    MenuItem fileSepMenu = new MenuItem( fileMenu, SWT.SEPARATOR );
    
    final MenuItem exitMenu = new MenuItem( fileMenu, SWT.PUSH );
    exitMenu.setText( "E&xit" );
    exitMenu.addListener( SWT.Selection,
        new Listener()
        {
          public void handleEvent( Event e )
          {
            shell.dispose();
          }
        }
    );
    
    MenuItem mnuExperiment = new MenuItem( menu, SWT.CASCADE );
    mnuExperiment.setText( "&Experiment" );
    
    Menu experimentMenu = new Menu( shell, SWT.DROP_DOWN );
    mnuExperiment.setMenu( experimentMenu );
    
    editExperimentMenu = new MenuItem( experimentMenu, SWT.PUSH );
    editExperimentMenu.setText( "&Edit..." );
    editExperimentMenu.setEnabled( true );
    
    editExperimentMenuListener =
      new ExperimentDialogMenuListener( this, "Edit Experiment...", false );
    editExperimentMenu.addListener( SWT.Selection, editExperimentMenuListener );
    
    MenuItem editSepMenu = new MenuItem( experimentMenu, SWT.SEPARATOR );
    
    MenuItem startMenu = new MenuItem( experimentMenu, SWT.PUSH );
    startMenu.setText( "&Start" );
    startMenu.setEnabled( true );
    startMenu.addListener( SWT.Selection,
        new Listener()
        {
          public void handleEvent( Event e )
          {
            experiment.start();
          }
        }
    );
    
    MenuItem pauseMenu = new MenuItem( experimentMenu, SWT.PUSH );
    pauseMenu.setText( "&Pause" );
    pauseMenu.setEnabled( true );
    
    MenuItem stopMenu = new MenuItem( experimentMenu, SWT.PUSH );
    stopMenu.setText( "S&top" );
    stopMenu.setEnabled( true );
    stopMenu.addListener( SWT.Selection,
        new Listener()
        {
          public void handleEvent( Event e )
          {
            experiment.stop();
          }
        }
    );
    
    MenuItem help = new MenuItem( menu, SWT.CASCADE );
    help.setText( "&Help" );
    
    Menu helpMenu = new Menu( shell, SWT.DROP_DOWN );
    help.setMenu( helpMenu );
    
    final MenuItem aboutMenu = new MenuItem( helpMenu, SWT.PUSH );
    aboutMenu.setText( "&About" );
    aboutMenu.addListener( SWT.Selection,
        new Listener()
        {
          public void handleEvent( Event e )
          {
            AboutDialog aboutDialog = new AboutDialog( shell ); 
          }
        }
    );
  }
  
  private void initWindow()
  { 
    initMenus();
    
    shell.setLayout( new FillLayout() );
    editWorldWidget = new WorldWidget( shell, SWT.BORDER );
    editWorldWidget.setVisible( false );
  }
  
  private void refreshWindow()
  {
    if( experiment != null )
    {
      try
      {
        if( experiment.getCurrentState() != null )
        {
          editWorldWidget.setWorld( experiment.getWorld() );
          editWorldWidget.setVisible( true );
        }
      }
      catch( ExperimentNotRunningException enre )
      {
        if( experiment.getInitialState() != null )
        {
          editWorldWidget.setWorld( experiment.getWorld() );
          editWorldWidget.setVisible( true );
        }
      }
    }
  }
  
  public static final void main( String [] args )
  {
    new Main();
    
    return;
  }
  
  // SelectionListener implementation.
  
  public void widgetSelected( SelectionEvent e )
  {
    if( ( newMenuListener.getDialog() != null &&
          e.getSource() == newMenuListener.getDialog().getOkCancel().getOk() )||
        ( editExperimentMenuListener.getDialog() != null &&
          e.getSource() == editExperimentMenuListener.getDialog().getOkCancel().getOk() ) )
    {
      editExperimentMenu.setEnabled( true );
      experiment.addExperimentListener( editWorldWidget );
      refreshWindow();
    }
    else if( e.getSource() == newMenuListener.getDialog().getOkCancel().getCancel() ||
        e.getSource() == editExperimentMenuListener.getDialog().getOkCancel().getCancel() )
    {
      experiment = null;
    }
  }
  
  public void widgetDefaultSelected( SelectionEvent e )
  {}
  
  // Statics
  //
  
  private static final int CELL_WIDTH = 10;
  private static final int CELL_HEIGHT = 10;
  
  // Private Attributes
  //
  private Thread experimentThread;
  private String filename;
  private MenuItem editExperimentMenu;
  private Experiment experiment;
  private Display display;
  private Shell shell;
  private Menu menu;
  private WorldWidget editWorldWidget;
  
  private ExperimentDialogMenuListener newMenuListener;
  private ExperimentDialogMenuListener editExperimentMenuListener;
  
  private class ExperimentDialogMenuListener
    implements Listener
  {
    public ExperimentDialogMenuListener( SelectionListener listener,
        String text, boolean newExperiment )
    {
      this.listener = listener;
      this.text = text;
      this.newExperiment = newExperiment;
    }
    
    public void handleEvent( Event e )
    {
      if( newExperiment )
      {
        experiment = new Experiment( "", "" );
      }
      
      // Initialize ExperimentDialog
      dialog = new ExperimentDialog( shell, experiment );
      dialog.getOkCancel().addSelectionListener( listener );
      
      if( (!newExperiment && experiment != null) || newExperiment )
      {
        dialog.setText( text );
        dialog.open();
      }
    }
    
    public ExperimentDialog getDialog()
    {
      return dialog;
    }
    
    // Private Attributes
    //
    private SelectionListener listener;
    private boolean newExperiment;
    private String text;
    private Main main;
    private ExperimentDialog dialog;
  }
}