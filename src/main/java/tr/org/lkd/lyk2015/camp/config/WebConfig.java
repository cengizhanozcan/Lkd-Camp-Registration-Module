package tr.org.lkd.lyk2015.camp.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("tr.org.lkd.lyk2015.camp")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean // Beanlar uygulama ilk basladıgında calısır. Bu nesneler Dependcy
			// Pool a atılır.
	@Description("Thymeleaf template resolver serving HTML 5")
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/html/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("LEGACYHTML5");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCacheable(false);

		return templateResolver;
	}

	@Bean
	@Description("Thymeleaf template engine with Spring integration")
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(this.templateResolver());

		return templateEngine;
	}

	@Bean
	@Description("Thymeleaf view resolver")
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(this.templateEngine());
		viewResolver.setContentType("text/html;charset=UTF-8");

		return viewResolver;
	}

	@Bean(name = "dataSource") // low levelda db connectioni konfigure eden
								// nesne.
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql:tododb");
		dataSource.setUsername("todouser");
		dataSource.setPassword("hede");
		return dataSource;
	}

	@Autowired
	@Bean(name = "transactionManager") // transaction menager
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "sessionFactory") // pool da sessionFAcroty adlı birşey varsa
									// otomatik eşleştiriyor.
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

		sessionBuilder.scanPackages("tr.org.lkd.lyk2015.camp"); // bu paketin
																// altında ki
																// entitylere
																// bakıyor.
		sessionBuilder.addProperties(this.getHibernateProperties());

		return sessionBuilder.buildSessionFactory();
	}

	private Properties getHibernateProperties() { // property dosyamız
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.use_sql_comments", "true");
		properties.put("hibernate.enable_lazy_load_no_trans", "true");
		return properties;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TyhmeleaftLayoutIntercector());

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/resources/**").addResourceLocations("/static/");
	}

}
