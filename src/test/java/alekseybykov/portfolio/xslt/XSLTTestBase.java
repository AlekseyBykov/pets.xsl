package alekseybykov.portfolio.xslt;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.BeforeClass;

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
	protected final String xmlFileName = "data.xml";

	@BeforeClass
	public static void setup() {
		XMLUnit.setIgnoreWhitespace(true);
		XMLUnit.setNormalizeWhitespace(true);

		transformerFactory = TransformerFactory.newInstance();
		classLoader = XSLTTestBase.class.getClassLoader();
	}

	private Source loadXml() {
		return new StreamSource(new File(classLoader.getResource(String.format("%s%s", "xml/", xmlFileName)).getFile()));
	}

	protected String loadFixture(String fixture) throws IOException {
		return IOUtils.toString(classLoader.getResourceAsStream(String.format("%s%s", "fixture/", fixture)));
	}

	protected String transform(String xslt) throws TransformerException {
		StringWriter writer = new StringWriter();
		Source source = new StreamSource(new File(classLoader.getResource(String.format("%s%s", "xsl/base/", xslt)).getFile()));
		Transformer transformer = transformerFactory.newTransformer(source);

		transformer.transform(loadXml(), new StreamResult(writer));
		return writer.toString();
	}
}
