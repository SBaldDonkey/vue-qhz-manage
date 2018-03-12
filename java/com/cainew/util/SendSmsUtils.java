package com.cainew.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.cainew.vo.SmsSendRequest;
import com.cainew.vo.SmsSendResponse;

/**
 * @Package com.cainew.service.manager.impl
 * @Description: 发送短信的工具类
 * @author: Mr.D
 * @date: 2018年1月25日 上午11:02:11
 * @version V1.0
 */
public class SendSmsUtils {

	public static final String charset = "utf-8";
	// 用户平台API账号(非登录账号,示例:N1234567)
	public static String account = "N6154700";
	// 用户平台API密码(非登录密码)
	public static String pswd = "RZ0P23IVdC7133";

	public static void sendSms(String phoneNum, String message) {
		String COMPANY_SMS_SIGN = "【" + CommonGlobalConfig.SMS_CONTENT + "】";
		// 请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
		String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
		// 短信内容 253云通讯 (int) ((Math.random() * 9 + 1) * 100000) 你好,你的验证码是:
		String msg = COMPANY_SMS_SIGN + message;
		// 手机号码
		String phone = phoneNum;
		// 状态报告
		String report = "true";

		SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone, report);

		String requestJson = JSON.toJSONString(smsSingleRequest);

		System.out.println("before request string is: " + requestJson);

		String response = sendSmsByPost(smsSingleRequestServerUrl, requestJson);

		System.out.println("response after request result is :" + response);

		SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

		System.out.println("response  toString is :" + smsSingleResponse);
	}

	public static String sendSmsByPost(String path, String postContent) {
		URL url = null;
		try {
			url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");// 提交模式
			httpURLConnection.setConnectTimeout(10000);// 连接超时 单位毫秒
			httpURLConnection.setReadTimeout(10000);// 读取超时 单位毫秒
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");

			httpURLConnection.connect();
			OutputStream os = httpURLConnection.getOutputStream();
			os.write(postContent.getBytes("UTF-8"));
			os.flush();

			StringBuilder sb = new StringBuilder();
			int httpRspCode = httpURLConnection.getResponseCode();
			if (httpRspCode == HttpURLConnection.HTTP_OK) {
				// 开始获取数据
				BufferedReader br = new BufferedReader(
						new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				return sb.toString();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		sendSms("13026424095", "工商银行");
	}

}
