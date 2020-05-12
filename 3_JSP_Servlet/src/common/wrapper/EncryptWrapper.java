package common.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
		//매개변수 있는 생성자 무조건 생성
	}
	
	@Override
	public String getParameter(String name) {
		//암호화 된 요청값 받아오는 법
		//회원가입, 로그인, 비밀번호 변경
		//joinUserPwd, userPwd, newPwd
		
		String value = "";
		
		if(name != null && (name.equals("joinUserPwd") || name.equals("userPwd") || name.equals("newPwd"))) {
			value = getSha512(super.getParameter(name));
		} else {
			value = super.getParameter(name);
		}
		
		return value;
	
	}
	
	public static String getSha512(String userPwd) {
		//SHA-512 : 암호화 방식     ==> Bcrypt를 더 선호함
		String encPwd = null;
		
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] bytes = userPwd.getBytes(Charset.forName("UTF-8"));
		md.update(bytes);
		encPwd = Base64.getEncoder().encodeToString(md.digest());
		
		return encPwd;
		
	}

}
