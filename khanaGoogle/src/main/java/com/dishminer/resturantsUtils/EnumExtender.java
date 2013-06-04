/**
 * 
 */
package com.dishminer.resturantsUtils;

/**
 * <pre>
 * Title:   ECASEnumExtender
 * Description: Interface have common functionality like getId , getName.
 * Copyright:      Copyright (c) 2011
 * Company:        eBay
 * @author Persistent Systems Ltd.
 * @version 1.0.0
 * 
 * </pre>
 */
public interface EnumExtender {
	/**
	 * @return id defined in enum
	 */
	int getId();

	/**
	 * @return name of enum.
	 */
	String getName();
}
