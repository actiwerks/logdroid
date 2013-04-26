/**
 * This file is part of the ObjectForms for Android framework and is protected by a copyright.
 * You may not use this file except in compliance with the license, available at 
 *  
 * http://www.objectforms.com/license.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package objectforms.utils;

/**
 * Platform neutral simple logger that can be plugged into native system
 * @author plahoda
 *
 */
public class prn extends SecurityManager implements java.io.Serializable {
	
	static final long serialVersionUID = -8231229825686795970L;
	
	private static LogPrinter printer;
	
	private static boolean skipCallStackCode = false; // don't skip by default
	private static boolean skipLogging = false; 
	
	private static final String NA = "n/a";
	private static final String NA_BUG = "n/a (bug)";
	
	/**
	 * Log the messages using this method
	 * @param text text to log
	 */
	public static void log(String text) {
		if(skipLogging == false) {
			if(printer != null) {
				printer.log(text);
			} else { // last resort call, use System.out, which should always work
				System.out.println(text);
			}
		}
	}
	
	/**
	 * Attach a (platform specific) log printer to be used for logging
	 * @param myPrinter
	 */
	public static void setPrinter(LogPrinter myPrinter) {
		printer = myPrinter;
	}
	
	
	// call stack introspection stuff
	
	/**
	 * Allows to short-circuit the call stack introspection for the production environment
	 * as such introspection has performance implications
	 * @param skipCallStackCode
	 */
	public static void setSkipCallStackCode(boolean skipCallStackCode) {
		prn.skipCallStackCode = skipCallStackCode;
	}
	
	
	/**
	 * Allow to short-circuit the logging at all
	 * Finer levels might be present in LogPrinter implementation
	 * @param skipLogging
	 */
	public static void setSkipLogging(boolean skipLogging) {
		prn.skipLogging = skipLogging;
	}
	
	
	@SuppressWarnings("rawtypes")
	public Class getCallerClass() {
		Class[] context = getClassContext();
		if (context.length > 3) return context[3];
		return context[context.length-1];
	}

	@SuppressWarnings("rawtypes")
	public Class[] getCallerContext() {
		return getClassContext();
	}

	public static StackTraceElement[] getStackTrace(String tag) {
		if(skipCallStackCode == true) {
			return null; // short-circuit for production
		}
		Exception ex = null;
		if(tag != null) {
			ex = new Exception(tag);
		} else {
			ex = new Exception();
		}
		return ex.getStackTrace();
	}
	
	
	public static String st() {
		return st(null);
	}


	public static String st(String tag) {
		if(skipCallStackCode == true) {
			return NA; // short-circuit for production
		}
		StackTraceElement[] ste = getStackTrace(tag);
		StringBuffer sb = new StringBuffer();
		if(ste != null) {
			boolean first = true;
			for(StackTraceElement element : ste) {
				if(prn.class.getName().equals(element.getClassName()) == false) { 
					if(first) {
						first = false; 
					} else {
						sb.append(" | ");
					}
					String name = element.getFileName();
					if(name != null) {
						if(name.endsWith(".java")) {
							name = name.substring(0, name.length()-5);
						}
					} else {
						name ="[?]";
					}
					sb.append(name);
					sb.append(":");
					sb.append(element.getMethodName());
					sb.append(" :");
					sb.append(element.getLineNumber());
				}
			}
		} 
		return sb.toString();
	}
	
	
	/**
	 * Return the caller class simple name
	 * @return
	 */
	public static String cc(int depth) {
		if(skipCallStackCode == true) {
			return NA; // short-circuit for production
		}
		StackTraceElement[] ste = getStackTrace(null);
		int depthCount = 0;
		boolean shallowFlag = true;
		for(StackTraceElement element : ste) {
			if(prn.class.getName().equals(element.getClassName()) == true) {
				// always ignore elements that are above this class in the stack
				shallowFlag = false;
			} else {
				if(shallowFlag == false) {
					if(depthCount >= depth) {
						String name = element.getFileName();
						if(name != null) {
							if(name.endsWith(".java")) {
								name = name.substring(0, name.length()-5);
							}
						} else {
							name ="[?]";
						}
						return name;
					} else {
						depthCount++;
					}
				}
			}
		}
		return NA_BUG;
	}
}



