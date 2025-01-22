package com.agritech.agritech_app.util;

import javax.crypto.SecretKey;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class Test {

	public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generates a secure key
        String encodedKey = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Generated Key: " + encodedKey);
    }

}
