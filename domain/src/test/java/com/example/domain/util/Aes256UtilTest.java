package com.example.domain.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Aes256UtilTest {

    @Test
    @DisplayName("Aes256 Utils Test")
    void successEncryptTest() {
        // given
        String encrypt = Aes256Util.encrypt("Hello World");

        // when
        // then
        assertEquals(Aes256Util.decrypt(encrypt), "Hello World");
    }
}