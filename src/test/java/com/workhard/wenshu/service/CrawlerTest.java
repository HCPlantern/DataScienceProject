package com.workhard.wenshu.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class CrawlerTest {

    @Test
    public void test() {
        Crawler crawler = new Crawler();
        crawler.go();
    }
}