/**
 * 
 */
package com.cainew.enums;



/**
 * @author jx
 * @date 2018-1-26
 * 
 */
public enum BusinessExceptionEnum {
	
	NOT_FIND_PAGE(404,"not.find.page","该网页无法打开")
	, USER_LOGIN_NO(1000, "user.login.no", "你还没有登录哦")
	, USER_NO(1001, "user.no", "参数为空")
	, USER_PASSWORD_login(1005,"user.password.login", "哎呀，手机号或密码输错了...")
	, USER_PHONE_LOGIN(1010,"user.phone.login", "错了，手机号码错了~！")
	, USER_USERNAME_REG(1015,"user.username", "要输入中文、英文以及数字，但是字符不可以超过20个哦")
	, USER_PASSWORD_REG(1020,"user.password.reg", "请输入6~16位的密码，必须要用英文+数字+符号的高大上密码")
	, USER_REG_SAVE_ERROR(1023,"user.reg.save.error", "注册用户失败")
	, USER_LOGIN_ERR(1025,"user.login.err", "哎呀，手机号或密码输错了...")
	, USER_CODE_ERROR(1026,"user.code.error","这个短信验证码不对呢")
	, USER_EXIST_ERROR(1027,"user.exist.error","真是抱歉啊，这个号码已经被人注册了")
	, USER_PHONE_NULL(1029,"user.phone.null","哈哈~这个号码还没有被注册~")
	, USER_LOGIN_SESSION_OUT(1028, "user.login.session.out", "超时了~失效啦~更新登陆吧")
	, USER_ACCOUNT_NULL(1030, "user.account.null", "真是抱歉，这个资金账户不存在啊...")
	, USER_PHONE_FORMAT_ERROR(1031, "user.phone.format.error", "你输错手机号码了诶...")
	, USER_PASSWORD_OLD_ERROR(1032, "user.password.old.error", "您输入的原密码有误...")
	, USER_ACCOUNT_USE_MONEY_INSUFFICIENT(1035, "user.account.use.money.insufficient", "余额不够了呀~！")
	, USER_ACCOUNT_TRANSFER_DEBT_ADD_ERROR(1040,"user.account.transfer.debt.add.error", "这个...转让债权金额添加失败了...")
	, USER_ACCOUNT_TRANSFER_DEBT_UPDATE_COLLECTION_ERROR(1045, "user.account.transfer.debt.update.collection.error",
			"不好意思，这次利差更新失败了，唉")
	, USER_PAY_PASSWORD_ERROR(1050,"user.pay.password.error", "咳咳，你输错支付密码了...")
	, USER_NO_LEGAL(1055,"user.no.legal", "怎么办？这个账号有违规操作！")
	, USER_NULL(1060, "user.null", "唉，真是抱歉，没有找到这个账号诶，我想大概是不存在吧")
	,USER_ID_ERROR(1061,"user.id.error","唉，真是抱歉，你的用户ID格式不正确")
	, USER_UPDATE_ERROR(1062,"user.update.error","Sorry,数据更新失败了")
	, USER_ACCOUNT_UPDATE_ERROR(1065, "user.account.update.error", "不好意思，账户数据更新...没有成功诶")
	, USER_ACCOUNT_ABNORMITY(1066,"user.account.abnormity","用户账户出现异常，请联系客服进行查看")
	, TRANSFER_DEBT_NULL(2000, "transfer.debt.null", "找不到这个债权信息啊~不存在~！")
	, TRANSFER_DEBT_INVEST_LESS(2005, "transfer.debt.invest.less", "哎呀，债权转让的投资金额小于起投金额了，请您增加投资金额吧~")
	, TRANSFER_DEBT_INVEST_GREATER(2010, "transfer.debt.invest.greater", "哇，您的债权转让投资金额太大了呀，超过最大限度了啦~！")
	, TRANSFER_DEBT_INVEST_EXCEED(2015, "transfer.debt.invest.exceed", "这个，这个，债权转让投资金额已经超过最大可投金额了呢...")
	, TRANSFER_DEBT_ID_ERROR(2020, "transfer.debt.id.error", "有误有误！债权转让账号有误！")
	, TRANSFER_DEBT_STATUS_UNDERWAY_ERROR(2025, "transfer.debt.status.underway.error", "啊呀，债权转让没有成功...")
	, TRANSFER_DEBT_UPDTE_ERROR(2030, "transfer.debt.update.error", "啊呀，债权更新没有成功...")
	, TRANSFER_DEBT_ADD_DEBT_TENDER_ERROR(2035, "transfer.debt.add.debt.tender.error", "实在是不好意思，债权投资没有添加成功...呵呵")
	, TRANSFER_DEBT_INVEST_LOG_UPDATE_PAY_STATUS_ERROR(2040,
			"transfer.debt.invest.log.update.pay_status.error", "我只能告诉你，这个处理状态数据的更新啊，没有成功诶")
	, TRANSFER_DEBT_INVEST_LAST_ERROR(2045,"transfer.debt.invest.last.error","注意！注意！最后一批的投资金额必须要和可投金额的大小一致哦")
	, BORROW_TENDER_NULL(3000, "borrow.tender.null", "找不到啦~没有这条投资信息")
	, BORROW_TENDER_ID_ERROR(3005, "borrow.tender.id.error", "投资ID账号有误，有误，有误...")
	, BORROW_TENDER_STOP_NOT_TRANSFER_ERROR(3010, "borrow.tender.stop.not.transfer.error", "这笔投资还没有发起转让诶,你不能进行暂停的操作哦~")
	, BORROW_TENDER_STOP_FINISH_TRANSFER_ERROR(3015, "borrow.tender.stop.finish.transfer.error", "抱歉啦，投资已经完成转让了，不可以暂停咯")
	, BORROW_TENDER_STOP_CANCEL_TRANSFER_ERROR(3020, "borrow.tender.stop.cancel.transfer.error", "好了好了，投资转让已经暂停了")
	, BORROW_TENDER_STOP_UPDATE_ERROR(3025, "borrow.tender.stop.update.error", "唉，投资暂停更新没有成功")
	, BORROW_TENDER_START_UNDERWAY_TRANSFER_ERROR(3030,"borrow.tender.start.underway.transfer.error", "投资已经在转让中了，转让ing~")
	, BORROW_TENDER_START_FINISH_TRANSFER_ERROR(3035, "borrow.tender.start.finish.transfer.error", "投资转让，已完成~！")
	, BORROW_TENDER_START_UPDATE_ERROR(3040, "borrow_tender.start.update.error", "投资转让数据更新失败了...")
	, BORROW_TENDER_NO_PUBLISH(3055, "borrow.tender.no.publish", "这笔债权还没有被发布啦")
	, BORROW_TENDER_SAVE_ERROR(3040,"borrow.tender.save.error","失败了...散标投资记录添加没有成功诶")
	, BORROW_UPDATE_ERROR(3045,"borrow.update.error","不对不对，散标更新出现错误了")
	, BORROW_TENDER_RED_PAPER_USED_ERROR(3050,"borrow.tender.redpaper.used.error", "不得不告诉你，这个散标投资内的使用红包没有被激活诶，唉。")
	, BORROW_NULL(4000, "borrow.null", "抱歉，找不到这个散标信息啦")
	, BORROW_INVEST_GREATER(4050, "borrow.invest.greater", "一不小心金额大小超出可投最大限度了")
	, BORROW_INVEST_MIN_ERROR(4055,"borrow.invest.min.error","真是抱歉，金额大小低于起投金额诶")
	, BORROW_INVEST_LAST_ERROR(4060,"borrow.invest.last.error","看过来看过来：最后一批投资金额必须与可投金额大小一致哦~")
	, FINANCING_NULL(5000, "financing.null", "呵呵，这个理财信息不存在啦")
	, FINANCING_INVEST_LESS(5005, "financing.invest.less", "投资金额小于起投金额了，增加金额吧~")
	,FINANCING_INVEST_FINISH(5008,"financing.invest.finish","耶，已经被投完了~")
	, FINANCING_INVEST_EXCEED(5010, "financing.invest.exceed", "哇哦，投资金额已经超过可投金额最大限度了呢")
	, FINANCING_INVEST_LAST_ERROR(5013,"financing.invest.last.error","请注意哦，最后一批投资金额的大小必须要与可投金额大小一致")
	, FINANCING_UPDATE_ERROR(5015, "financing.update.error", "抱歉，理财数据更新失败啦啦啦")
	, FINANCING_TENDER_SAVE_ERROR(5020, "financing.tender.save.error", "Sorry，理财投资添加失败了诶")
	, FINANCING_STATUS_NOT_BUY_ERROR(5025, "financing.status.not_buy.error","有可能是这个理财状态发生错误了！也有可能是这笔理财还没有被发布呢")
	, FINANCING_TENDER_NULL(5030, "financing.tender.null", "没有发现这个理财投资的存在...")
	, FINANCING_TENDER_UPDATE_ERROR(5035, "financing.tender.update.error", "失败了，理财投资数据更新失败了")
	, FINANCING_CLOSE_FLAG_NOT(5040, "financing.close.flag.not", "当前的理财产品不可以提前退出呀！")
	, FINANCING_TENDER_TRANSFER_FINISH(5045, "financing.tender.transfer.finish", "耶，已经完成了~")
	, FINANCING_CLOSE_FLAG_DEBT_ERROR(5050, "financing.close.flag.debt.error", "当前理财产品不可以进行债权转让呢")
	, FINANCING_TENDER_TRANSFER_UNDERWAY_ERROR(5055, "financing.tender.underway.error", "正在进行转让~")
	, FINANCING_TENDER_RED_PAPER_USED_ERROR(5060,"financing.tender.redpaper.used.error", "糟糕，理财投资中的使用红包没有激活成功...")
	, FINANCING_TENDER_NEW_USER_FLAG_ERROR(5065,"financing.tender.new.user.flag.error","只有还没有投资过的用户才可以进行哦~")
	, FINANCING_TENDER_NEW_USER_MAX_MONEY_ERROR(5065,"financing.tender.new.user.max.money.error","啊~！已经超出投资最大限度了~")
	, FINANCING_TENDER_PAY_FINISH(5066,"financing.tender.pay.finish","已经还款了哦")
	, ENCODE_ERROR(6000, "encode.error", "转换编码错误")
	, BEAN_TO_MAP_ERROR(6001, "bean.to.map.error", "实体类转换成map出错")
	
