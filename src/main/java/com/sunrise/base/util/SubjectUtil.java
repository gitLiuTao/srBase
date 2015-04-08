/**
 *<p>Copyright: Copyright (c) 2012</p> 
 *<p>Company: SCPII</p>
 *<p>Project:optimus-manage</p>
 *@author xiezhouyan
 *@Date 2013-4-29 下午5:06:43
 *@version 1.0
 *@description:
 */
package com.sunrise.base.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.subject.WebSubject;

public class SubjectUtil {

//	public static AuthUser getSubject() {
//		WebSubject subject = (WebSubject) SecurityUtils.getSubject();
//		if (subject == null) {
//			return null;
//		}
//		Object principal = subject.getPrincipal();
//		if (principal instanceof AuthUser) {
//			return (AuthUser) principal;
//		} else {
//			return null;
//		}
//	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static void logout() {
		WebSubject subject = (WebSubject) SecurityUtils.getSubject();
		subject.logout();
	}
}
