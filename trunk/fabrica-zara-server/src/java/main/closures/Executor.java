package closures;

import org.apache.log4j.Logger;

public class Executor {
	public static Object execute(Closure closure) {
		Object result = null;
		try {
			result = closure.execute();
		} catch (Exception e) {
			Logger logger=Logger.getLogger(Executor.class);
			e.printStackTrace();
			logger.error("Server");
			logger.error(e,e.getCause());
			throw new RuntimeException(e);
		} 
		return result;
	}
}
