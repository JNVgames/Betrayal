package com.jnv.betrayal.online;

import org.json.JSONObject;

public interface JsonSerializable {

	JSONObject toJson();

	void fromJson(JSONObject data);
}
