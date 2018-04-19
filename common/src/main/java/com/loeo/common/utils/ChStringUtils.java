package com.loeo.common.utils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 功能：
 *
 * @author ：renzhongshan(zhongshan.ren@ihydt.com)
 * @create ：2017-03-08 17:02:28
 * @version ：2017 Version：1.0

 */
public class ChStringUtils extends org.apache.commons.lang3.StringUtils {
	private static Logger logger = LoggerFactory.getLogger(ChStringUtils.class);
	private static String[] charSets = {"GBK", "GB2312", "UTF-8", "ISO-8859-1"};
	private static String filterMarkdownTagMatch = "[^ _0-9A-Za-z\\u4e00-\\u9fa5]*";

	public static String collectionToString(Collection collection, ICallable<String> callable) {
		if (callable == null) {
			callable = args -> args[0].toString();
		}
		boolean isFist = true;
		StringBuilder ret = new StringBuilder();
		for (Object obj : collection) {
			String cbResult = callable.call(obj);
			if (cbResult == null) {
				continue;
			}
			if (isFist) {
				ret.append(cbResult);
				isFist = false;
			} else {
				ret.append(",").append(cbResult);
			}
		}
		return ret.toString();
	}

	private static final Pattern pattern = Pattern.compile("(\\{(\\d+)\\})");

	public static String format(String string, Object... args) {
		if (string == null) {
			return null;
		}
		Matcher m = pattern.matcher(string);
		if (m.find()) {
			StringBuffer sb = new StringBuffer();
			while (true) {
				Integer g2 = Integer.parseInt(m.group(2));
				if (g2 >= 0 && g2 < args.length) {
					m.appendReplacement(sb, Matcher.quoteReplacement(args[g2].toString()));
				}
				if (!m.find()) {
					break;
				}
			}
			m.appendTail(sb);
			return sb.toString();
		}
		return string;
	}

	public static String uppercaseFirstChar(String in) {
		if (in == null || in.length() == 0) {
			return in;
		}
		int length = in.length();
		StringBuilder sb = new StringBuilder(length);

		sb.append(Character.toUpperCase(in.charAt(0)));
		if (length > 1) {
			String remaining = in.substring(1);
			sb.append(remaining);
		}
		return sb.toString();
	}

	public static boolean regMatched(String text, String reg) {
		if (ChStringUtils.isEmpty(text)) {
			text = "";
		}
		Matcher m = Pattern.compile(reg).matcher(text);
		return m.matches();
	}

	/**
	 * 获取字符串长度(支持中文)
	 *
	 * @param str
	 * @return -1表示获取长度失败
	 */
	public static final int getStringLength(String str) {
		if (ChStringUtils.isEmpty(str)) {
			return 0;
		}
		//党冬 2017年6月23日　看不明白getCharSet作用暂时修改验证
		return str.length();
		/*try {
			return str.getBytes(getCharSet()).length;
		} catch (Exception e) {
			logger.error("", e);
		}
		return -1;*/
	}

