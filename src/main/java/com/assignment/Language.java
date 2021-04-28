package com.assignment;

import java.util.HashMap;

public class Language {

    private String content;
    private LangLabel langLabel;

    private HashMap<String,Integer> charCount = new HashMap<>();
    private HashMap<String,Double> charDistribution = new HashMap<>();

    public Language(String content, LangLabel langLabel) {

        this.content = content.replaceAll("[^\\p{L}]", "");
        this.langLabel = langLabel;

        calculateCharDistribution();
    }

    private void calculateCharDistribution() {

        for (int i = 0; i < content.length(); i++) {
            String letter = Character.toString(content.charAt(i));
            charCount.put(letter, charCount.getOrDefault(letter, 0)+1);
        }

        for (String key : charCount.keySet()) {
            double distr = (double) charCount.getOrDefault(key, 0) / (double) content.length() *100;
            charDistribution.put(key, distr);
        }

    }

    public String getContent() {
        return content;
    }

    public HashMap<String, Integer> getCharCount() {
        return charCount;
    }

    public HashMap<String, Double> getCharDistribution() {
        return charDistribution;
    }
}
