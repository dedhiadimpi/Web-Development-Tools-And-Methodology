/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimpi;

import java.util.ListResourceBundle;

/**
 *
 * @author dedhi
 */
public class Bundle extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }
    
    public static final Object[][] contents = {{"country.India","India"},{"country.China","China"},{"country.USA","USA"}};
    
}
