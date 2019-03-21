package com.cookstudy.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.cookstudy.domain.Member;
import com.cookstudy.persistence.Member_dao;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	@Inject
	private Member_dao m_dao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value = "Login", method = RequestMethod.POST)
	public int login(Member m, HttpSession session) {
		return m_dao.Login(m, session);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public int register(Member m) {
		int result = m_dao.Register(m);
		return result;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index() {
		return "home";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("m");
		return "redirect:/";
	}

//	@ControllerAdvice
//	public class JsonpAdviceController extends AbstractJsonpResponseBodyAdvice {
//		public JsonpAdviceController() {
//			super("callback");
//		}
//	}
//
//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		jsonConverter.setObjectMapper(objectMapper);
//		return jsonConverter;
//	}
	
}
