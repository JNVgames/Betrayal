package com.jnv.betrayal.lobby.inventory;

import com.jnv.betrayal.scene2d.Dimension;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

inal class CharacterChart {

	static final int SLOTS = 7;
	static final float LENGTH = 92;

	static final Dimension HEAD = new Dimension(212, 525, LENGTH, LENGTH);
	static final Dimension BODY = new Dimension(212, 425, LENGTH, LENGTH);
	static final Dimension LEFT_HAND = new Dimension(112, 325, LENGTH, LENGTH);
	static final Dimension RIGHT_HAND = new Dimension(312, 325, LENGTH, LENGTH);
	static final Dimension RING_1 = new Dimension(518, 325, LENGTH, LENGTH);
	static final Dimension RING_2 = new Dimension(518, 225, LENGTH, LENGTH);
	static final Dimension CLOAK = new Dimension(518, 525, LENGTH, LENGTH);

	static final Dimension[] ALL_DIMENS = {
			HEAD, BODY, LEFT_HAND, RIGHT_HAND, RING_1, RING_2, CLOAK
	};
}
