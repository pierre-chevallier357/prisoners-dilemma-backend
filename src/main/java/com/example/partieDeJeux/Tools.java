package com.example.partieDeJeux;

import java.util.Random;

public class Tools {
    public static Integer randomNum(){
        Random r = new Random();
		int low = 1000;
		int high = 9999;
		return  r.nextInt(high-low) + low;
    }
}
