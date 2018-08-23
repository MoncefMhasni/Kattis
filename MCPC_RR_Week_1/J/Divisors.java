
import com.mehdi.lib.ds.CountingMap;
import com.mehdi.lib.io.InputReader;
import com.mehdi.lib.math.IntMath;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

import static com.mehdi.lib.Factories.*;

public class Divisors {

  BigInteger[][] c;
  int[] primes = IntMath.generatePrimesLinear(1000);

  long powerInFact(int x, int p) {
    long result = 0;
    while (true) {
      x /= p;
      if (x == 0) {
        break;
      }
      result += x;
    }
    return result;
  }

  BigInteger getAnswer(int n, int k) {
    HashMap<Integer, BigInteger> a = getFor(n);
    HashMap<Integer, BigInteger> b = getFor(k);
    HashMap<Integer, BigInteger> c = getFor(n - k);
    decrement(a, b);
    decrement(a, c);
    return compute(a);
  }

  private BigInteger compute(HashMap<Integer, BigInteger> a) {
    BigInteger ret = BigInteger.ONE;
    for (Integer key : a.keySet()) {
      ret = ret.multiply(BigInteger.ONE.add(a.get(key)));
    }
    return ret;
  }

  private void decrement(HashMap<Integer, BigInteger> a, HashMap<Integer, BigInteger> b) {
    for (Integer key : b.keySet()) {
      BigInteger val = a.get(key);
      a.put(key, val.subtract(b.get(key)));
    }
  }

  private HashMap<Integer, BigInteger> getFor(int n) {
    HashMap<Integer, BigInteger> ret = new HashMap<>();
    for (int pr : primes) {
      if (pr > n) {
        break;
      }
      ret.put(pr, BigInteger.valueOf(powerInFact(n, pr)));
    }
    return ret;
  }

  void pre() {
    c = new BigInteger[444][444];
    for (int i = 0; i <= 433; i++) {
      for (int j = 0; j <= i; j++) {
        c[i][j] = getAnswer(i, j);
      }
    }
  }

  public void solve(int testNumber, InputReader in, PrintWriter out) {
    int n = in.nextInt();
    int k = in.nextInt();
    out.println(getAnswer(n, k));
  }
}
