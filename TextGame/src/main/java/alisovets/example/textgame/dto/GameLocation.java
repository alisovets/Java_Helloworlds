package alisovets.example.textgame.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * To hold a game location object
 *
 */
public class GameLocation implements Serializable, Comparable<GameLocation>{

	private static final long serialVersionUID = -3269515364729452379L;
	public static final String ID_JSON_KEY = "id";
	public static final String DESCRIPTION_JSON_KEY = "descript";
	public static final String OPTIONS_JSON_KEY = "options";
	
	
	private long id;
	private String description;
	private List<GameOption> optionList;
	
	/**
	 * Default constructor;
	 */
	public GameLocation(){
		optionList = new ArrayList<GameOption>();
	}
	
	/**
	 * Creates a game location with the specified parameters
	 * @param id - id
	 * @param description - a description of the location 
	 */
	public GameLocation(int id, String description){
		this();
		this.id = id;
		this.description = description;	
	}
	
	/**
	 * creates object as the result of parsing the specified JSON object
	 * @param jsonObject - the JSON Object to parse
	 */
	public GameLocation(JSONObject jsonObject){
		this();
		set(jsonObject);
		
	}
	
	/**
	 * initials object by values which gets as the result of parsing the specified JSON object
	 * @param jsonObject
	 */
	public void set(JSONObject jsonObject){
		id = (Long)jsonObject.get(ID_JSON_KEY);
		description = (String)jsonObject.get(DESCRIPTION_JSON_KEY);
		JSONArray optionArray = (JSONArray) jsonObject.get(OPTIONS_JSON_KEY);
		
		if(optionList.size() > 0){
			optionList.clear();
		}
		
		if(optionArray == null){
			return;
		}
		
		for(Object optionJson : optionArray){
			GameOption option = new GameOption((JSONObject) optionJson);
			optionList.add(option);
		}
		sortOptions();
	}

	/*
	 * to sort by id
	 */
	@Override
	public int compareTo(GameLocation location) {
		return (int)(id - location.id);
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the number of options
	 */
	public int optionsSize(){
		return optionList.size();
	}
	
	/**
	 * gets the GameOption object by index  
	 * @param index
	 * @return gotten object;
	 */
	public GameOption getOption(int index){
		return optionList.get(index);
	}
	
	/**
	 * adds the specified GameOption object to the current object
	 * @param gameOption GameOption object to add.
	 * @return true if added success
	 */
	public boolean addOption(GameOption gameOption){
		return optionList.add(gameOption);
	}
	
	/**
	 * Deletes all of the options
	 */
	public void clearOptions(){
		optionList.clear();
	}
	
	/**
	 * Sorts options
	 */
	public void sortOptions(){
		Collections.sort(optionList);
	}
	
}
