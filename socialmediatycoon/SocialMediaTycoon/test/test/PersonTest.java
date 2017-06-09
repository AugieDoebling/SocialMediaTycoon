/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Test;
import smt.Person;

/**
 *
 * @author augiedoebling
 */
public class PersonTest extends TestCase
{
    @Test
    public void testAddress()
    {
        Person person = new Person();
        person.setPostalAddress("1 Grand Avenue, San Luis Obispo, CA 93410");
        assertEquals("1 Grand Avenue, San Luis Obispo, CA 93410", 
                person.getPostalAddress());
    }
    
    @Test
    public void testEmail()
    {
        Person person = new Person();
        person.setEmail("lknope@calpoly.edu");
        assertNotEquals("bwyatt@calpoly.edu", person.getEmail());
        assertEquals("lknope@calpoly.edu", person.getEmail());
    }
}
