package com.assignment;

import java.util.*;

public class LanguageAnalyzer {
    public static double compare(Map<String, Double> map1, Map<String, Double> map2){
        double output = 0;
        Map<String, Double> dif = new HashMap<String, Double>();
        HashSet<String> unionKeys = new HashSet<>(map1.keySet()); unionKeys.addAll(map2.keySet()); //make a hashset of the keys from both maps, this will be used to make sure we iterate through every element that is present in either sets
        for (String key : unionKeys) {
            if( map1.containsKey(key) && map2.containsKey(key)){
                //if the key is present in both maps
                dif.put(key, Math.abs(map1.get(key) - map2.get(key)));
            }else if(map1.containsKey(key) && !map2.containsKey(key)){
                //if the key is present in the first map
                dif.put(key, map1.get(key));
            }else if(!map1.containsKey(key) && map2.containsKey(key)){
                //if the key is present in the second map
                dif.put(key, map2.get(key));
            }
        }

        for (Map.Entry<String,Double> entry : dif.entrySet()){
            output += entry.getValue();
        }

        return output;
    }

    public static Analysis analyze(Map<String, Double> singleCharacterMap, Map<String, Double> firstLetterMap, Map<String, Double> tripletMap){
    String[] langs = {"DE", "EN", "ES", "FI", "FR", "IT", "NO", "SV"};
        Analysis analysis = new Analysis();
        double lowest = -1; //the lowest value might actually be 0 if there is a perfect match, hence it can not be used
        double current;
        for(int i = 0; i < langs.length; i++){
            Map<String, Double> scd = (Map<String, Double>) FileUtils.loadObject(System.getProperty("user.dir") + "/assets/characterDistribution/"+langs[i]+"_distribution.txt");
            Map<String, Double> fld = (Map<String, Double>) FileUtils.loadObject(System.getProperty("user.dir") + "/assets/flDistribution/"+langs[i]+"_fldistribution.txt");
            Map<String, Double> td = (Map<String, Double>) FileUtils.loadObject(System.getProperty("user.dir") + "/assets/tripletDistribution/"+langs[i]+"_tdistribution.txt");
            current = ( compare(singleCharacterMap, scd) + compare(firstLetterMap, fld) + compare(tripletMap, td) );
            if(lowest == -1 || lowest > current){
                lowest = current;
                analysis.guess = LangLabel.valueOf(langs[i]);
            }
            analysis.results.put(langs[i], current);
		}

        return analysis;
    }

    public static String cleanInput(String input, boolean removeWhitespace){
        input = input.replaceAll("[^\\p{L}^\\s]", ""); //remove non-unicode characters except whitespace
        if(removeWhitespace){
            //whitespace is needed for the starting letter test
            input = input.replaceAll(" ", "");
        }
        input = input.replaceAll("\\s{2,}", " "); //replaces repeating whitespace with a single whitespace
        input = input.toLowerCase();

        return input;
    }
    public static Map<String, Double> countSingleChars(String input){

        input = cleanInput(input, true);
        int srcAbsSize = 0;
        Map<String, Double> output = new HashMap<String, Double>();
        ArrayList<String> duplicates = new ArrayList<String>();
        String[] characters = input.split("");
        for(int i = 0; i < input.length(); i++){
            if(!duplicates.contains("" + input.charAt(i))){
                duplicates.add("" + input.charAt(i)); //apparently you can not type cast chars into Strings so just make a new string and concat the char to that string :/
            }
        }
        for(int i = 0; i < duplicates.size(); i++){
            String character = duplicates.get(i);
            double frequency = Collections.frequency(Arrays.asList(characters), character);
            srcAbsSize += frequency;
            output.put(character, frequency);

        }
    output = countPercentages(output, srcAbsSize);
    return output;
    }
    public static Map<String, Double> countCharacterTriplets(String input){
        Map<String, Double> output = new HashMap<String, Double>();
        int srcAbsSize = 0;
        input = cleanInput(input, true);
        for(int i = 0; i < input.length()-2; i++){
            srcAbsSize++;
            String currentTriplet = input.substring(i, i+3);
            if(output.containsKey(currentTriplet)){
                output.put(currentTriplet, output.get(currentTriplet) + 1);
            }else{
                output.put(currentTriplet, 1.0);
            }
        }



        output = countPercentages(output, srcAbsSize);
        return output;
    }
    public static Map<String, Double> countStartingCharacters(String input){
        input = cleanInput(input, false);
        int srcAbsSize = 0;
        Map<String, Double> output = new HashMap<String, Double>();
        ArrayList<String> duplicates = new ArrayList<String>();
        String[] words = input.split(" ");
        for(int i = 0; i < input.length(); i++){
            if(!duplicates.contains("" + input.charAt(i))){
                duplicates.add("" + input.charAt(i));
            }
        }
        for(int i = 0; i < words.length; i++){
            srcAbsSize++;
            if(output.containsKey(""+words[i].charAt(0))){
                output.put(""+words[i].charAt(0), output.get(""+words[i].charAt(0)) + 1);
            }else{
                output.put(""+words[i].charAt(0), 1.0);
            }
        }
        output = countPercentages(output, srcAbsSize);
        return output;
    }
    public static Map<String, Double> countPercentages(Map<String, Double> src, int srcAbsSize){
        Map<String, Double> output = new HashMap<String, Double>();
        for (Map.Entry<String,Double> entry : src.entrySet()){
            output.put(entry.getKey(), ( entry.getValue() / srcAbsSize ));//entry.getKey()
            // //entry.getValue());
        }
        return output;
    }
}
