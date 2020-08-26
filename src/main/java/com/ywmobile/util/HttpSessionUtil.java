package com.ywmobile.util;

import javax.servlet.http.HttpSession;
import com.ywmobile.domain.User;

public class HttpSessionUtil {
	public static final String USER_SESSION_KEY = "sessionedUser";

	public static boolean isLoginUser(HttpSession httpSession) {
		Object sessionedUser = httpSession.getAttribute(USER_SESSION_KEY);
		if (sessionedUser == null) {
			return false;
		}

		return true;
	}

	public static User getUserFromSession(HttpSession httpSession) {
		if (!isLoginUser(httpSession)) {
			return null;
		}

		return (User) httpSession.getAttribute(USER_SESSION_KEY);
	}
}
