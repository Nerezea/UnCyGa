package ucg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContext {

	private static ApplicationContext context;

	@Autowired
	public SpringContext(ApplicationContext context) {
		SpringContext.context = context;
	}

	/**
	 * Returns the Spring managed bean instance of the given class type if it exists.
	 * Returns null otherwise.
	 * @param beanClass
	 * @return
	 */
	public static <T extends Object> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}
}
