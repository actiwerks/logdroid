package objectforms.utils;

/**
 * Definition of the LogPrinter interface, used to plug native logging system into platform neutral API
 * @author plahoda
 */
public interface LogPrinter {
	public void log(String text);
}
