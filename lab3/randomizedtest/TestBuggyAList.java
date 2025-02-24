package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> controlList = new AListNoResizing<>();
        BuggyAList<Integer> experimentList = new BuggyAList<>();

        for (int i = 0; i < 10; i++) {
            controlList.addLast(i);
            experimentList.addLast(i);
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(controlList.removeLast(), experimentList.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> controlList = new AListNoResizing<>();
        BuggyAList<Integer> experimentList = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            switch (operationNumber) {
                case 0:
                    // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    controlList.addLast(randVal);
                    experimentList.addLast(randVal);
                    break;
                case 1:
                    // size
                    int size1 = controlList.size();
                    int size2 = experimentList.size();
                    assertEquals(size1, size2);
                    break;
                case 2:
                    // getLast
                    if (controlList.size() == 0) {
                        break;
                    }
                    int value1 = controlList.getLast();
                    int value2 = experimentList.getLast();
                    assertEquals(value1, value2);
                    break;
                case 3:
                    // removeLast
                    if (controlList.size() == 0) {
                        break;
                    }
                    int removed1 = controlList.removeLast();
                    int removed2 = experimentList.removeLast();
                    assertEquals(removed1, removed2);
                    break;
            }
        }
    }
}
