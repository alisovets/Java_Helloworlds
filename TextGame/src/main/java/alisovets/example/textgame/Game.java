package alisovets.example.textgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import alisovets.example.textgame.dto.GameLocation;
import alisovets.example.textgame.dto.GameOption;

/**
 * 
 * A simple test game.
 * It is a test task #3 for the iOS Trainee vacancy.
 *
 */
public class Game {
	private static final String USER_INPUT_HINT = "Введите номер варианта.";
	private static final String INCORRECT_USER_INPUT_HINT = "Hе верный ввод, повторите пожалуйста.";
	private static final String FILE_NOT_EXISTS_FORMAT = "Файл \"%s\" не существует!";
	private static final String FILE_READING_FAIL_FORMAT = "Не удается прочитать файл \"%s\"!";
	private static final String WRONG_FILE_FORMAT_RORMAT = "Не верный формат файла \"%s\"!";
	private static final String NO_PATH_TO_CONF_ERRMESG = "Не указан путь к файлу конфигурации игры!"; 
	
	private static final String GAME_JSON_KEY = "game";
	private static final int START_ID = 1;
	
	
	private List<GameLocation> locationList = new ArrayList<GameLocation>();

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println(NO_PATH_TO_CONF_ERRMESG);
			return;
		}

		Game game = new Game();
		File file = new File(args[0]);
		try {
			String jsonString = game.fileToString(file);
			game.jsonToLocationList(jsonString);

		} catch (FileNotFoundException e) {
			System.err.format(FILE_NOT_EXISTS_FORMAT, file.getName());
			return;
		} catch (IOException e) {
			System.err.format(FILE_READING_FAIL_FORMAT, file.getName());
			return;
		} catch (ParseException e) {
			System.err.format(WRONG_FILE_FORMAT_RORMAT, file.getName());
			return;
		}
		game.startGame();	
	}

	/*
	 * reads the specified text file and returns a string with the contents of the file. 
	 */
	private String fileToString(File file) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String string;
		try {
			StringBuilder stringBuilder = new StringBuilder();

			String line = reader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				line = reader.readLine();
			}
			string = stringBuilder.toString();
			return string;

		} finally {
			reader.close();
		}
	}

	/*
	 * parses the JSON string with a game configuration and creates list of game locations. 
	 */
	private void jsonToLocationList(String jsonString) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
		JSONArray jsonLocations = (JSONArray) jsonObject.get(GAME_JSON_KEY);
		if (jsonLocations == null) {
			throw new ParseException(ParseException.ERROR_UNEXPECTED_EXCEPTION);
		}

		for (Object jsonLocation : jsonLocations) {
			GameLocation gameLocation = new GameLocation((JSONObject) jsonLocation);
			locationList.add(gameLocation);
		}
		Collections.sort(locationList);
	}
	
	/*
	 * a main game loop;
	 */
	private void startGame() {
		
		long locationId = START_ID;
		
		while (true) {
			GameLocation gameLocation = findLocationById(locationId);
			System.out.println("\n"+ gameLocation.getDescription());
			
			if (gameLocation.optionsSize() == 0) {
				return;
			}
			for (int i = 0; i < gameLocation.optionsSize(); i++) {
				GameOption option = gameLocation.getOption(i);
				System.out.println(i + 1 + ". " + option.getDescription());
			}
			System.out.println(USER_INPUT_HINT);
			
			int index;
			try {
				index = getIntegerFromUser(1, gameLocation.optionsSize());
			} catch (IOException e) {
				System.err.println(e.getMessage());
				return;
			}
			
			GameOption option = gameLocation.getOption(index - 1);
			locationId = option.getTargetLocationId();
		}
		
	}

	/*
	 * searches an returns a GameLocation object with a specified id in the locationList.
	 * returns null if object was not found.
	 * The list must be sorted by id! 
	 */
	private GameLocation findLocationById(long id) {
		int startIndex = 0;
		int endIndex = locationList.size();
		int curentIndex = startIndex;
		
		GameLocation gameLocation = locationList.get(curentIndex); 
		if (id == gameLocation.getId()) {
			return gameLocation;
		}
		curentIndex = (startIndex + endIndex) / 2;
		while (curentIndex > startIndex) {

			gameLocation = locationList.get(curentIndex);
			if (id == gameLocation.getId()) {
				return gameLocation;
			}

			if (id > gameLocation.getId()) {
				startIndex = curentIndex;
			} else {
				endIndex = curentIndex;
			}
			curentIndex = (startIndex + endIndex) / 2;
		}

		return null;
	}


	/*
	 * gets and returns a integer value from the user input. Ensures that the value is in the specified range of values.
	 */
	private int getIntegerFromUser(int minValue, int maxValue) throws IOException {
		String userInput;
		int index = minValue - 1;
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			userInput = bufferRead.readLine();

			try {
				index = Integer.valueOf(userInput);
			} catch (NumberFormatException nfe) {
				// ignore
			}

			if ((index >= minValue) && (index <= maxValue)) {
				// a correct value was inserted
				break;
			}

			// an incorrect value was inserted
			System.out.println(INCORRECT_USER_INPUT_HINT);
		}

		return index;
	}
}
