package com.example.gustavoar.sgp.util;

import java.util.HashMap;

/**
 * Created by nalmir on 04/03/2017.
 */
public class HMAux extends HashMap<String, String> {

    public static final String ID = "id";
    public static final String TEXTO_01 = "texto_01";
    public static final String TEXTO_02 = "texto_02";
    public static final String TEXTO_03 = "texto_03";
    public static final String TEXTO_04 = "texto_04";
    public static final String TEXTO_05 = "texto_05";

    @Override
    public String toString() {

        return get(TEXTO_01);
    }
}
