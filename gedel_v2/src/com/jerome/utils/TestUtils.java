/*
 * Created on 8 oct. 2004 for gedel_v2
 *
 * 
 */
package com.jerome.utils;

/**
 * TestUtils
 * @author jerome forestier @ sqli
 */
public class TestUtils
{

    public static void main(String[] args)
    {
        MapList ml = new MapList();
        //ml.add("hello");
        //ml.add("world");
        
        ml.add("1", "bonjour");
        ml.add("2", "le");
        ml.add("3", "monde");
        ml.add("2", "du");
        ml.add("1", "dehors");
        
        System.out.println(ml);
    }
}
