package com.example.frankkoutoulas.splashscreen;

import java.util.ArrayList;

/**
 * Created by miles on 11/11/15.
 */
public class SharedResultList<E> extends ArrayList<E>{

    private static SharedResultList instance = new SharedResultList();

    private SharedResultList(){

    }

    public static SharedResultList getInstance(){
        return instance;
    }
}
