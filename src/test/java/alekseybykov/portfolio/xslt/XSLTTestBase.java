package alekseybykov.portfolio.xslt;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.BeforeClass;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import static java.lang.String.format;

/**
 * @author Aleksey Bykov
 * @since 04.04.2020
 */
public abstract class XSLTTestBase {

	protected static TransformerFactory transformerFactory;
	protected static ClassLoader classLoader;

	private static final String XML_PATH = "xml";
	private static final String XSL_PATH = "xsl";
	private static final String FIXTURE_PATH = "fixture";

	@BeforeClass
	public static void setup() {
		XMLUnit.setIgnoreWhitespace(true);
		XMLUnit.setNormalizeWhitespace(true);

		transformerFactory = TransformerFactory.newInstance();
		classLoader = XSLTTestBase.class.getClassLoader();
	}

	protected String loadFixture(String fixture) throws IOException {
		return IOUtils.toString(classLoader.getResourceAsStream(format("%s/%s", FIXTURE_PATH, fixture)));
	}

	protected String transform(String xslt, String xml) throws TransformerException {
		StringWriter writer = new StringWriter();
		Source source = new StreamSource(new File(classLoader.getResource(format("%s/%s", XSL_PATH, xslt)).getFile()));
		Transformer transformer = transformerFactory.newTransformer(source);
		transformer.transform(loadXml(xml), new StreamResult(writer));
		return writer.toString();
	}

	protected boolean isXmlSimilar(String expected, String actual) throws IOException, SAXException {
		Diff diff = new Diff(expected, actual);
		return diff.similar();
	}

	private Source loadXml(String xml) {
		return new StreamSource(new File(classLoader.getResource(format("%s/%s", XML_PATH, xml)).getFile()));
	}
}
