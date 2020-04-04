package alekseybykov.portfolio.xslt;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.Diff;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;

public class XSLTTest extends XSLTTestBase {

	@Test
	public void testBaseTransformations() throws TransformerException, IOException, SAXException {
		final String xslFileName = "base-transformations.xsl";
		StringWriter writer = new StringWriter();

		Source xsltSource = new StreamSource(new File(classLoader.getResource(String.format("%s%s", "xsl/base/", xslFileName)).getFile()));
		Transformer transformer = transformerFactory.newTransformer(xsltSource);
		Source xmlSource = new StreamSource(new File(classLoader.getResource(String.format("%s%s", "xml/", xmlFileName)).getFile()));

		transformer.transform(xmlSource, new StreamResult(writer));
		String xmlTransformed = writer.toString();
		String xmlExpected = IOUtils.toString(classLoader.getResourceAsStream("fixture/transformed.xml"));

		Diff diff = new Diff(xmlExpected, xmlTransformed);
		assertTrue(diff.similar());
	}
}
