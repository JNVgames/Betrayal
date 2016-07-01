/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character.utils;

public enum Gender {
	MALE("M"),
	FEMALE("F");

	private String initial;

	Gender(String initial) {
		this.initial = initial;
	}

	public String getInitial() {
		return initial;
	}

	public Gender getOtherGender() {
		if (this == MALE) {
			return FEMALE;
		} else {
			return MALE;
		}
	}
}
