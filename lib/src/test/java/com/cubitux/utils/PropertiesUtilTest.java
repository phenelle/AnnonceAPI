package com.cubitux.utils;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by pierre on 2016-05-19.
 */
public class PropertiesUtilTest extends TestCase {

    @Test
    public void testGetProperties() throws Exception {
        String value = PropertiesUtil.getValue("annoncePath");
        assertEquals("Path is incorrect", "/tmp", value);
        System.out.println("Value: " + value);
    }
}
