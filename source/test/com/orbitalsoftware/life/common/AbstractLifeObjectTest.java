/*
 * Copyright (C) 2004 Orbital Software
 * $Id: $
 */

package com.orbitalsoftware.life.common;


import junit.framework.*;

/**
 * <code>AbstractLifeObjectTest</code>
 *
 * @author   apolson
 * @version  $Revision$
 */

public class AbstractLifeObjectTest
  extends TestCase
{
  protected void setUp()
  {
    alo0 = new AbstractLifeObjectImpl();
    alo1 = new AbstractLifeObjectImpl( ALO1_NAME );
    alo2 = new AbstractLifeObjectImpl( ALO2_NAME, ALO2_DESCRIPTION );
  }

  // BEGIN Tests
  //

  public void testSetName()
  {
    alo0.setName( ALO0_NEWNAME );
    Assert.assertEquals( ALO0_NEWNAME, alo0.getName() );
    alo1.setName( ALO1_NEWNAME );
    Assert.assertEquals( ALO1_NEWNAME, alo1.getName() );
    alo2.setName( ALO2_NEWNAME );
    Assert.assertEquals( ALO2_NEWNAME, alo2.getName() );
  }

  public void testGetName()
  {
    Assert.assertEquals( ALO0_NAME, alo0.getName() );
    Assert.assertEquals( ALO1_NAME, alo1.getName() );
    Assert.assertEquals( ALO2_NAME, alo2.getName() );
  }

  public void testSetDescription()
  {
    alo0.setDescription( ALO0_NEWDESCRIPTION );
    Assert.assertEquals( ALO0_NEWDESCRIPTION, alo0.getDescription() );
    alo1.setDescription( ALO1_NEWDESCRIPTION );
    Assert.assertEquals( ALO1_NEWDESCRIPTION, alo1.getDescription() );
    alo2.setDescription( ALO2_NEWDESCRIPTION );
    Assert.assertEquals( ALO2_NEWDESCRIPTION, alo2.getDescription() );
  }

  public void testGetDescription()
  {
    Assert.assertEquals( "", alo0.getDescription() );
    Assert.assertEquals( "", alo1.getDescription() );
    Assert.assertEquals( ALO2_DESCRIPTION, alo2.getDescription() );
  }

  public void testEquals()
  {
    Assert.assertFalse( alo0.equals( null ) );
    AbstractLifeObject alo0Ref = alo0;
    Assert.assertTrue( alo0.equals( alo0Ref ) );
    Assert.assertFalse( alo0.equals( new CellState( "", "" ) ) );
    AbstractLifeObject aloEquals =
      new AbstractLifeObjectImpl( ALO0_NAME, ALO0_DESCRIPTION );
    Assert.assertTrue( alo0.equals( aloEquals ) );
    Assert.assertFalse( alo0.equals( alo1 ) );
  }

  //
  // END Tests

  protected void tearDown()
  {
    
  }

  
  // Statics
  //
  private static final String ALO0_NAME           = "";
  private static final String ALO0_NEWNAME        = "AnotherTestName0";
  private static final String ALO0_DESCRIPTION    = "";
  private static final String ALO0_NEWDESCRIPTION = "AnotherTestDescription0";
  private static final String ALO1_NAME           = "TestName1";
  private static final String ALO1_NEWNAME        = "AnotherTestName";
  private static final String ALO1_NEWDESCRIPTION = "Test1 New Description";
  private static final String ALO2_NAME           = "TestName2";
  private static final String ALO2_NEWNAME        = "Test2 New Name";
  private static final String ALO2_DESCRIPTION    = "TestDescription";
  private static final String ALO2_NEWDESCRIPTION = "Test2 New Description.";

  // Private Attributes
  //
  private AbstractLifeObject alo0;
  private AbstractLifeObject alo1;
  private AbstractLifeObject alo2;
}
