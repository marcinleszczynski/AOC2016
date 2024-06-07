
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

void main() throws NoSuchAlgorithmException {
    String id = "ugkcyxxp";
    String password = part1(id);
    System.out.println(password);
    String password2 = part2(id);
    System.out.println(password2);
}

String part1(String id) throws NoSuchAlgorithmException {
    StringBuilder password = new StringBuilder();
    int num = 0;
    MessageDigest md = MessageDigest.getInstance("MD5");
    while(password.length() < 8) {
        md.update(STR."\{id}\{num}".getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for(byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        String hash = sb.toString();
        if(hash.startsWith("00000")) {
            //System.out.println(hash);
            password.append(hash.charAt(5));
        }
        //System.out.println(STR."code: \{id}\{num}, hash: \{hash}, digest: \{Arrays.toString(digest)}");
        ++num;
    }
    return password.toString();
}

String part2(String id) throws NoSuchAlgorithmException {
    char[] password = new char[8];
    int count = 0;
    int num = 0;
    MessageDigest md = MessageDigest.getInstance("MD5");
    while(count < 8) {
        md.update(STR."\{id}\{num}".getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for(byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        String hash = sb.toString();
        if(hash.startsWith("00000")) {
            try {
                int position = Integer.parseInt(hash.substring(5, 6));
                if(position < 8 && password[position] == '\0') {
                    password[position] = hash.charAt(6);
                    ++count;
                }
            } catch(NumberFormatException _) {}
        }
        ++num;
    }
    return new String(password);
}