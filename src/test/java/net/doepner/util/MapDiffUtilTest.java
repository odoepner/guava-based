package net.doepner.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TODO: Document this
 */
public class MapDiffUtilTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Rule
    public TestName testName = new TestName();

    @Before
    public void logTestName() {
        log.info("Executing {}", testName.getMethodName());
    }

    @Test
    public void testEqual() {
        final Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("A", 1);
        map1.put("B", 2);

        final Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("B", 2);
        map2.put("A", 1);

        assertTrue("Maps should be equal", MapDiffUtil.validateEqual(
            map1, map2, "map1", "map2"));
    }

    @Test
    public void testSubset() {
        final Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("A", 1);

        final Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("B", 2);
        map2.put("A", 1);

        assertFalse("Maps should be unequal", MapDiffUtil.validateEqual(
            map1, map2, "map1", "map2"));

    }

    @Test
    public void testSeparate() {
        final Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("A", 1);

        final Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("B", 2);

        assertFalse("Maps should be unequal", MapDiffUtil.validateEqual(
            map1, map2, "map1", "map2"));
    }

    @Test
    public void testMismatches() {
        final Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("A", 1);
        map1.put("B", 2);

        final Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("B", 20);
        map2.put("C", 3);

        assertFalse("Maps should be unequal", MapDiffUtil.validateEqual(
            map1, map2, "map1", "map2"));

    }
}
