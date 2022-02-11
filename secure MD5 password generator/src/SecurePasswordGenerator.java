
import java.util.Date;
import java.text.SimpleDateFormat;
import java.security.MessageDigest;
import java.util.Random;
import java.util.Scanner;
public class SecurePasswordGenerator {
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println("please enter password length: ");
		Scanner input = new Scanner(System.in);
		int length = input.nextInt();
		input.close();

		String charsNorml = "qwertyuiopasdfghjklzxcvbnm";
		String charsUppercase = "QWERTYUIOPASDFGHJKLZXCVBNM";
		String numbers = "1234567890";
		String specChars = "";
		String pswd = charsNorml + charsUppercase + numbers + specChars;
		String protection = "";
		Random rnd = new Random();

		char[] pass = new char[length];
		for (int i = 0; i < length; i++) {
			pass[i] = pswd.charAt(rnd.nextInt(pswd.length()));
		};
		if (length <= 6) {
			protection = "🔓Password is too short.";
		}
		if (length > 6) {
			protection = "🔒Password is medium in length.";
		}
		if (length > 12) {
			protection = "🔐Password is high in length.";
		}

		System.out.print("Your password: ");
		System.out.println(pass);
		System.out.println(protection);
		String passhex = new String(pass);

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(passhex.getBytes());
		byte[] digest = md.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {

			hexString.append(Integer.toHexString(0xFF & digest[i]));
		}

		System.out.println("MD5 Hash: " + hexString.toString());
		SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy HH:mm:ss z");

		System.out.println(dateFormat.format(new Date()));

		long endTime = System.currentTimeMillis();
		long time = (endTime - startTime);

		
		System.out.print("🔰");

		// Print the program run time
		System.out.println("Program runtime: " + (float) time / 1000 + " seconds");
	}
}