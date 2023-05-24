package classes;
import lombok.RequiredArgsConstructor;
import java.util.Arrays;


@RequiredArgsConstructor
public class Encoder {
    private final Key publicKey;
    private final Key privateKey;

    public long[] convertToNumbers(String str) {
        char[] arrChars = str.toCharArray();
        long[] arrNumbers = new long[arrChars.length];
        for(int i = 0; i < arrNumbers.length; i++) {
            arrNumbers[i] = arrChars[i];
        }
        return arrNumbers;
    }

    public String convertNumbersToString(long[] arrNumbers) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arrNumbers.length; i++) {
            char c = (char) arrNumbers[i];
            builder.append(c);
        }

        String str = builder.toString();
        builder.setLength(0);
        return str;
    }

    public long[] encode(long[] arrNumbers) {
        long[] arrEncodedNumbers = new long[arrNumbers.length];
        for(int i = 0; i < arrEncodedNumbers.length; i++) {
            arrEncodedNumbers[i] = (long) ((Math.pow(arrNumbers[i], publicKey.getFirstNumber())) % publicKey.getSecondNumber());
        }
        return arrEncodedNumbers;
    }

    public long[] decode(long[] arrEncodedNumbers) {
        long[] arrDecodedNumbers = new long[arrEncodedNumbers.length];
        for(int i = 0; i < arrDecodedNumbers.length; i++) {
            arrDecodedNumbers[i] = (long) (Math.pow(arrEncodedNumbers[i], privateKey.getFirstNumber()) % privateKey.getSecondNumber());
        }
        return arrDecodedNumbers;
    }

    public static void main(String[] args) {
        // creating the keys
        KeyCreator keyCreator = new KeyCreator(3, 11, 3);
        keyCreator.printNumbers();
        Key publicKey = keyCreator.createPublicKey();
        Key privateKey = keyCreator.createPrivateKey();
        System.out.println("public key:");
        System.out.println(publicKey);
        System.out.println("private key:");
        System.out.println(privateKey);

        // encoding the phrase
        Encoder encoder = new Encoder(publicKey, privateKey);
        String str = "The Legend of Zelda: Ocarina of Time";
        long[] arrNumbers = encoder.convertToNumbers(str.toLowerCase());
        System.out.println("phrase: " + str);
        System.out.println("converted phrase: " + Arrays.toString(arrNumbers));

        long[] arrEncodedNumbers = encoder.encode(arrNumbers);
        System.out.println("encoded numbers: " + Arrays.toString(arrEncodedNumbers));

        long[] arrDecodedNumbers = encoder.decode(arrEncodedNumbers);
        String decodedStr = encoder.convertNumbersToString(KeyCreator.arr_decoded_numbers2);
        System.out.println("decoded numbers: " + Arrays.toString(KeyCreator.arr_decoded_numbers2));
        System.out.println("decoded phrase: " + decodedStr);
    }
}