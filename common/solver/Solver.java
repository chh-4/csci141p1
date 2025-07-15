package puzzles.common.solver;

import java.util.*;

/**
 * author: CHARLES HENRY HUTSON IV [10 MAR 2024]
 */

public class Solver {
    private static int totalConfigs = 1; // Accounting for initial config

    public static void solver(Configuration startConfig) {
        Queue<Configuration> queue = new LinkedList<>();
        Map<Configuration, Configuration> predecessorMap = new HashMap<>();
        Set<Configuration> uniqueConfigs = new HashSet<>();
        queue.add(startConfig);
        predecessorMap.put(startConfig, null);
        uniqueConfigs.add(startConfig);
        while (!queue.isEmpty()) {
            Configuration config = queue.remove();
            if (config.isSolution()) {
                List<Configuration> path = new ArrayList<>();
                while (config != null) {
                    path.addFirst(config);
                    config = predecessorMap.get(config);
                }
                System.out.println("Total configs: " + totalConfigs);
                System.out.println("Unique configs: " + uniqueConfigs.size());
                int i = 0;
                for (Configuration pathConfig : path) {
                    System.out.println("Step " + i + ": " + pathConfig.toString());
                    i++;
                }
                return;
            }
            for (Configuration neighbor : config.getNeighbors()) {
                totalConfigs++;
                if (!predecessorMap.containsKey(neighbor)) {
                    queue.add(neighbor);
                    predecessorMap.put(neighbor, config);
                    uniqueConfigs.add(neighbor);
                }
            }
        }
        System.out.println("Total configs: " + totalConfigs);
        System.out.println("Unique configs: " + uniqueConfigs.size());
        System.out.println("No solution");
    }
}
