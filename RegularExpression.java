package com.ebay.app.raptor.controller;

/*
 * RegularExpression.java
 *
 * Created on March 29, 2010, 1:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Saurabh
 */

public class RegularExpression {
    
    /** Creates a new instance of RegularExpression */
    public RegularExpression() {
    }
    
    public String getPolygonPoints(String response) {
        String polygonPointRE="\"polygonpoints\":.*],\"lat\"";	

        Pattern p = Pattern.compile(polygonPointRE,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(response);
        if (m.find())
        {
            String match = m.group(0);
            return match;
        }
        return null;
    }   
}
