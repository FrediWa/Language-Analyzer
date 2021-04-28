package com.assignment;

import java.util.Map;
import java.util.Scanner;

public class UI {

    Analysis results;

    public String getInput(){

        Scanner scanner = new Scanner(System.in);


        System.out.println("Write something: ");
        String input = scanner.nextLine();

        return input;
    }

    public void show(){
        System.out.println("Here are your results: ");
        for (Map.Entry<String,Double> entry : results.results.entrySet())
            System.out.println(entry.getKey() + " : " + entry.getValue());
            System.out.println("I think the language is "+results.guess.getName());
    }
}
