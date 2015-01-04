package org.pajement.models;

public class WebObjectFactory {
	
	private static final String ROOT_PATH = "//*";
	
	public WebObject create(String parentPath, String path) {
		if (parentPath == null && path == null) {
			return new WebObject(ROOT_PATH);
		} else if (parentPath == null) {
			return doCreate(ROOT_PATH, path);
		} else {
			return doCreate(parentPath, path);
		}
	}
	
	public WebObject create() {
		return create(null,null);
	}
	
	public WebObject create(String path) {
		return create(null, path);
	}
	
	private WebObject doCreate(String parentPath, String path) {
		return new WebObject(xpathyPath(parentPath, path));
	}
	
	private String xpathyPath(String parentPath, String givenPath) {
		String xpathed;
		if (givenPath.matches("(^/+)(.*)")) {
			xpathed = givenPath;
			return parentPath + xpathed;
		} else if (givenPath.contains("#")) {
			xpathed = xpathyCss(givenPath.indexOf("#"), givenPath, "id");
			return parentPath + xpathed;
		} else if (givenPath.contains(".")) {
			xpathed = xpathyCss(givenPath.indexOf("."), givenPath, "class");
			return parentPath + xpathed;
		} else if (givenPath.contains("[") && givenPath.contains("=") && givenPath.contains("]")) {
			xpathed = xpathyBracketNotation(givenPath);
			return parentPath + xpathed;
		} else {
			return parentPath + "//" + givenPath;
		}
	}

	private String xpathyCss(int index, String givenPath, String css_type) {
		String tag, value;
		if (index > 0) {
			if (index < givenPath.length() - 1) {
				tag = givenPath.substring(0, index);
				value = givenPath.substring(index + 1, givenPath.length());
				return "//" + tag + "[@" + css_type + " = '" + value + "']";
			} else {
				tag = givenPath.substring(0, index);
				return "//" + tag;
			}
		} else {
			value = givenPath.substring(1, givenPath.length());
			if (value.length() > 0) {
				return "//*[@" + css_type + " = '" + value + "']";
			} else {
				return "//" + css_type;
			}
		}
	}

	private String xpathyBracketNotation(String givenPath) {
		int bracketIndex = givenPath.indexOf("[");
		String sign = "";

		if (!givenPath.contains("[text=") && !givenPath.contains("[text =")) {
			sign = "@";
		}

		if (bracketIndex > 0) {
			return "//" + givenPath.substring(0, bracketIndex) + "[" + sign
					+ givenPath.substring(bracketIndex + 1, givenPath.length());
		} else {
			return ROOT_PATH + "[@" + givenPath.substring(1, givenPath.length());
		}
	}
}
