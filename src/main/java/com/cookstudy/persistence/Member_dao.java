package com.cookstudy.persistence;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.cookstudy.domain.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class Member_dao {
	@Inject
	private SqlSession session;
	private static final String NameSpace = "memberMapper.";
	
	public int Id_Check(Member m) {
		try {
			return session.selectOne(NameSpace + "Id_Check", m);
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int Register(Member m) {
		int Id_Check_Result = Id_Check(m);
		if(Id_Check_Result != 0) return Id_Check_Result;
		
		try {
			session.insert(NameSpace + "Register", m);
			return 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}
	
	public int Login(Member m, HttpSession Hsession) {
		int Id_Search_Result = -1;
		try {
			Id_Search_Result = session.selectOne(NameSpace + "Login", m);
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		if(Id_Search_Result != 1) return Id_Search_Result;
		
		try {
			Member m_info = session.selectOne(NameSpace + "Login_Info", m);
			Hsession.setAttribute("m", m_info);
			return 1;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}
}
