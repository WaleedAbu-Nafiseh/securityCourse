import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class CrackMe {

	public static void main(String[] args) throws Exception {
		long sleepy = 1;
		int z=1;
		while (true) {
			long seed = new Random().nextLong();
			Random rnd = new Random(seed);
			final int range = 26;

			String suffix = "";
			for (int i = 0; i < 20; i++) {
				int v = rnd.nextInt(range) + 1;
				char c = (char) ('`' + v);
				System.out.println(i+")"+('`' + v)+"('`' + v);");
				suffix += c;
			}
			suffix = suffix.replace("s", "-");
			System.out.println("loops=>"+z);
			if (SHA256(suffix).startsWith("e9")) {
				System.out.println("Suffix =>"+suffix);
				System.out.println("SHA256(suffix)=>"+SHA256(suffix));
				System.out.println("You unlocked the challenge - This is your key [" + suffix.substring(0, 15) + "]");
				System.out.println("Your job now is to write a solution summary");
				break;
			}
			z++;

		}
	}

	private static String SHA256(String input) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

}