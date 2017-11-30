/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author pfares
 */
public class FonctionsDiverses {

    /**
     * Les nom d'algorythme possible Algorithm Name	Description MD2	The MD2
     * message digest algorithm as defined in RFC 1319. 
     * MD5 SHA-1 SHA-256 SHA-384 SHA-512
     * SHA-256 is a 256-bit hash function intended to provide 128 bits of security against collision attacks, 
     * while SHA-512 is a 512-bit hash function intended to provide 256 bits of security. 
     * A 384-bit hash may be obtained by truncating the SHA-512 output.
     * @param base la chaine à signer
     * @return le code chaine de carctère hexa du SHA-256(base)
     */
    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}
