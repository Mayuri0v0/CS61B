package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    private static boolean isPowerOfTwo(int x) {
        return ((x & (x - 1)) == 0);
    }

    public static void timeGetLast() {
        SLList<Integer> testingLst = new SLList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opcounts = new AList<>();

        int getTimes = 10000, maxSize = 128000;
        while (testingLst.size() < maxSize) {
            do {
                testingLst.addLast(1);
            } while (!(testingLst.size() % 1000 == 0 && isPowerOfTwo(testingLst.size() / 1000)));

            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < getTimes; i++) {
                testingLst.getLast();
            }
            times.addLast(sw.elapsedTime());
            Ns.addLast(testingLst.size());
            opcounts.addLast(getTimes);
        }

        printTimingTable(Ns, times, opcounts);
    }

}
