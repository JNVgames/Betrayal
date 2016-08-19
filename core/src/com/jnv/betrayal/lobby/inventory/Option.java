package com.jnv.betrayal.lobby.inventory;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

enum Option {
	Equip(" Equip"),
	Unequip(" Eject"),
	Use(" Use"),
	Info(" Info"),
	Sell(" Sell"),
	Cancel(" Cancel");

	private String text;

	Option(String text) {
		this.text = text;
	}

	public String toString() {
		return text;
	}
}
