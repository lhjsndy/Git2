package com.offcn.soap;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TestClient {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost post = new HttpPost("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx");
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append(
				"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/>");
		sb.append("<soap:Body>");
		sb.append("<getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">");
		sb.append("<mobileCode>15510712426</mobileCode>");
		sb.append("<userID></userID>");
		sb.append("</getMobileCodeInfo>");
		sb.append("</soap:Body>");
		sb.append("</soap:Envelope>");
		
		StringEntity entity = new StringEntity(sb.toString(),"UTF-8");
		post.setEntity(entity);
		post.setHeader("Content-Type", "application/soap+xml; charset=utf-8");
		post.setHeader("SOAPAction", "http://WebXml.com.cn/getMobileCodeInfo");
		HttpResponse response = client.execute(post);
		HttpEntity e = response.getEntity();
		String num = e.toString();
		System.out.println(num);
		client.close();
		
	}
}
