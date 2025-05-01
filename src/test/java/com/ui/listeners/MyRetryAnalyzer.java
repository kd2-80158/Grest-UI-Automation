package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {

	//private static final int MAX_ATTEMPT = Integer.parseInt(PropertiesUtil.readProperty(Env.DEV,"MAX_ATTEMPT"));
	private static final int MAX_ATTEMPT = JSONUtility.readJSONProperty(Env.DEV).getMAX_ATTEMPT();
	private static int current_attempt = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (current_attempt <= MAX_ATTEMPT) {
			current_attempt++;
			return true;
		} else
			return false;
	}

}
