package com.assignment;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		UI ui = new UI();
		String input = ui.getInput();
		Map<String, Double> scd = LanguageAnalyzer.countSingleChars(input);
		Map<String, Double> fld = LanguageAnalyzer.countStartingCharacters(input);
		Map<String, Double> td = LanguageAnalyzer.countCharacterTriplets(input);
		Analysis results = LanguageAnalyzer.analyze(scd, fld,td);
		ui.results = results;
		ui.show();



	}
}

