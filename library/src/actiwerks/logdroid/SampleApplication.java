package actiwerks.logdroid;

import objectforms.utils.prn;

/**
 * Application object to be used as the basis for ObjectForms applications
 * Its use is not mandatory, but in such case static method init() must be called
 * with the actual Application as a parameter
 * @author plahoda
 */
public class SampleApplication extends android.app.Application {
	
	private static android.app.Application instance;
	
	private static boolean inited = false;
	
	public SampleApplication() {
		super();
		init(this);
	}
	
	
	/**
	 * Init various components of the ObjectForms framework
	 * If you don't use this class as your Application, make sure this method gets called
	 */
	public static void init(android.app.Application application) {
		if(inited == false) {
			inited = true;
			instance = application;
			prn.setPrinter(new LogPlug());
			prn.log("App init called");
		}
	}
	
	
	/**
	 * Handy static shortcut to Application instance, mainly to get Application Context out of it
	 * You may not use this class as your Application object for your application, but make sure 
	 * that you call init method from your own application
	 * @return
	 */
	public static android.app.Application get() {
		if(instance == null) {
			throw new IllegalStateException("Application context holder not set. Either use actiwerks.logdroid.Application as your Application object or call init() method and pass your Application object as an argument.");
		}
		return instance;
	}
	
}
