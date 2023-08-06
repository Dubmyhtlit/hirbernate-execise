package app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.emp.entity.Dept;
import web.emp.entity.Emp;
import web.member.entity.Member;

public class TestApp {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Emp emp = session.get(Emp.class, 7369);
		Dept dept = emp.getDept();
		List<Emp> emps = dept.getEmps();
		for (Emp tmp : emps) {
			System.out.println(tmp.getEname());
		}
		
//		Emp emp = session.get(Emp.class, 7369);
//		Dept dept = emp.getDept();
//		System.out.println(dept.getDeptno());
//		System.out.println(dept.getDname());
		
//		Dept dept = session.get(Dept.class, 30);
//		var emps = dept.getEmps();
//		for(var emp : emps) {
//			System.out.println(emp.getEname());
//		}
		
//		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
//		Root<Member> root = criteriaQuery.from(Member.class);
//		
//		criteriaQuery.where(criteriaBuilder.and(
//				criteriaBuilder.equal(root.get("username"), "admin"),
//				criteriaBuilder.equal(root.get("password"), "p@ssw0rd")
//				));
//		
//		criteriaQuery.multiselect(root.get("username"), root.get("nickname"));
//		
//		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
//		
//		Member member = session.createQuery(criteriaQuery).uniqueResult();
//		System.out.println(member.getNickname());
		
//		TestApp app = new TestApp();
////		Member member = new Member();
////		member.setUsername("使用者名稱");
////		member.setPassword("密碼");
////		member.setNickname("暱稱");
////		member.setId(1);
////		member.setPass(false);
////		app.insert(member);
////		System.out.println(member.getId());
////		System.out.println(app.deleteById(3));
////		System.out.println(app.selectById(2).getNickname());		
//		app.selectAll().forEach(member -> System.out.println(member.getNickname()));
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
	
	public List<Member> selectAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<Member> query= session.createQuery(
					"SELECT new web.member.pojo.Member(username, nickname) FROM Member", Member.class);
			List<Member> list = query.getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} 
	}
	
}