	private static String getCharSet() {
		String cn = "a中";
		try {
			for (String set : charSets) {
				if (cn.getBytes(set).length == 3) {
					return set;
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return charSets[2];
	}

	public static String filterMarkdownTag(String text) {
		return text.replaceAll(filterMarkdownTagMatch, "").trim();
	}

	public static <T> String collectionJoin(Collection<T> collection, String separator) {
		if (collection == null || collection.size() == 0) {
			return "";
		}
		if (isEmpty(separator)) {
			separator = ",";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (T t : collection) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(separator);
			}
			sb.append(t);
		}
		return sb.toString();
	}

	public static <T> String arrayJoin(T[] objs, String separator) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		String split = (null == separator) ? "," : separator;
		for (int i = 0; i < objs.length; i++) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(split);
			}
			sb.append(objs[i]);
		}
		return sb.toString();
	}

	public static final boolean isEquals(String s, String t, boolean isIgnoreCase) {
		if (s == null || t == null) {
			return false;
		}
		if (isIgnoreCase) {
			return s.equalsIgnoreCase(t);
		} else {
			return s.equals(t);
		}
	}

	public static String[] toStringArry(Object... object) {
		if (object == null || object.length == 0) {
			return null;
		}
		int len = object.length;
		String[] str = new String[len];
		for (int i = 0; i < len; i++) {
			str[i] = String.valueOf(object[i]);
		}
		return str;
	}

	public static String sha1(String str) {
		if (isEmpty(str)) {
			return "";
		}
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(str.getBytes("UTF-8"));
			Formatter formatter = new Formatter();
			for (byte b : crypt.digest()) {
				formatter.format("%02x", b);
			}
			String result = formatter.toString();
			formatter.close();
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Long stringToLang(String str) {
		if (isEmpty(str)) {
			return null;
		}
		if (isNumeric(str)) {
			return Long.valueOf(str);
		}
		throw new IllegalArgumentException();
	}

	/**
	 * dangdong List<Long> 转List<String>
	 *
	 * @param list
	 * @return
	 */
	public static List<String> listLongToListString(List<Long> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.stream().map(Object::toString).collect(Collectors.toList());
	}

	/**
	 * dangdong Long数组转 List<String>
	 *
	 * @param longArray
	 * @return
	 */
	public static List<String> arrayLongToListString(Long[] longArray) {
		if (longArray == null || longArray.length == 0) {
			return null;
		}
		List<String> stringList = new ArrayList<>();
		int count = longArray.length;
		for (int i = 0; i < count; i++) {
			stringList.add(String.valueOf(longArray[i]));
		}
		return stringList;
	}

	/**
	 * dangdong String数组转List<String>
	 *
	 * @param strArray
	 * @return
	 */
	public static List<String> arrayStringToListString(String[] strArray) {
		if (strArray == null || strArray.length == 0) {
			return null;
		}
		List<String> stringList = new ArrayList<>();
		int count = strArray.length;
		for (int i = 0; i < count; i++) {
			stringList.add(strArray[i]);
		}
		return stringList;
	}

	/**
	 * 长度低于length的高位补0
	 *
	 * @param str
	 * @param length
	 * @return
	 */
	public static String highFill0(String str, int length) {
		int len = str.length();
		if (len >= length) {
			return str;
		} else if (len < length) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < length - len; i++) {
				sb.append('0');
			}
			sb.append(str);
			return sb.toString();
		} else {
			return str;
		}
	}

	/**
	 * 防止xss攻击
	 */
	private static final Pattern PREVENT_XSS_GT = Pattern.compile(">");
	private static final Pattern PREVENT_XSS_LT = Pattern.compile("<");
	private static final String PREVENT_XSS_GT_TO = "_e@gt@e_";
	private static final String PREVENT_XSS_LT_TO = "_e@lt@e_";

	public static final String preventXssAttacks(String htmlStr) {
		if (isBlank(htmlStr)) {
			return htmlStr;
		}
		htmlStr = PREVENT_XSS_GT.matcher(htmlStr).replaceAll(PREVENT_XSS_GT_TO);
		return PREVENT_XSS_LT.matcher(htmlStr).replaceAll(PREVENT_XSS_LT_TO);
	}

	public static final String concat(String... args) {
		StringBuilder sb = new StringBuilder("\n");
		for (String arg : args) {
			if (arg != null) {
				sb.append(arg);
			}
		}
		return sb.toString();
	}

	public static final void concat(StringBuilder sb, String... args) {
		sb.append("\n");
		for (String arg : args) {
			if (arg != null) {
				sb.append(arg);
			}
		}
	}

	/**
	 * 全角转半角
	 * Created by dangdong(mrdangdong@163.com)
	 *
	 * @param input
	 * @return 半角字符串
	 * @DATE:2015-1-19 上午11:48:43
	 */
	public static String toDbc(String input) {

		if (isEmpty(input)) {
			return input;
		}
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);
		return returnString;
	}
}
