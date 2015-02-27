package alisovets.example.textgame.dto;

import java.io.Serializable;

import org.json.simple.JSONObject;

/**
 * To store a game option
 *
 */
public class GameOption implements Serializable, Comparable<GameOption> {

	private static final long serialVersionUID = -8976796589461193679L;
	public static final String POS_JSON_KEY = "pos";
	public static final String DESCRIPTION_JSON_KEY = "descript";
	public static final String TARGET_JSON_KEY = "aim";

	private long position;
	private String description;
	private long targetLocationId;

	/**
	 * Default constructor;
	 */
	public GameOption() {
	}

	/**
	 * Creates a game option with the specified parameters
	 * @param position - position in the list 
	 * @param description - a description of the option
	 * @param id of game location to pass after select this option  
	 */
	public GameOption(int position, String description, int targetLocationId) {
		
		this.position = position;
		this.description = description;
		this.targetLocationId = targetLocationId;
	}

	/**
	 * Creates a game option with the specified parameters
	 * @param id - id
	 * @param description - a description of the location 
	 */
	public GameOption(JSONObject jsonObject) {
		set(jsonObject);
	}

	
	/**
	 * initials object by values which gets as the result of parsing the specified JSON object
	 * @param jsonObject
	 */
	public void set(JSONObject jsonObject) {
		position = (Long) jsonObject.get(POS_JSON_KEY);
		description = (String) jsonObject.get(DESCRIPTION_JSON_KEY);
		targetLocationId = (Long) jsonObject.get(TARGET_JSON_KEY);
	}

	@Override
	public int compareTo(GameOption option) {
		return (int)(position - option.position);
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getTargetLocationId() {
		return targetLocationId;
	}

	public void setTargetLocationId(long targetLocationId) {
		this.targetLocationId = targetLocationId;
	}

}
