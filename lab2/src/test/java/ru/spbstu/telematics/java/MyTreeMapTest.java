package test.java.ru.spbstu.telematics.java;

import org.junit.Test;
import main.java.ru.spbstu.telematics.java.MyTreeMap;

import java.util.Iterator;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class MyTreeMapTest {

    @Test
    /*Проверка на правильность добавления*/
    public void ArListTest()
    {
        MyTreeMap<Character,Integer> myList = new MyTreeMap<Character,Integer>();
        TreeMap<Character,Integer> javaList = new TreeMap<Character,Integer>();

        myList.put('1',1);
        myList.put('2',2);
        myList.put('3',3);
        myList.put('4',4);

        javaList.put('1',1);
        javaList.put('2',2);
        javaList.put('3',3);
        javaList.put('4',4);

        assertEquals("Add to map failed", javaList, myList);

        myList.put('2',2);
        javaList.put('2',2);

        assertEquals("Replace key failed", javaList, myList);

        myList.remove('3');
        javaList.remove('3');

        assertEquals("Remove element failed", javaList, myList);

        assertEquals("\"get(4)\" failed", javaList.get('1'), myList.get('1'));
        assertEquals("\"contains(1)\" failed", javaList.containsValue(1), myList.containsValue(1));
        assertEquals("\"contains(7)\" failed", javaList.containsValue(7), myList.containsValue(7));
    }


}