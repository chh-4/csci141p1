package puzzles.dice;

import puzzles.common.solver.Configuration;
import java.util.*;

/**
 * author: CHARLES HENRY HUTSON IV [10 MAR 2024]
 */

public class DiceConfig implements Configuration {
    private final String start;
    private static String end;
    private final ArrayList<String> diceList;
    private final Map<Integer, Map<String, ArrayList<String>>> adMap;
    public DiceConfig(String start, String end, ArrayList<String> diceList, Map<Integer, Map<String, ArrayList<String>>> adMap) {
        this.start = start;
        DiceConfig.end = end;
        this.diceList = diceList;
        this.adMap = adMap;
    }

    public String getStart() {
        return this.start;
    }
    @Override
    public boolean isSolution() {
        return (start.equals(end));
    }

    @Override
    public Collection<Configuration> getNeighbors() {
        Collection<Configuration> neighbors = new ArrayList<>();
        char[] startArray = start.toCharArray();
        for (int i = 0; i < start.length(); i++) {
            for (String neighboringFace : adMap.get(Integer.parseInt(diceList.get(i))).get(String.valueOf(startArray[i]))) {
                StringBuilder neighbor = new StringBuilder(start);
                char[] neighboringFaceArray = neighboringFace.toCharArray();
                neighbor.setCharAt(i, neighboringFaceArray[0]);
                DiceConfig maybeFace = new DiceConfig(neighbor.toString(), end, diceList, adMap);
                if (!neighbors.contains(maybeFace)) {
                    neighbors.add(maybeFace);
                }
            }
        }
        return neighbors;
    }


    @Override
    public boolean equals(Object other) {
        if (other instanceof DiceConfig diceConfig) {
            return this.diceList == diceConfig.diceList && this.adMap == diceConfig.adMap && Objects.equals(this.start, diceConfig.start);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start);
    }

    @Override
    public String toString() {
        return start;
    }
}
