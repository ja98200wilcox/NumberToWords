package com.jwilcox.translator;

/**
 * ApplcationService builds and provides an instance of an Application.
 * @author Jim Wilcox
 *
 */
public class ApplicationService {
	
	public static final ApplicationService INSTANCE = new ApplicationService();
	
	/**
	 * Provides a new instance of an Application
	 * @return a new application instance
	 */
	public Application getApplication() {
		return new Console(new ConvertImpl()); 
	}
}
