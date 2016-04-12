package org.fulltest.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.fulltest.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Entity
@Table(name = "user_record")
public class User {
	@Id
	@GeneratedValue(generator = "user_record_gen")
	@SequenceGenerator(name = "user_record_gen", sequenceName = "user_record_seq")
	private long id;
	private String username;
	private String password;
	
	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static User createUser(String username, String password) {
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		
		u.save();
		
		return u;
	}
	
	public static User getUser(String username) {
		try(Session sess = SessionUtil.getUtil().getSession()) {
			List users = sess.createQuery("from User as user where user.username = :username")
					.setString("username", username).list();
			if (users.size() > 0) {
				return (User) users.get(0);
			} 
			return null;
		}
	}
	
	private void save() {
		Session sess = SessionUtil.getUtil().getSession();
		Transaction t = sess.beginTransaction();
		
		sess.persist(this);
		
		t.commit();
		sess.close();
	}
}
