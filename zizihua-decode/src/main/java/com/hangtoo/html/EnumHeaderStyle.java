package com.hangtoo.html;

/**
 * 
 * @author hlf 
 * html table header layout,header in top/right/bottom/left or none
 */
public enum EnumHeaderStyle {
	TOP("TOP", 1), RIGHT("RIGHT", 2), BOTTOM("BOTTOM", 3), LEFT("LEFT", 4), NONE("NONE", 0);

	String style;
	int value;

	private EnumHeaderStyle(String style, int value) {
		this.style = style;
		this.value = value;
	}
}
