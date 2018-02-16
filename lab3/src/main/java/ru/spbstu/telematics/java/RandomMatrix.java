package main.java;

import java.util.LinkedList;

/**
 * Created by ED on 19.12.2017.
 */
public class RandomMatrix {


    public static int[][] perform(int[][] matr, int min, int max) {
        LinkedList<Thread> threads = new LinkedList<>();
        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[i].length; j++) {
                Integer I = i;
                Integer J = j;
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        matr[I][J] = (int)(Math.random()*(max-min+1)+min);
                    }
                });
                t.start();
                threads.add(t);
            }
        }
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return matr;
    }

}