package wcscda;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.ArrayUtils;



/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		Set<Character> characterSet = new TreeSet<Character>(
				Arrays.asList(
						ArrayUtils.toObject(
								"Hello World!".toCharArray()
								)
						)
				);
	}
}
