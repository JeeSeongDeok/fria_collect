package com.fria.collect

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.Test

class CrawlingTest {
    private val USER_AGENT =
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36"
    @Test
    fun afreecaTVCrawlingTest() {
        var doc: Document = Jsoup
            .connect("https://bj.afreecatv.com/qweasd3456/posts/89999831")
            .userAgent("Mozilla/5.0")
            .get()

        print(doc)
        val element = doc
            .select("div")
        assertNotNull(element)
    }
}