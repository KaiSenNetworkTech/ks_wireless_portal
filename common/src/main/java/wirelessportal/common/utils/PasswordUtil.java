package wirelessportal.common.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class PasswordUtil {
	private static final Pattern PASSWORD_FORMAT_PATTERN = Pattern
			.compile("[0-9a-zA-Z_]{6,12}");

	public static boolean passwordFormatCheck(String password) {
		return StringUtils.isBlank(password) ? false : PASSWORD_FORMAT_PATTERN
				.matcher(password).matches();
	}
}
