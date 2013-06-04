/**
 * 
 */
package com.dishminer.resturantsUtils;

import java.util.HashMap;
import java.util.Map;


public class ReverseEnumLookup<E extends EnumExtender> {
	private Map<Integer, E> idMap;
	private Map<String, E> nameMap;

	/**
	 * @param enumType
	 */
	public ReverseEnumLookup(Class<E> enumType) {
		// Initialize the maps.
		idMap = new HashMap<Integer, E>();
		nameMap = new HashMap<String, E>();
		// get enum variables from given enum
		E[] enumConstants = enumType.getEnumConstants();
		if (enumConstants.length > 0) {
			for (E enumConstant : enumConstants) {
				idMap.put(Integer.valueOf(enumConstant.getId()), enumConstant);
				nameMap.put(enumConstant.getName(), enumConstant);
			}
		}
	}

	/**
	 * Return the enum type of given id.
	 * 
	 * @param id
	 *            - Id of enum.
	 * @return E enum type
	 */
	public E getById(int id) {
		return idMap.get(id);
	}

	/**
	 * Returns enum type of given name.
	 * 
	 * @param name
	 *            - name of enum.
	 * @return E - enum type.
	 */
	public E getByName(String name) {
		return nameMap.get(name);
	}
}
