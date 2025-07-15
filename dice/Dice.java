package puzzles.dice;

import puzzles.common.solver.Solver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author: CHARLES HENRY HUTSON IV [10 MAR 2024]
 */

public class Dice {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Dice start end die1 die2...");
        } else {
            String start = args[0];
            String end = args[1];
            Map<Integer, Map<String, ArrayList<String>>> adMap = new LinkedHashMap<>();
            ArrayList<String> diceList = new ArrayList<>();
            for (int i = 2; i < args.length; i++) {
                diceList.add(String.valueOf(args[i]));
            }
            int counter = 0;
            for (String dice : diceList) {
                String diceFilename = "die-" + dice + ".txt";
                System.out.println("Die #" + (counter) + ": File: " + diceFilename + ", Faces: " + dice);
                counter++;
                try {
                    BufferedReader br = new BufferedReader(new FileReader(diceFilename));
                    String line;
                    br.readLine();
                    br.readLine();
                    Map<String, ArrayList<String>> mapPerDie = new LinkedHashMap<>();
                    while ((line = br.readLine()) != null) {
                        String[] fields = line.split("\\s+");
                        ArrayList<String> neighbors = new ArrayList<>(Arrays.asList(fields).subList(1, fields.length));
                        mapPerDie.put(fields[0], neighbors);
                        System.out.println("\t" + fields[0] + "=" + neighbors);
                    }
                    adMap.put(Integer.parseInt(dice), mapPerDie);
                    br.close();
                } catch (IOException e) {
                    return;
                }
            }
            System.out.println("Start: " + start + ", End: " + end);
            DiceConfig startConfig = new DiceConfig(start, end, diceList, adMap);
            Solver.solver(startConfig);
            }

        }

    }
