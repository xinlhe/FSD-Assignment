package com.ibm.springframework.assignment.utils;

import java.util.UUID;

public class GenerateUUidUtil {

	public static String getUUid() {
		
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
}
