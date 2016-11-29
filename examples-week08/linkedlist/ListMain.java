package linkedlist;

import java.util.ArrayList;

/**
 * Tests some functionality of the class List<T>.
 *
 * @author Carsten Fuhs
 */
public class ListMain {
    public static void main(String[] args) {
        List<Integer> xs = new List<Integer>();
        System.out.println(xs);

        System.out.println("Size: " + xs.size());
        System.out.println("SizeIterative: " + xs.sizeIterative());
        System.out.println("IsEmpty: " + xs.isEmpty());

        xs.add(0, 20);
        System.out.println(xs);
        xs.add(1, 21);
        System.out.println(xs);
        xs.add(0, 22);
        System.out.println(xs);
        xs.add(2, 23);
        System.out.println(xs);
        xs.add(0, 24);
        System.out.println(xs);
        
        System.out.println("Size: " + xs.size());
        System.out.println("SizeIterative: " + xs.sizeIterative());
        System.out.println("IsEmpty: " + xs.isEmpty());

        System.out.println("23 is at: " + xs.indexOf(23));
        System.out.println("23 is at: " + xs.indexOfRecursive(23));

        System.out.println("24 is at: " + xs.indexOf(24));
        System.out.println("24 is at: " + xs.indexOfRecursive(24));

        xs.remove(0);
        System.out.println("Removed at 0: " + xs);
        xs.add(0, 25);
        System.out.println(xs);
        xs.remove(1);
        System.out.println("Removed at 1: " + xs);
        System.out.println(xs);
        System.out.println(xs.toStringReverse());

        List<Integer> ys = new List<Integer>(xs);

        System.out.println("xs: " + xs);
        System.out.println("ys: " + ys);

        xs.remove(0);
        System.out.println("Removed in xs at 0!");
        System.out.println("xs: " + xs);
        System.out.println("ys: " + ys);

        ys.remove(1);
        System.out.println("Removed in ys at 1!");
        System.out.println("xs: " + xs);
        System.out.println("ys: " + ys);
    }
}
