package core.listnener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static core.util.HibernateUtil.*;

@WebListener
public class HibernateLinstener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		getSessionFactory();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		shutdown();
	}
}
