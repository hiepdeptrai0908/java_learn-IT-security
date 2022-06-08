package com.hiep.message;

import java.util.ArrayList;


public  class   ErMessage {
	/**
	 ErEmail :  メールの定型フォーマットチェック
	 */
	public final static  String erEmailHs = "メールの定型フォーマットが正しくない！";
	
	/**
	 erPwHs :  パスワードの定型フォーマットチェック
	 */
	public final static String erPwHs = "パスワードの定型フォーマットが正しくない！";
	
	/**
	 erPwLen : 大文字と小文字と数字チェック
	 */
	public final static  String erPwLen = "8桁以上の大文字と小文字と数字を入力してください！";
	/**
	 ErEmail :  DB存在チェック
	 */
	public final static  String erEmailDbCheck = "このメールアドレスはすでに使いました！";

	
		
	public ArrayList<String> err = new ArrayList<String>();
	


	
	
		
	
	
	}