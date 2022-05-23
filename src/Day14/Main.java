package Day14;

import Util.DataReader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {

        DataReader dataReader = new DataReader("./Data/Day14.txt");
        List<String> inputString = dataReader.readUntilEmptyLine();
        String startPattern = inputString.get(0);
        List<String> ruleStrings = dataReader.readUntilEOF();

        System.out.println(startPattern);
        List<Rule> rules = ruleStrings.stream().map(Rule::new).collect(Collectors.toList());
        Map<String, Long> patternOccurrences = new HashMap<>();

        for (Rule rule : rules) {
            patternOccurrences.put(rule.input, 0L);
        }

        for (int i = 0; i < startPattern.length() - 1; i++) {
            Long count = patternOccurrences.get(startPattern.substring(i,i+2));
            patternOccurrences.replace(startPattern.substring(i,i+2), count+1);
        }



        for (int i=0; i<40; i++) {
            HashMap<String,Long> newMap = new HashMap<>();
            for (String string : patternOccurrences.keySet()) {
                Long n = patternOccurrences.get(string);
                String result = rules.stream().filter(r -> r.input.equals(string)).map(r -> r.output).findAny().get();
                newMap.merge(result.substring(0, 2), n, Long::sum);
                newMap.merge(result.substring(1, 3), n, Long::sum);
            }
            patternOccurrences = newMap;
        }

        List<Character> characters = new LinkedList<>();
        for (String s : patternOccurrences.keySet()) {
            for (Character c : s.toCharArray()) {
                if (!characters.contains(c)) {
                    characters.add(c);
                }
            }
        }
        Map<Character, Long> freq = new HashMap<>();
        for (Character character : characters) {
            Long sum = 0L;
            for (String s : patternOccurrences.keySet()) {
                Long occ = patternOccurrences.get(s);
                if (s.charAt(0) == character) {
                    sum+= occ;
                }
                if (s.charAt(1) == character) {
                    sum+= occ;
                }
            }
            if (character == 'B' || character == 'K') {
                sum++;
            }
            freq.put(character, sum/2);
            System.out.println(character + ": " + freq.get(character));
        }
        Long min = freq.values().stream().min(Long::compareTo).get();
        Long max = freq.values().stream().max(Long::compareTo).get();
        System.out.println(max - min);
    }
}
