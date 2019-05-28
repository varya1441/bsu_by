<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"><xsl:template match="/">
        <html>
            <head>
                <style>

                    th {
                    font-weight: normal;
                    color: black;

                    }
                    td {
                    font-weight: bold;
                    color: black;

                    }
                    tr:hover td {
                    background: rgba(51, 51, 153, .1);
                    }
                </style>
            </head>
            <body>
                <table>
                    <tr>
                        <td align="center"><strong>Name</strong></td>
                        <td align="center"><strong>Author</strong></td>
                        <td align="center"><strong>Extension</strong></td>
                        <td align="center"><strong>Year</strong></td>
                        <td align="center"><strong>Words</strong></td>
                    </tr>

                    <xsl:for-each select="music/albom">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="author"/></td>
                            <td><xsl:value-of select="@extension"/></td>
                            <td><xsl:value-of select="@year"/></td>
                            <td><xsl:value-of select="words"/></td>
                        </tr>
                    </xsl:for-each>
                    <tr>
                        <td></td><td></td><td></td>
                        <td><strong>Count of words: </strong></td>
                        <td><xsl:value-of select="sum(music/albom/words)"/></td>
                    </tr>

                    <tr>
                        <td></td><td></td><td></td>
                        <td><strong>Average word count: </strong></td>
                        <td><xsl:value-of select="sum(music/albom/words) div count(music/albom)"/></td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>