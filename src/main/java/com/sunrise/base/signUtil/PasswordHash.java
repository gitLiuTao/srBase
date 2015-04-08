package com.sunrise.base.signUtil;

import java.security.SecureRandom;

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.UUID;

/**
 * password的Hash算法
 * 
 */
public class PasswordHash {
	// 算法类型
	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

	// Salt字节长度
	public static final int SALT_BYTE_SIZE = 24;
	// 散列字节长度
	public static final int HASH_BYTE_SIZE = 24;
	// PBKDF2迭代次数
	public static final int PBKDF2_ITERATIONS = 1000;

	public static final int ITERATION_INDEX = 0;
	public static final int SALT_INDEX = 1;
	public static final int PBKDF2_INDEX = 2;

	/**
	 * 获取password hash值.
	 * 
	 * @param password
	 *            the password to hash
	 * @return a salted PBKDF2 hash of the password
	 */
	public static String createHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		return createHash(password.toCharArray());
	}

	/**
	 * 获取password hash值.
	 * 
	 * @param password
	 *            the password to hash
	 * @return a salted PBKDF2 hash of the password
	 */
	public static String createHash(char[] password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// 生成盐 salt
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);

		// 生成 password Hash值
		byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
		
		// 格式： 迭代次数:salt:hash值
		return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
	}

	/**
	 * 验证password的 hash值.
	 * 
	 * @param password
	 *            the password to check
	 * @param correctHash
	 *            the hash of the valid password
	 * @return true if the password is correct, false if not
	 */
	public static boolean validatePassword(String password, String correctHash)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		return validatePassword(password.toCharArray(), correctHash);
	}

	/**
	 * 验证password的 hash值.
	 * 
	 * @param password
	 *            被验证的 password
	 * @param correctHash
	 *            用户验证 password 的Hash值
	 * @return true if the password is correct, false if not
	 */
	public static boolean validatePassword(char[] password, String correctHash)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// Decode the hash into its parameters
		String[] params = correctHash.split(":");
		int iterations = Integer.parseInt(params[ITERATION_INDEX]);
		byte[] salt = fromHex(params[SALT_INDEX]);
		byte[] hash = fromHex(params[PBKDF2_INDEX]);
		// Compute the hash of the provided password, using the same salt,
		// iteration count, and hash length
		byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
		// Compare the hashes in constant time. The password is correct if
		// both hashes match.
		return slowEquals(hash, testHash);
	}

	/**
	 * Compares two byte arrays in length-constant time. This comparison method
	 * is used so that password hashes cannot be extracted from an on-line
	 * system using a timing attack and then attacked off-line.
	 * 
	 * @param a
	 *            the first byte array
	 * @param b
	 *            the second byte array
	 * @return true if both byte arrays are the same, false if not
	 */
	private static boolean slowEquals(byte[] a, byte[] b) {
		int diff = a.length ^ b.length;
		for (int i = 0; i < a.length && i < b.length; i++)
			diff |= a[i] ^ b[i];
		return diff == 0;
	}

	/**
	 * 对password进行 PBKDF2 hash运算.
	 * 
	 * @param password
	 *            the password to hash.
	 * @param salt
	 *            the salt
	 * @param iterations
	 *            the iteration count (slowness factor)
	 * @param bytes
	 *            the length of the hash to compute in bytes
	 * @return the PBDKF2 hash of the password
	 */
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations,
			int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		return skf.generateSecret(spec).getEncoded();
	}

	/**
	 * Converts a string of hexadecimal characters into a byte array.
	 * 
	 * @param hex
	 *            the hex string
	 * @return the hex string decoded into a byte array
	 */
	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			binary[i] = (byte) Integer.parseInt(
					hex.substring(2 * i, 2 * i + 2), 16);
		}
		return binary;
	}

	/**
	 * Converts a byte array into a hexadecimal string.
	 * 
	 * @param array
	 *            the byte array to convert
	 * @return a length*2 character string encoding the byte array
	 */
	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0)
			return String.format("%0" + paddingLength + "d", 0) + hex;
		else
			return hex;
	}

	/**
	 * 测试 PasswordHash
	 * 
	 * @param args
	 *            ignored
	 */
	public static void main2(String[] args) {
		try {
			long start = System.currentTimeMillis();
			// Print out 10 hashes
			for (int i = 0; i < 1000; i++)
				PasswordHash.createHash("p\r\nassw0Rd!");
			//System.out.println(PasswordHash.createHash("p\r\nassw0Rd!"));

			System.out.println( System.currentTimeMillis() - start);
			// Test password validation
			boolean failure = false;
			System.out.println("Running tests...");
			for (int i = 0; i < 100; i++) {
				String password = "" + i;
				String hash = createHash(password);
				// 测试Hash值的唯一性
				String secondHash = createHash(password);
				if (hash.equals(secondHash)) {
					System.out.println("FAILURE: TWO HASHES ARE EQUAL!");
					failure = true;
				}
				// 测试错误口令是否通过验证
				String wrongPassword = "" + (i + 1);
				if (validatePassword(wrongPassword, hash)) {
					System.out.println("FAILURE: WRONG PASSWORD ACCEPTED!");
					failure = true;
				}
				// 测试正确口令是否出现无法通过验证的情况
				if (!validatePassword(password, hash)) {
					System.out.println("FAILURE: GOOD PASSWORD NOT ACCEPTED!");
					failure = true;
				}
			}
			if (failure)
				System.out.println("TESTS FAILED!");
			else
				System.out.println("TESTS PASSED!");
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex);
		}
	}
	
	/**
	 * 测试 PasswordHash
	 * 
	 * @param args
	 *            ignored
	 */
	public static void main(String[] args) {
		try {
			long start = System.currentTimeMillis();
			
			//System.out.println(PasswordHash.createHash("osp123456"));
			//System.out.println(PasswordHash.createHash("cp123456"));
			System.out.println(PasswordHash.createHash("test121123456"));
			//System.out.println(PasswordHash.createHash("administratortesttest"));
			//System.out.println(UUID.randomUUID().toString());
			//System.out.println(getRandomPwd(8));
			
			System.out.println( System.currentTimeMillis() - start);
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex);
		}
	}

	/**
	* 生成随机密码
	* @param pwdLenth 生成的密码长度
	* @return 随机密码
	*/
	public static String getRandomPwd(int pwdLenth) {
	   StringBuilder buffer = new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
	   StringBuilder sb = new StringBuilder();
	   Random r = new Random();
	   int range = buffer.length();
	   for (int i = 0; i < pwdLenth; i++) {
		   //生成指定范围类的随机数0—字符串长度(包括0、不包括字符串长度)
		   sb.append(buffer.charAt(r.nextInt(range)));
	   }
	   return sb.toString();
	}
	
}
