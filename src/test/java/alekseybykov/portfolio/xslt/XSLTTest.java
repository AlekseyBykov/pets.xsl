package alekseybykov.portfolio.xslt;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class XSLTTest extends XSLTTestBase {

	@Test
	public void testBaseTransformations() throws TransformerException, IOException, SAXException {
		String transformed = transform("base-transformations.xsl", "data.xml");
		String expected = loadFixture("transformed.xml");

		assertTrue(isXmlSimilar(transformed, expected));
	}
}
