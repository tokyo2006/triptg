/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2003-2008 Frederico Caldeira Knabben
 * 
 * == BEGIN LICENSE ==
 * 
 * Licensed under the terms of any of the following licenses at your
 * choice:
 * 
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 * 
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 * 
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 * 
 * == END LICENSE ==
 */
package com.yeoou.fckeditor;

import net.fckeditor.connector.Messages;
import net.fckeditor.tool.Utils;

/**
 * Simply abstracts from the JavaScript callback to a Java object.
 * 
 * <p>
 * The usage is quite easy but can be tricky since varargs are used in the class
 * constructor.<br/> The requestor expects a JS method callback with variable
 * arguments size.
 * </p>
 * <p>
 * e.g.
 * <code>window.parent.OnUploadCompleted(101,'some/url/file.img','file.img','no error');</code>
 * </p>
 * <p>
 * The UploadResponse constructor behaves the same way by simply calling it
 * with:<br/>
 * <code>UploadResponse ur = new UploadResonse(SC_SOME_ERROR,"/some/url/file.img","file.img","no error"):</code>
 * </p>
 * 
 * @version $Id: UploadResponse.java 2151 2008-07-02 22:03:15Z mosipov $
 */
public class UploadResponse {

	private Object[] parameters;

	// Naming scheme according static status codes in javax.servlet.http.HttpServletResponse
	/** Error number OK */
	public static final int SC_OK = 0;

	/** Error number ERROR */
	public static final int SC_ERROR = 1;

	/** Error number RENAMED */
	public static final int SC_RENAMED = 201;

	/** Error number INVALID EXTENSION */
	public static final int SC_INVALID_EXTENSION = 202;

	/** Error number SECURITY ERROR */
	public static final int SC_SECURITY_ERROR = 203;
	
	/** UploadResponse INVALID CURRENT FOLDER */
	public static final UploadResponse UR_INVALID_CURRENT_FOLDER = new UploadResponse(
			UploadResponse.SC_ERROR, null, null,
			Messages.INVALID_CURRENT_FOLDER);

	/**
	 * Constructs the response with variable amount of parameters.
	 * <p>
	 * Put the desired parameters in the constructor. You may omit them from
	 * right to left but you have to remain the order.<br/> e.g.
	 * <code>UploadResponse(SC_OK,"/some/url/to/pic.jpg","pic")</code> or
	 * <code>UploadResponse(SC_OK)</code> but <b>not</b>
	 * <code>UploadResponse(SC_OK,"some error message")</code>
	 * </p>
	 * <p>
	 * Use, if possible, the pre-defined error numbers or upload responses.
	 * </p>
	 * <p>
	 * If you need to set error number and message only, use constructor with
	 * one parameter and call {@link UploadResponse#setCustomMessage(String)}.
	 * 
	 * @param arguments
	 *            possible argument order:
	 *            <code>int errorNumber, String fileUrl, String filename, String customMessage</code>
	 * @throws IllegalArgumentException
	 *             if amount of arguments is less than 1 and above 4
	 * @throws IllegalArgumentException
	 *             if the first argument is not an error number (int)
	 */
	public UploadResponse(Object... arguments) throws IllegalArgumentException {
		if (arguments.length < 1 || arguments.length > 4)
			throw new IllegalArgumentException(
					"The amount of arguments has to be between 1 and 4");

		parameters = new Object[arguments.length];

		if (!(arguments[0] instanceof Integer))
			throw new IllegalArgumentException(
					"The first argument has to be an error number (int)");

		System.arraycopy(arguments, 0, parameters, 0, arguments.length);
	}

	/**
	 * Sets the message in the <code>UploadResponse</code>.
	 * 
	 * Methods automatically determines how many arguments are set and puts the
	 * message at the end.
	 * 
	 * @param customMassage
	 *            the message you want to pass to the user
	 */
	public void setCustomMessage(final String customMassage) {
		if (Utils.isNotEmpty(customMassage)) {
			if (parameters.length == 1) {
				Object errorNumber = parameters[0];
				parameters = new Object[4];
				parameters[0] = errorNumber;
				parameters[1] = null;
				parameters[2] = null;
			}
			parameters[3] = customMassage;
		}
	}

	/**
	 * Assembles the JavaScript method for the user callback
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(400);
		sb.append("<script type=\"text/javascript\">\n");
        // Minified version of the document.domain automatic fix script.
		// The original script can be found at [fckeditor dir]/_dev/domain_fix_template.js
		sb.append("(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;}})();\n");//try{document.domain=d;}catch (e){break;}
		sb.append("window.parent.OnUploadCompleted(");

		for (Object parameter : parameters) {
			if (parameter instanceof Integer) {
				sb.append(parameter);
			} else {
				sb.append("'");
				if (parameter != null)
					sb.append(parameter);
				sb.append("'");
			}
			sb.append(",");
		}

		sb.deleteCharAt(sb.length() - 1);
		sb.append(");\n");
		sb.append("</script>");

		return sb.toString();
	}
}
