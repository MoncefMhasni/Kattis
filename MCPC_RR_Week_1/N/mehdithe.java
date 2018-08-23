import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in Actual solution is at the top
 *
 * @author MaxHeap
 */
public class Main {

  public static void main(String[] args) {
    InputStream inputStream = System.in;
    OutputStream outputStream = System.out;
    InputReader in = new InputReader(inputStream);
    PrintWriter out = new PrintWriter(outputStream);
    WhatDoesItMean solver = new WhatDoesItMean();
    solver.solve(1, in, out);
    out.close();
  }

  static class WhatDoesItMean {

    long MOD = (long) (1e9 + 7);
    HashMap<String, Long> map = new HashMap<>();
    String s;
    int n;
    long[] memo = new long[50];

    long go(int len) {
      if (memo[len] != -1) {
        return memo[len];
      }
      String cur = s.substring(len);
      long ans = 0;
      if (map.containsKey(cur)) {
        ans += map.get(cur);
      }
      for (int i = 1; i + len <= s.length(); ++i) {
        String sub = s.substring(len, len + i);
        if (map.containsKey(sub)) {
          ans = (ans + (map.get(sub) * go(i + len) % MOD)) % MOD;
        }
      }
      return memo[len] = ans;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
      n = in.nextInt();
      s = in.next();
      Arrays.fill(memo, -1);
      for (int i = 0; i < n; i++) {
        String cur = in.next();
        long val = in.nextLong();
        map.put(cur, val);
      }
      out.println(go(0));
    }

  }

  static class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1 << 13];
    private int curChar;
    private int numChars;

    public InputReader(InputStream stream) {
      this.stream = stream;
    }

    public int read() {
      if (this.numChars == -1) {
        throw new UnknownError();
      } else {
        if (this.curChar >= this.numChars) {
          this.curChar = 0;

          try {
            this.numChars = this.stream.read(this.buf);
          } catch (IOException ex) {
            throw new InputMismatchException();
          }

          if (this.numChars <= 0) {
            return -1;
          }
        }

        return this.buf[this.curChar++];
      }
    }

    public int nextInt() {
      int c;
      for (c = this.read(); isSpaceChar(c); c = this.read()) {
      }

      byte sgn = 1;
      if (c == 45) {
        sgn = -1;
        c = this.read();
      }

      int res = 0;

      while (c >= 48 && c <= 57) {
        res *= 10;
        res += c - 48;
        c = this.read();
        if (isSpaceChar(c)) {
          return res * sgn;
        }
      }

      throw new InputMismatchException();
    }

    public long nextLong() {
      int c;
      for (c = this.read(); isSpaceChar(c); c = this.read()) {
      }

      byte sgn = 1;
      if (c == 45) {
        sgn = -1;
        c = this.read();
      }

      long res = 0;

      while (c >= 48 && c <= 57) {
        res *= 10L;
        res += c - 48;
        c = this.read();
        if (isSpaceChar(c)) {
          return res * sgn;
        }
      }
      throw new InputMismatchException();
    }

    public String next() {
      int c;
      while (isSpaceChar(c = this.read())) {
      }

      StringBuilder result = new StringBuilder();
      result.appendCodePoint(c);

      while (!isSpaceChar(c = this.read())) {
        result.appendCodePoint(c);
      }

      return result.toString();
    }

    public static boolean isSpaceChar(int c) {
      return c == 32 || c == 10 || c == 13 || c == 9 || c == -1;
    }

  }
}
