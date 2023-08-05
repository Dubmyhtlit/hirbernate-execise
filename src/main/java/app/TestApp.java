package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import core.util.HibernateUtil;
import web.member.pojo.Member;

public class TestApp {
	
	public static void main(String[] args) {
		TestApp app = new TestApp();
//		Member member = new Member();
//		member.setUsername("使用者名稱");
//		member.setPassword("密碼");
//		member.setNickname("暱稱");
//		member.setId(1);
//		member.setPass(false);
//		app.insert(member);
//		System.out.println(member.getId());
//		System.out.println(app.deleteById(3));
		System.out.println(app.selectById(2).getNickname());		
	}
	
	public Integer insert(Member member) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(member);
			transaction.commit();
			return member.getId();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} 
	}
	
	public int deleteById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);
			session.remove(member);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		} 
	}
	
	public int updataById(Member newMember) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member oldmember = session.get(Member.class, newMember.getId()	);
			
			final Boolean pass = newMember.getPass();
			if (pass != null) {
				oldmember.setPass(pass);
			}
			
			final Integer roleId = newMember.getRoleId();
			if (roleId != null) {
				oldmember.setRoleId(roleId);
			}
			
			
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		} 
	}
	
	public Member selectById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);			
			transaction.commit();
			return member;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} 
	}
	
	
	
}
