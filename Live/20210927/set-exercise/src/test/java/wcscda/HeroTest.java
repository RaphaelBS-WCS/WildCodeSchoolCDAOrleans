package wcscda;

import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;

public class HeroTest extends TestCase {
	public void testApp()
    {
        // 3 heroes should be able to fight Thanos
		Set<Hero> heroSet = new TreeSet<Hero>();
		
		heroSet.add(new Hero("Thor", 1500));
		heroSet.add(new Hero("IronMan", 40));
		heroSet.add(new Hero("Captain America", 40));
		
		// Oups pourquoi ???
		assertEquals(heroSet.size(), 3);
    }
}
