<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/">
		<html>
			<body>
				<h1>data</h1>
				<xsl:apply-templates select="data/item"/>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="data/item">
		<xsl:apply-templates select="@id"/>
		<xsl:apply-templates select="index"/>
		<xsl:apply-templates select="value"/>
	</xsl:template>

	<xsl:template match="@id">
		id:<span>
			<xsl:value-of select="."/>
		</span>
	</xsl:template>

	<xsl:template match="index">
		index:<span>
			<xsl:value-of select="."/>
		</span>
	</xsl:template>

	<xsl:template match="value">
		value:<span>
			<xsl:value-of select="."/>
		</span>
	</xsl:template>

</xsl:stylesheet>
