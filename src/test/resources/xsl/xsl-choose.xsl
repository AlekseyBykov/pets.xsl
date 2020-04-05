<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/">
		<html>
			<body>
				<h1>data</h1>
				<table>
					<tr>
						<th>Index</th>
						<th>Value</th>
						<th>&gt;=100</th>
					</tr>
			<xsl:for-each select="data/item">
					<tr>
						<td><xsl:value-of select="index"/></td>
						<td><xsl:value-of select="value"/></td>
						<td>
							<xsl:choose>
								<xsl:when test="value >= 100">Y</xsl:when>
								<xsl:otherwise>N</xsl:otherwise>
							</xsl:choose>
						</td>
					</tr>
			</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