	, ACCOUNT_UPDATE_ERROR(6030,"account.update.error","不好意思啦，账户数据更新失败了...")
	, ACCOUNT_BANK_EXIST(6031,"account.bank.exist","不好意思啦，银行卡已经被绑定...")
	
	, BANK_NULL(6050,"bank.null","您还未绑定银行卡")
	, BANK_NOT_EXIST(6051,"bank.not.exist","抱歉，这个银行暂时还不在咱们系统里，需要找客服修改绑定银行哦")
	, BANK_IS_CREDIT_CARD(6052, "bank.is.credit.card", "不好意思啊，暂时还不支持信用卡绑定")
	, BANK_CARD_IS_ERROR(6053,"bank.card.is.error","请准确输入自己的银行卡号，认真检查，不要输错了~")
	
	, RECHARGE_STATUS_ERROR(6070,"recharge.status.error","不对哦，充值状态不对哦")
	, RECHARGE_IS_NONE(6071,"recharge.is.none","我怎么也没有找到这条充值记录，我想它应该是不存在吧")
	, RECHARGE_LOG_INSERT_ERROR(6072,"recharge.log.insert.error","抱歉啦，充值记录插入失败了呢")
	, RECHARGE_ZERO(6073,"recharge.zero","充值金额必须大于0")
	, RECHARGE_UNKNOW_ERROR(6074,"recharge.unknow.error","充值发送了未知的错误")
	, DEDUCT_ZERO(6075,"deduct.zero","扣款金额必须大于0")
	
	
	, SIGN_ERROR(7000, "sign.error","可能是您在加密认证签名的时候有误呢")
	, REAL_APPROVE_ERROR(7010,"real.approve.error","唉，实名认证失败了诶，要不再试一次？")
	, REAL_APPROVE_EXIST(7011,"real.approve.exist","这个身份信息已经绑定过了，不可以再绑定了哦")
	, REAL_APPROVE_IDCARD_ERROR(7012,"real.approve.idcard.error","身份证信息填写有误，请重新填写！")
	, REAL_APPROVE_NAME_ERROR(7013,"real.approve.name.error","童鞋，你的这个真实姓名也太长了吧~！")		
	, REAL_APPROVE_NO(7014,"real.approve.no","告诉我为什么不实名认证？")
	
	
	, TRY_COUPONS_TEMPLATE_NULL(8000,"try.coupons.template.null","没有这个数据！也有可能是这个数据被禁用了...")	
	,TRY_COUPONS_NULL(8050,"try.coupons.null","你还没有体验金券哦~")
	,TRY_COUPONS_WITHDRAW_FLAG_YES(8055,"try.coupons.withdraw_flag_yes","好了好了，已经提利息到余额里了~")
	,TRY_COUPONS_NO_USED(8060,"try.coupons.no.used","还没有被使用诶")
	,TRY_COUPONS_YES_USED(8065,"try.coupons.yes.used","已经使用过了~")
	,TRY_COUPONS_YES_PAST(8066,"try.coupons.yes.past","糟糕，已经过期了诶...")
	,TRY_COUPONS_NO_EXPIRE(8067,"try.coupons.no.expire","投资还没有到期，再等等吧")	
	,TRY_COUPONS_UPDATE_ERROR(8070,"try.coupons.update.error","哎呀，更新体验金券失败了呢")
	
