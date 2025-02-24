package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    private static boolean isPowerOfTwo(int x) {
        return ((x & (x - 1)) == 0);
    }

    public static void timeAListConstruction() {
        AList<Integer> testingLst = new AList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opcounts = new AList<>();
        int size, counter;
        int maxCheckingSize = 128000;
        Stopwatch sw = new Stopwatch();
        counter = 0;

        while (testingLst.size() <= maxCheckingSize) {
            testingLst.addLast(1);
            counter += 1;
            size = testingLst.size();

            if (size % 1000 == 0 && isPowerOfTwo(size / 1000)) {
                times.addLast(sw.elapsedTime());
                Ns.addLast(size);
                opcounts.addLast(counter);
            }
        }
        printTimingTable(Ns, times, opcounts);
    }
}
