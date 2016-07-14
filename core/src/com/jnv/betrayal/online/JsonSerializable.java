package com.jnv.betrayal.online;

import org.json.JSONObject;

public interface JsonSerializable {

	void write(JSONObject json);

	void read(JSONObject json);
}
