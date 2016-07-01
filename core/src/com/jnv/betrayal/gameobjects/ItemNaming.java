/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

/**
 * Provides static methods that convert item names to preview names
 */
public class ItemNaming {
	// Current preview naming system: Itemtype + color + side

	/**
	 * Converts a dual-wieldable item icon name to the preview name
	 *
	 * @param itemIconName the item icon name
	 * @param isLeft       true if you want the left side preview, false if you want the right side
	 * @return the preview name
	 */
	public static String toPreview(String itemIconName, boolean isLeft) {
		String preview = "preview" + itemIconName.substring(0, itemIconName.length() - 1);
		if (isLeft) {
			preview += "left";
		} else preview += "right";
		return preview;
	}

	/**
	 * Converts a previewable item icon name to the preview name
	 *
	 * @param itemIconName the item icon name
	 * @return the preview name
	 */
	public static String toPreview(String itemIconName) {
		return "preview" + itemIconName.substring(0, itemIconName.length() - 1);
	}
}
