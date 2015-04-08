package com.sunrise.scjd.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class TestDigest {

	@Test
	public void testDigest() {
		System.out.println(DigestUtils.md5Hex("123456"));
	}
}
