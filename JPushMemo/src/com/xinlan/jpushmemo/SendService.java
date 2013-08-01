package com.xinlan.jpushmemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cn.jpush.api.ErrorCodeEnum;
import cn.jpush.api.IOSExtra;
import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

public class SendService {
	public static final String appKey ="eedb551e1fc441e9d4571abd";	
	public static final String masterSecret =	"fcc25dd96e60b626f4ddda6b";
	
	public String send(String content){
		JPushClient jpush =  new JPushClient(masterSecret,appKey);
		String msgTitle = "����";
		String msgContent = content;
		String tag = "tag";
		//�Զ�����Ϣ android/ios
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hey", "test");
		map.put("you", "test");
		
		MessageResult msgResult = jpush.sendNotificationWithAppKey(new Random().nextInt(),msgTitle,msgContent,0,map,null);

		if (null != msgResult) {
//			System.out.println("��������������: " + msgResult.toString());
			if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
				return "���ͳɹ��� sendNo=" + msgResult.getSendno();
			} else {
				return "����ʧ�ܣ� �������=" + msgResult.getErrcode() + ", ������Ϣ=" + msgResult.getErrmsg();
			}
		} else {
			return "�޷���ȡ���ݣ���������Ƿ�ʱ";
		}
	}
}//end class
