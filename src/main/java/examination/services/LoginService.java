package examination.services;

import examination.entity.User;
import examination.repository.UserRepository;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginService {
    private final UserRepository userRepository;
    public LoginService() {
        this.userRepository = new UserRepository();
    }
    public User register(String userName,String password,String name,String surName){
        if(userName.trim().equals("")){
            System.out.println("Username is empty");
            return null;
        }
        if(password.trim().equals("")){
            System.out.println("Password is empty");
            return null;
        }
        if(name.trim().equals("")){
            System.out.println("Name is empty");
            return null;
        }
        if(surName.trim().equals("")){
            System.out.println("Surname is empty");
            return null;
        }
        if(userRepository.exists(userName)){
            System.out.println("User already exist");
            return null;
        }
        try {
            String hashedPassword = hashPassword(password);
            userRepository.createUser(new User(null,name,surName,userName,hashedPassword));
            return userRepository.getUser(userName,hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
            return null;
        }

    }
    public User login(String userName, String password){
        try {
           return userRepository.getUser(userName,hashPassword(password));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
            return null;
        }

    }
    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        return toHexString(getSHA(password));
    }
   private static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
