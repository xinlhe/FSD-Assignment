package com.ibm.springframework.assignment.utils;

import javax.servlet.http.HttpServletRequest;

public class VarifyCaptchaUtil {

	// @RequestMapping("/imgvrifyControllerDefaultKaptcha")
	public static boolean varifyCaptcha(HttpServletRequest httpServletRequest, String vrifyCode) {

		boolean varifyFlag;
		String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
		
		System.out.println("Session  vrifyCode " + captchaId + " form vrifyCode " + vrifyCode);

		if (!captchaId.equalsIgnoreCase(vrifyCode)) {

			varifyFlag = false;

		} else {

			varifyFlag = true;
		}
		return varifyFlag;
	}

}