	,SMS_PHONE_SESSION_NULL(9000,"sms.phon.session.null","短信验证会话是空的诶")
	,SMS_TIMEOUT_NULL(9005,"sms.timeout.null","短信验证时间是空的诶")
	,SMS_PHONE_SESSION_EQ_ERROR(9010,"sms.phone.session.eq.error","错了错了，验证手机有误！")
	,SMS_TIME_OFTEN_ERROR(9015,"sms.time.often.error","淡定，操作太频繁了,耐心等一会儿吧！")
	,SMS_TIME_OUT_ERROR(9020,"sms.time.out.error","哎呀呀，这条短信验证过期了诶...")
	//自定义常亮
	,login_IMAGECODE_NULL(9021,"imageCode.is.null","图片验证码不能为空")
	,login_IMAGECODE_ERROR(9022,"imageCode.is.error","图片验证码错误")
	
	
	, SYSTEM_REQUEST_ERROR(9999,"SYSTEM_REQUEST_ERROR","接口请求失败，请稍后再试")
	,WALLET_CURRENT_FINANCING_MAX_MONEY_ERROR(10000,"wallet.current.financing.max.money.error","超出可投金额")
	,WALLET_CURRENT_FINANCING_LIMIT_MONEY_ERROR(10005,"wallet.current.financing.limit.money.error","超出每日投资上限")
	,WALLET_CURRENT_FINANCING_MIN_MONEY_ERROR(10007,"wallet.current.financing.min.money.error","投资金额低于起投金额")
	,WALLET_CURRENT_FINANCING_LAST_MONEY_ERROR(10008,"wallet.current.financing.last.money.error","投资金额必须等于可投金额")
	,WALLET_CURRENT_FINANCING_LOG_SAVE_ERROR(10010,"wallet.current.financing.log.save.error","添加活期理财日志异常")
	,WALLET_USER_CURRENT_FINANCING_DETAIL_SAVE_ERROR(10015,"wallet.user.current.financing.detail.save.error","添加用户活期详情异常")
	,WALLET_USER_CURRENT_FINANCING_DETAIL_UPDATE_ERROR(10015,"wallet.user.current.financing.detail.update.error","更新用户活期详情异常")
	,WALLET_USER_CURRENT_FINANCING_UPDATE_ERROR(10020,"wallet.user.current.financing.update.error","更新活期理财异常")
	,WALLET_USER_CURRENT_FINANCING_DETAIL_OUT_MAX_MONEY_ERROR(10025,"wallet.user.current.financing.detail.out.max.money.error","超出活期理财总额")
	,WALLET_USER_DAY_EARNINGS_SAVE_ERROR(10030,"wallet.user.day.earnings.save.error","添加用户活期理财收益异常")
	,WALLET_USER_CURRENT_FINANCING_LOG_UPDATE_FLAG_ERROR(10035,"wallet.user.current.financing.log.update.flag.error","更新用户投资活期理财收益标识异常")
	,WALLET_USER_CURRENT_FINANCING_NULL(10040,"wallet.user.current.financing.null","无当前用户活期理财")
	,WALLET_CURRENT_FINANCING_FINISH_ERROR(10045,"wallet.current.financing.finish.error","活期理财已满额")
	
