package com.example.abbs.util;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:static/data/myKeys.properties")
public class AsideUtil {
	@Value("roadAddrKey") private String roadAddrKey;
	@Value("kakaoApiKey") private String kakaoApiKey;
	@Value("openWeatherApiKey") private String openWeatherApiKey;
	
	public String getTodayQuote(String filename) {
		String result = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename), 1024);
			int index = (int) Math.floor(Math.random() * 100); // 0 ~ 99 // 100줄
			for (int i = 0; i <= index; i++) {
				result = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
