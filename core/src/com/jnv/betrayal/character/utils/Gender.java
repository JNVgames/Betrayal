package com.jnv.betrayal.character.utils;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

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