	,JSONOBJECT_GET_ERROR(10100,"jsonobject.get.error","获取的json对象不存在")
	
	, WX_ALREADY_SHARE(10150,"wx.already.share","已经获取分享奖励")
	
	, OAUTH_ERROR(10160,"oauth.error","注册成功，第三方登陆账号绑定失败")
	, WECHAT_OPENID_NULL(10161,"wechat.openid.null","公众号登陆时没有获取到用户唯一ID")
	, WECHAT_BINDING_ERROR(10162,"wechat.binding.error","公众号登陆绑定失败")
	, EMP_SESSION_OUT(10200,"emp.session.out","管理员登陆超时，请重新登陆")
	
	, SYSTEM_PARAM_NULL(10300,"system.param.null","系统参数不存在")
	, CASH_WAY_ERROR(10350,"cash.way.error","提现方式不存在")
	, CASH_ZERO(10351,"cash.zero","提现金额不能为0")
	, CASH_USE_MONEY_LOW(10352,"cash.use.money.low","可用余额不足，无法提现")
	, LIANPAY_BRANCH_ERROR(10360,"lianpay.branch.error","支行为空或支行信息有误，无法提现")
	, LIANPAY_BRANCH_CITY_ERROR(10361,"lianpay.branch.city.error","支行地区信息有误")
	, RECOMMEND_USER_NULL(10400,"recommend.user.null","推荐人不存在用户表中")
	
	;
	private int code;
	private String key;
	private String message;

	BusinessExceptionEnum(int code, String key, String message) {
		this.code = code;
		this.key = key;
		this.message = message;

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static String getMessageFromCode(int code) {

		for (BusinessExceptionEnum business : BusinessExceptionEnum.values()) {
			if (code == business.getCode())
				return business.getMessage();
		}

		return "";

	}

	public static BusinessExceptionEnum getBusiness(int code) {
		for (BusinessExceptionEnum business : BusinessExceptionEnum.values()) {
			if (code == business.getCode())
				return business;
		}
		return null;
	}

	public static BusinessExceptionEnum getBusiness(String key) {
		for (BusinessExceptionEnum business : BusinessExceptionEnum.values()) {
			if (key == business.getKey())
				return business;
		}
		return null;
	}

}
