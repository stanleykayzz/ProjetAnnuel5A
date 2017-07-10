package server.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import server.model.Client;
import server.repository.ClientRepository;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SecurityClient {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private static String ENCRYPTION_KEY = "45811456458114";

    public String hashPassword(String password){
        MessageDigest digest = null;
        String finalPassword = "bad password";
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
            finalPassword = bytesToHex(encodedhash);
            LOG.trace("Generate new hash {}", finalPassword);
            return finalPassword;
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Cannot hash {}, result: {}", password, finalPassword);
        }
        return finalPassword;
    }


    public Client updatePasswordClient(Client client) {
        int randomCode = ThreadLocalRandom.current().nextInt(0, 9999);

        String pswd = hashPassword(client.getPassword());
        client.setStatus(0);
        client.setCode(String.valueOf(randomCode));
        client.setPassword(pswd.toString());
        return client;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }


}
