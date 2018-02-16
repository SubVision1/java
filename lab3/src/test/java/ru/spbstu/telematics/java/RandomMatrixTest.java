package test.java;

import main.java.RandomMatrix;

import static org.junit.Assert.*;

/**
 * Created by ED on 20.01.2018.
 */
public class RandomMatrixTest {

    private static int[][] MY_RESULT;


    boolean check(int[][]m,int min, int max)//проверка что все числа не одинаковые и входят в заданный промежуток
    {
        int same=m[0][0];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if(m[i][j]!=same)return true;
                if(m[i][j]>max||m[i][j]<min)return false;
            }
        }
        return false;
    }

    @org.junit.Test
    public void perform() throws Exception {
        int[][]matr=new int[8][5];
        MY_RESULT = RandomMatrix.perform(matr,5,20);
        assertTrue(check(MY_RESULT,5,20));
    }

}
