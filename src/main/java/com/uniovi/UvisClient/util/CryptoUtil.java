package com.uniovi.UvisClient.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

/**
 * Class with statics methods for cryptographic operations.
 * @author Pelayo Díaz Soto
 *
 */
public class CryptoUtil {
	
	/**
	 * It uses Google Guava Library to obtain a hash representation of the String
	 * given by parameter.
	 * 
	 * @param input 
	 * 			the String to be transformed to a hash.
	 * @return String 
	 * 			the hash representation of the input.
	 */
	public static String getSha256Hash(String input) {
		HashCode sha256 = Hashing.sha256().hashString(input, StandardCharsets.UTF_8);
		return sha256.toString();
	}

}
