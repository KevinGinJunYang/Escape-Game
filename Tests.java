
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Suite tester.
 * @author joewill
 *
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({GameWorldTest.class, PersistenceTest.class, PlaceableFactoryTests.class,
    RendererRotateHelperTests.class, RendererTests.class, MapEditorTest.class,
    ApplicationTest.class})

public class Tests {
}


