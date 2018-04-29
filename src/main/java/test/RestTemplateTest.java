package test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import net.sf.json.JSONObject;

public class RestTemplateTest {
	
	 static SimpleClientHttpRequestFactory factory;
	 static String  jsessionid = "2253E98C0A38D3BEC6F617C44E967990";
	 
	 static String  token = "545741b692668b95c7d33a0e9b41830a";
	 

	public static void main(String[] args) {
	factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(20000);
		
		
		
	
		
/*	JSONObject sms = post("http://m.0791jr.com/sapi/login/sms.do?phone=18279915667&debug=true");
	ref(sms);
	String vcode= sms.getString("msg");
	p(sms);
	
	JSONObject login = post( "http://m.0791jr.com/sapi/login.do?account=18279915669&type=sms&vcode="+vcode);
	ref(login);
	p(login);*/
	
	JSONObject user = post( "http://m.0791jr.com/sapi/user.do");
	
	p(user);
	System.out.println(user);
	
	
	
	
	}
	
	
	static void p(JSONObject o){
		System.out.println(o);
		System.out.println(jsessionid);
		System.out.println(token);
		
		
	}
	
	static void ref(JSONObject o){
		//if(!"null".equals(o.getString("jsessionid")))
			jsessionid = o.getString("jsessionid");
		
			
			String mtoken = o.getString("token");
		if(!"null".equals(mtoken))
			token = o.getString("token");
		
	}
	
	
	
	private static JSONObject post(String url) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(factory);
		
		  HttpHeaders requestHeaders = new HttpHeaders();
		  String cookie = "";
		  if(jsessionid!=null)
			  cookie+="JSESSIONID="+jsessionid+";";
		  if(token!=null)
			  cookie+="token="+token+";";
	        requestHeaders.add("Cookie", cookie);
	       
		
	        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
	        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		
		
		
		return JSONObject.fromObject(response.getBody());
		
		

	}
	
	
}
