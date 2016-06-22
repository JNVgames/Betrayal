/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.lobby.inventory;

enum Option {
	Equip(" Equip"),
	Unequip(" Unequip"),
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
