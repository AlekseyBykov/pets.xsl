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

public abstract class XSLTTestBase {

	protected static TransformerFactory transformerFactory;
	protected static ClassLoader classLoader;

	@BeforeClass
	public static void setup() {
		XMLUnit.setIgnoreWhitespace(true);
		XMLUnit.setNormalizeWhitespace(true);

		transformerFactory = TransformerFactory.newInstance();
		classLoader = XSLTTestBase.class.getClassLoader();
	}

	private Source loadXml(String xml) {
		return new StreamSource(new File(classLoader.getResource(String.format("%s%s", "xml/", xml)).getFile()));
	}

	protected String loadFixture(String fixture) throws IOException {
		return IOUtils.toString(classLoader.getResourceAsStream(String.format("%s%s", "fixture/", fixture)));
	}

	protected String transform(String xslt, String xml) throws TransformerException {
		StringWriter writer = new StringWriter();
		Source source = new StreamSource(new File(classLoader.getResource(String.format("%s%s", "xsl/base/", xslt)).getFile()));
		Transformer transformer = transformerFactory.newTransformer(source);
		transformer.transform(loadXml(xml), new StreamResult(writer));
		return writer.toString();
	}

	protected boolean isXmlSimilar(String expected, String actual) throws IOException, SAXException {
		Diff diff = new Diff(expected, actual);
		return diff.similar();
	}
}
