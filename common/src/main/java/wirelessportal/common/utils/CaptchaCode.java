package wirelessportal.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class CaptchaCode {
	private String captchaCode;

	/**
	 * 验证码过期时间
	 */
	private long expireTime;

	/**
	 * 验证码被验证次数
	 */
	private int verifyTimes;

	/**
	 * 验证码使用限制次数
	 */
	private int verifyTimesLimit;

	/**
	 * @param captchaCode
	 *            验证码
	 * @param interval
	 *            验证码有效时间，单位：分钟
	 * @param verifyTimesLimit
	 *            验证码使用限制次数
	 */
	public CaptchaCode(String captchaCode, int interval, int verifyTimesLimit) {
		if (StringUtils.isBlank(captchaCode))
			throw new IllegalArgumentException("验证码不能为空");
		else if (interval < 1)
			throw new IllegalArgumentException("验证码有效时间不合法");
		else {
			this.verifyTimesLimit = verifyTimesLimit;
			this.captchaCode = captchaCode;
			this.expireTime = DateUtils.MILLIS_PER_MINUTE * interval
					+ System.currentTimeMillis();
		}
	}

	/**
	 * 验证码是否过期
	 */
	public boolean isExpired() {
		return System.currentTimeMillis() > expireTime;
	}

	/**
	 * 输入的验证码是否正确
	 */
	public boolean verify(String input) {
		if (verifyTimes >= verifyTimesLimit)
			return false;
		else {
			verifyTimes++;
			return captchaCode.equals(input);
		}
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}
}
