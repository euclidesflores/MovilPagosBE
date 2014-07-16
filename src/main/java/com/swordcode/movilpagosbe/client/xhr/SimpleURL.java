package com.swordcode.movilpagosbe.client.xhr;

/**
 *
 * @author euclidesflores
 */
public class SimpleURL {
	private String							_protocol;
	private String							_host;
	private int								_port;
	private String							_path;
	private transient String				_composed;

	public SimpleURL(String url) {
		parse(url);
	}

	public SimpleURL(String protocol, String host, int port, String path) {
		_protocol = protocol;
		_host = host;
		_port = port;
		_path = cleanPath(path);
	}

	public String getProtocol() {
		return _protocol;
	}

	public String getHost() {
		return _host;
	}

	public int getPort() {
		return _port;
	}

	public String getPath() {
		return _path;
	}

	public SimpleURL withProtocol(String protocol) {
		return new SimpleURL(protocol, _host, _port, _path);
	}

	public SimpleURL withHost(String host) {
		return new SimpleURL(_protocol, host, _port, _path);
	}

	public SimpleURL withHostPort(String host, int port) {
		return new SimpleURL(_protocol, host, port, _path);
	}

	public SimpleURL withPort(int port) {
		return new SimpleURL(_protocol, _host, port, _path);
	}

	public SimpleURL withPath(String path) {
		return new SimpleURL(_protocol, _host, _port, cleanPath(path));
	}

	public SimpleURL addPath(String path) {
		return new SimpleURL(_protocol, _host, _port, _path + "/" + cleanPath(path));
	}

	@Override
	public String toString() {
		compose();
		return _composed;
	}

	private void parse(String url) {
		int ix = url.indexOf("://");
		if (ix < 0) invalid(url);
		_protocol = url.substring(0, ix);
		ix += 3;
		if (ix >= url.length()) invalid(url);
		int iy = url.indexOf('/', ix + 3);
		if (iy < 0) iy = url.length();
		_host = url.substring(ix, iy);
		// Parse out the port if one is specified
		int ip = _host.indexOf(':');
		if (ip >= 0) {
		if (ip + 1 >= _host.length()) invalid(url);
		try {
				_port = Integer.parseInt(_host.substring(ip + 1));
		}
		catch (NumberFormatException nfe) {
				invalid(url);
		}
		_host = _host.substring(0, ip);
		}
		iy++;
		if (iy < url.length()) _path = cleanPath(url.substring(iy));
		else _path = "";
	}

	private String cleanPath(String path) {
		// Path should not contain any leading or trailing spaces
		path = path.trim();

		// Path should not contain any leading slashes
		while (path.startsWith("/")) path = path.substring(1);

		// Path should not contain any trailing slashes
		while (path.endsWith("/")) path = path.substring(0, path.length() - 1);

		return path;
	}

	private void invalid(String url) {
		throw new IllegalArgumentException("Malformed URL: " + url);
	}

	private void compose() {
		if (_composed != null) return;

		StringBuilder buf = new StringBuilder();
		buf.append(_protocol);
		buf.append("://");
		buf.append(_host);
		if (_port != 0) {
			buf.append(':');
			buf.append(_port);
		}
		buf.append('/');
		if (_path != null) {
			buf.append(_path);
//      if (!_path.endsWith("/")) buf.append('/');
		}
		_composed = buf.toString();
	}
}
