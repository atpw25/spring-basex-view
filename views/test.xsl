<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">
    <xsl:output omit-xml-declaration="yes"/>
    <xsl:template match="/">
        <result><xsl:value-of select="test"/> - 123</result>
    </xsl:template>
</xsl:stylesheet>
