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

    public static void timeAListConstruction() {
        AList<Integer> test_list = new AList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> cnts = new AList<>();
        int start = 1000;
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i <= 128000; i++) {
            test_list.addLast(i);
            if (i == start) {
                double timeInSeconds = sw.elapsedTime();
                Ns.addLast(i);
                times.addLast(timeInSeconds);
                cnts.addLast(i);
                start *= 2;
            }
        }
        printTimingTable(Ns, times, cnts);
    }
}
