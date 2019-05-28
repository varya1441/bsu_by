<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text"/>
    <xsl:template match="/">
        <xsl:for-each select="music/albom">
            newAlbum
            name:<xsl:value-of select="concat('name:', name, ' by ', author, ', ', words, ' words. Year: ', @year,'. Extension: ', @extension, '.')"/>
        </xsl:for-each>
<!--        <xsl:for-each select="bsu/student">-->
<!--            newStudent-->
<!--            id:<xsl:value-of select="@id"/>-->
<!--            name:<xsl:value-of select="@name"/>-->
<!--            faculty:<xsl:value-of select="faculty"/>-->
<!--            year:<xsl:value-of select="year"/>-->
<!--            Mean Grade:<xsl:value-of select="grade"/>-->
<!--        </xsl:for-each>-->
<!--    </xsl:template>-->
    </xsl:template>
</xsl:stylesheet>