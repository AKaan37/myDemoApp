package com.mycompany.app;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testFound() {
      ArrayList<Integer> array = new ArrayList<>(Arrays.asList(0, 0, 0, 0,0));
      String res=new App().search("hello",array, array,0);
      boolean flag=false;
      if(res=="hello")flag=true;
      assertTrue(flag);
    }

    public void testNotFound() {
      ArrayList<Integer> array = new ArrayList<>(Arrays.asList(0, 0, 0, 0,0));
      String res=new App().search("hello",array, array,2);
      boolean flag=false;
      if(res=="llohe")flag=true;
      assertTrue(flag);
    }

    public void testEmptyArray() {
      ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 1, 1, 1,1));
      String res=new App().search("hello",array, array,2);
      boolean flag=false;
      if(res=="mmpif")flag=true;
      assertTrue(flag);
    }

    public void testNull() {
      ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 1, 1, 1,1));
      String res=new App().search("",array, array,2);
      boolean flag=false;
      if(res=="")flag=true;
      assertTrue(flag);
    }

    public void testApp()
    {
        assertTrue( true );
    }
}
