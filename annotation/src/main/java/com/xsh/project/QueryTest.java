package com.xsh.project;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class QueryTest {

	public static void main(String[] args) {
		
		User user = new User();
		user.setId(100l);
		
		User user2 = new User();
		user2.setId(100l);
		user2.setEmail("xiaosohua@163.com");
		
		User user3 = new User();
		user3.setId(100l);
		user3.setMobile("13249441234");
		user3.setEmail("xiaosohua@163.com,545367642@qq.com");

		System.out.println(querySql(user));
		System.out.println(querySql(user2));
		System.out.println(querySql(user3));
	}

	private static String querySql(User obj) {
		
		Class clazz  = obj.getClass();
		
		StringBuilder sbd = new StringBuilder();
		sbd.append("select * from ");
		if(!clazz.isAnnotationPresent(Table.class)) {
			return null;
		}
		
		Table table = (Table) clazz.getAnnotation(Table.class);
		sbd.append(table.value());
		sbd.append(" ").append(" where 1 = 1");
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			
			if(!field.isAnnotationPresent(Column.class)) {
				continue;
			}
			
			Column column = (Column)field.getAnnotation(Column.class);
			String columnName = column.value();
			
			String fieldName = field.getName();
			Object fieldValue = null;
			try {
				String getMethodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
				Method method = clazz.getMethod(getMethodName, null);
				fieldValue = (Object) method.invoke(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(null == fieldValue) {
				continue;
			}
			
			sbd.append(" and").append(" ").append(columnName);
			if(fieldValue instanceof String) {
				if(((String) fieldValue).contains(",")) {
					String[] params = ((String) fieldValue).split(",");
					sbd.append(" ").append("in").append(" (");
					for (String param : params) {
						sbd.append("'").append(param).append("'").append(",");
					}
					sbd.deleteCharAt(sbd.length()-1);
					sbd.append(")");
				}else {
					sbd.append("=").append("'").append(fieldValue).append("'");
				}
			}
			else {
				sbd.append("=").append(fieldValue);
			}
		}
		return sbd.toString();
	}

}
