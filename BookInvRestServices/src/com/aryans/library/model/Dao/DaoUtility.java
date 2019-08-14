package com.aryans.library.model.Dao;

import java.util.Iterator;
import java.util.Set;

/**
 * This utility class consists of static methods which are commonly used amongst all the Dao classes. 
 * 
 * @author Shinchan
 *
 */
public class DaoUtility {
	
	public static String PrepareUpdateQuery(String rawSql, Set<String> columns) {
		StringBuilder sqlBuilder = new StringBuilder();
		
		//TODO: Think through this if it is possible to replace the below logic with streams.
		Iterator<String> iter = columns.iterator();
		while(iter.hasNext()) {
			sqlBuilder.append(iter.next()).append("=?");
			if(!iter.hasNext()) {
				break;
			}
			sqlBuilder.append(",");
		}
		
		
		return rawSql.replace("#p#", sqlBuilder.toString());
	}
}
