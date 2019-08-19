package com.aryans.library.model.Dao;

import java.util.Iterator;
import java.util.Set;

import com.aryans.library.model.Model;
import com.aryans.library.model.UserModel;

/**
 * This utility class consists of static methods which are commonly used amongst all the Dao classes. 
 * 
 * @author Shinchan
 *
 */
public class DaoUtility {
	
	public static String PrepareUpdateQuery(String rawSql, Set<String> columns, Model model) {
		StringBuilder sqlBuilder = new StringBuilder();		
		
		//TODO: Think through this if it is possible to replace the below logic with streams.
		Iterator<String> iter = columns.iterator();
		while(iter.hasNext()) {
			String st = model.getColumnsMap().get(iter.next());
			if(st == null) continue;
			sqlBuilder.append(st).append("=?");
			if(!iter.hasNext()) {
				break;
			}
			sqlBuilder.append(",");
		}
		
		
		return rawSql.replace("#p#", sqlBuilder.toString());
	}
}
