package puzzles.clock;

import puzzles.common.solver.Solver;

/**
 * author: CHARLES HENRY HUTSON IV [10 MAR 2024]
 */

public class Clock {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Clock hours start end");
        }
        else {
            int hours = Integer.parseInt(args[0]);
            int start = Integer.parseInt(args[1]);
            int end = Integer.parseInt(args[2]);
            System.out.println("Hours: " + args[0] + ", Start: " + args[1] + ", End: " + args[2]);
            ClockConfig startConfig = new ClockConfig(hours, start, end);
            Solver.solver(startConfig);
        }
    }
}
