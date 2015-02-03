package com.kaisen.wirelessportal.services;

import static com.kaisen.wirelessportal.WirelessPortalResult.PARAMS_ERROR_RESULT;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.kaisen.finearts.domain.AnswerInfoDO;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.wirelessportal.WirelessPortalResult;

@Controller(value = "answerQuestion")
public class AnswerQuestionService extends BaseService<AnswerInfoDO> {

	@Override
	protected WirelessPortalResult doBusiness(final AnswerInfoDO answerInfoDO) {
		if (StringUtils.isAnyBlank(answerInfoDO.getContent(),
				answerInfoDO.getVoiceUrl())) {
			return PARAMS_ERROR_RESULT;
		}

		UserInfoDO currentUser = super.getUserInfoFromSession();
		answerInfoDO.setAnswerID(currentUser.getId());

		return super.getResult(questionService.answerQuestion(answerInfoDO));
	}

	@Override
	protected AnswerInfoDO parseRequestBody(String requestBody) {
		return JSON.parseObject(requestBody, AnswerInfoDO.class);
	}

	@Override
	public boolean needLogin() {
		return true;
	}
}
