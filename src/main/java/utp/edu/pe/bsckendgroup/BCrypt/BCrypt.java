package utp.edu.pe.bsckendgroup.BCrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCrypt {
    public static void main(String[] args) {
        String password = "jhomt";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded password: " + encodedPassword);
    }
}
