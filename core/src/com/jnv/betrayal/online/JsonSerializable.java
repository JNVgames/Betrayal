package com.jnv.betrayal.online;

import org.json.JSONObject;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public interface JsonSerializable {

	JSONObject toJson();

	void fromJson(JSONObject data);
}
