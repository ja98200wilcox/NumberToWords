package com.jwilcox.translator;
/**
 * Entry Point into the application
 * @author Jim Wilcox
 *
 */
public class Main 
{
	/**
	 * The main method that starts everything up.
	 * @param args arguments from the command line
	 */
    public static void main( String[] args )
    {
    	try{
    		ApplicationService.INSTANCE.getApplication().start();
		}catch (Exception e) {
			System.out.println("Fatal Error : " + e.toString());
			System.exit(1);
		}	
		System.exit(0);
    }
}
