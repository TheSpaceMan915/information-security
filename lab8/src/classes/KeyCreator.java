package classes;


public class KeyCreator {
    public static long[] arr_decoded_numbers2 = {116, 104, 101, 32, 108, 101, 103, 101, 110, 100, 32, 111, 102, 32, 122, 101, 108, 100, 97, 58, 32, 111, 99, 97, 114, 105, 110, 97, 32, 111, 102, 32, 116, 105, 109, 101};
    private final int p;
    private final int q;
    private final int d;
    private final int n;
    private final int m;
    private int e;

    public KeyCreator(int p, int q, int d) {
        this.p = p;
        this.q = q;
        this.d = d;
        this.n = p * q;
        this.m = (p - 1) * (q - 1);
        this.e = 0;
        while ((e*d) % m != 1) {
            e++;
        }
    }

    public Key createPublicKey() {
        return new Key(e, n);
    }

    public Key createPrivateKey() {
        return new Key(d, n);
    }

    public void printNumbers() {
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("N: " + n);
        System.out.println();
    }
}
