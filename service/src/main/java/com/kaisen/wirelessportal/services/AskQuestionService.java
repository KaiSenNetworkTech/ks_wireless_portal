package com.kaisen.wirelessportal.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.kaisen.finearts.domain.QuestionInfoDO;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.wirelessportal.WirelessPortalResult;

import static com.kaisen.wirelessportal.WirelessPortalResult.PARAMS_ERROR_RESULT;

@Controller(value = "askQuestion")
public class AskQuestionService extends BaseService<QuestionInfoDO> {

	@Override
	protected WirelessPortalResult doBusiness(
			final QuestionInfoDO questionInfoDO) {
		if (StringUtils.isAnyBlank(questionInfoDO.getContent(),
				questionInfoDO.getImgUrl())) {
			return PARAMS_ERROR_RESULT;
		}

		UserInfoDO currentUser = super.getUserInfoFromSession();

		questionInfoDO.setQuestionerID(currentUser.getId());
		questionInfoDO.setQuestionStatus(QuestionInfoDO.STATUS_NO_ANSWER);

		return super.getResult(questionService.askQuestion(questionInfoDO));
	}

	@Override
	protected QuestionInfoDO parseRequestBody(String requestBody) {
		return JSON.parseObject(requestBody, QuestionInfoDO.class);
	}

	@Override
	public boolean needLogin() {
		return true;
	}
}
