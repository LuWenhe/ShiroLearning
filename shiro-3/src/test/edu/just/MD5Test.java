package edu.just;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class MD5Test {

    @Test
    public void test3() {
        String hashAlgorithmName = "SHA1";
        String credentials = "123456";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes("user");
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(simpleHash);
    }

    @Test
    public void test2() {
        String username = "admin";
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        System.out.println(credentialsSalt);
    }

    @Test
    public void test1() {
        String hashAlgorithmName = "MD5";
        String credentials = "123456";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes("user");
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(simpleHash);
    }

    @Test
    public void test() {
        String hashAlgorithmName = "MD5";
        String credentials = "123456";
        Object salt = null;
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(simpleHash);
    }

}
