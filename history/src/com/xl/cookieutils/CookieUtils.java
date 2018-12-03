package com.xl.cookieutils;

import javax.servlet.http.Cookie;

public class CookieUtils {

	public static Cookie getCookieByName(String string, Cookie[] cookies) {
		if(cookies!=null)
		{
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("ids"))
				{
					return cookie;
				}
			}
		}
		return null;
	}
	
}
