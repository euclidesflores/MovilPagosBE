package com.swordcode.movilpagosbe.client.util;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author euclidesflores
 */
public class JsonUtils {
	private final static DateTimeFormat _sdf = DateTimeFormat.getFormat("yyyy-MM-ddTHH:mm:ss.SZZZZ");

	public static Date getJsonDate(String dateStr) {
		return _sdf.parse(dateStr);
	}

	public static String getURLConstraints(boolean hasWhereConstraints, String k, String v) {
		String url = "";
		Boolean firstParam = true;
		url = "?";
		if (hasWhereConstraints)
			{
				if (!firstParam)
				{
					url += "&";
				}
				else
				{
					firstParam = false;
				}

				url += "where=" + getJSONWhereConstraints(k, v);
			}
		return url;
	}

	private static String getJSONWhereConstraints(String k, String v) {
		String js = "";
		JSONObject jo = new JSONObject();
		jo.put(k, new JSONString(v));
		js = jo.toString();
		return js;
	}
}
