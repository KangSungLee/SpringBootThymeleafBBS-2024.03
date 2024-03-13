package com.example.abbs.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.abbs.entity.SchDay;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	
	@GetMapping({"/calendar/{arrow}", "/calendar"})
	public String calendar(@PathVariable(required = false) String arrow, HttpSession session, Model model) {
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		String date = "일 월 화 수 목 금 토".split(" ")[today.getDayOfWeek().getValue() % 7];
		String sessionMonthYear = (String) session.getAttribute("scheduleMonthYear");	// 2024.03
		if(sessionMonthYear != null) {
			year = Integer.parseInt(sessionMonthYear.substring(0, 4));
			month = Integer.parseInt(sessionMonthYear.substring(5));
		}
		if (arrow != null) {
			switch(arrow) {
			case "laft":
				month -= 1;
				if(month == 0) {
					year -= 1; month = 12;
				}
				break;
			case "right":
				month += 1;
				if (month == 13) {
					year +=1; month = 1;
				}
				break;
			case "laft2":
				year -= 1; break;
			case "right2":
				year += 1; break;
			}
		}
		sessionMonthYear = String.format("%d.%02d", year, month);
		session.setAttribute("sessionMonthYear", sessionMonthYear);
		String sessUid = (String) session.getAttribute("sessUid");
		
		List<SchDay> week = new ArrayList<>();
		List<List<SchDay>> calendar = new ArrayList<>();
		LocalDate startDay = LocalDate.parse(String.format("%d.%02d-01", year, month));
		int startDate = startDay.getDayOfWeek().getValue() % 7;
		LocalDate lastDay = startDay.withDayOfMonth(startDay.lengthOfMonth());
		int lastDate = lastDay.getDayOfWeek().getValue() % 7;
		
		return "schedule/calendar";
	}
}
