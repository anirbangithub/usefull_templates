package Ekstep_user_Count.total_count;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



public class ApiCalls {
	static Logger logger ;
	 IniFile configFile ;
	 String strToken;
	
	public ApiCalls( String token, Logger iplogger) throws IOException{
		
		this.strToken = token;
		this.logger = iplogger;
		//strUserToken = strPUserToken;
	}

	public  String doPatch(String strApiUrl, String strApiBody, String strToken) throws Exception {
		logger.info("In Content --> patch --> strApiUrl :: " + strApiUrl);
		logger.info("In Content --> patch --> strApiBody :: " + strApiBody);
		String strResponse = null;

		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();

			String ApiUrl = strApiUrl;
			ApiUrl = ApiUrl.replace("'", "");
			HttpPatch patchReq = new HttpPatch(ApiUrl);
			// HttpPost uploadFile = new HttpPost(ApiUrl);
			patchReq.setEntity(new StringEntity(strApiBody));

			patchReq.setHeader("Authorization", "bearer " + strToken);
			patchReq.setHeader("Content-Type", "application/json");
			/*
			 * uploadFile.setHeader("Content-Type","multipart/form-data" +
			 * " boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
			 */

			HttpResponse response = httpClient.execute(patchReq);
			// HttpResponse response = client.execute(httpGet);
			ResponseHandler<String> handler = new BasicResponseHandler();
			String body = handler.handleResponse(response);
			int code = response.getStatusLine().getStatusCode();
			System.out.println("respose from update content:::" + "res code:::" + code + " res body :::" + body);
			strResponse = body;
		} catch (Exception e) {
			logger.info("In Content --> patch -->  Exception occurred:: " + e.getMessage());
			throw e;
		}
		return strResponse;
	}
	
	public  String doGet(String strApiUrl, String strToken) throws Exception {
		logger.info("In Content --> get --> strApiUrl :: " + strApiUrl);
		//logger.info("In Content --> patch --> strApiBody :: " + strApiBody);
		String strResponse = null;

		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();

			String ApiUrl = strApiUrl;
			ApiUrl = ApiUrl.replace("'", "");
			HttpGet httpGet = new HttpGet(ApiUrl);
			httpGet.setHeader("Authorization", "bearer " + strToken);
			httpGet.setHeader("Content-Type", "application/json");
			HttpResponse response = httpClient.execute(httpGet);
			// HttpResponse response = client.execute(httpGet);
			ResponseHandler<String> handler = new BasicResponseHandler();
			String body = handler.handleResponse(response);
			int code = response.getStatusLine().getStatusCode();
			System.out.println("respose from get request for url:::" +ApiUrl+ " res code:::" + code + " res body :::" + body);
			strResponse = body;
		} catch (Exception e) {
			logger.info("get request-->  Exception occurred for url :: "+strApiUrl +"error message"+ e.getMessage());
			throw e;
		}
		return strResponse;
	}
	public  String doPost(String strApiUrl, String strApiBody, String userToken) throws Exception
	{

		logger.info("In Content --> post --> strApiUrl :: " + strApiUrl);
		logger.info("In Content --> post --> strApiBody :: " + strApiBody);
		String strResponse;

		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();

			String ApiUrl = strApiUrl;
			ApiUrl = ApiUrl.replace("'", "");
			HttpPost postReq = new HttpPost(ApiUrl);
			// HttpPost uploadFile = new HttpPost(ApiUrl);
			postReq.setEntity(new StringEntity(strApiBody));

			postReq.setHeader("Authorization", "Bearer " + strToken);
			postReq.setHeader("Content-Type", "application/json");
			postReq.setHeader("x-authenticated-user-token", userToken);
			/*
			 * uploadFile.setHeader("Content-Type","multipart/form-data" +
			 * " boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
			 */

			HttpResponse response = httpClient.execute(postReq);
			// HttpResponse response = client.execute(httpGet);
			ResponseHandler<String> handler = new BasicResponseHandler();
			String body = handler.handleResponse(response);
			int code = response.getStatusLine().getStatusCode();
			System.out.println("respose from update content:::" + "res code:::" + code + " res body :::" + body);
			strResponse = body;
		} catch (Exception e) {
			logger.info("In Content --> post -->  Exception occurred:: " + e.getMessage());
			throw e;
		}
		return strResponse;
	
	}
}