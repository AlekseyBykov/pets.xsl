package alekseybykov.portfolio.xslt;

import org.custommonkey.xmlunit.XMLUnit;
import org.junit.BeforeClass;

import javax.xml.transform.TransformerFactory;

public class XSLTTestBase {

	protected static TransformerFactory transformerFactory;
	protected static ClassLoader classLoader;
	protected final String xmlFileName = "data.xml";

	@BeforeClass
	public static void setup() {
		XMLUnit.setIgnoreWhitespace(true);
		XMLUnit.setNormalizeWhitespace(true);

		transformerFactory = TransformerFactory.newInstance();
		classLoader = XSLTTest.class.getClassLoader();
	}
}
