import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
    Ls solver = new Ls();
    solver.solve(1, in, out);
    out.close();
  }

  static class Ls {

    char[] s;
    char[] t;
    int[][] memo;
    int lst;

    int rec(int i, int j) {
      if (j >= t.length) {
        if (i == s.length) {
          return 0;
        }
        if (s[i] != '*') {
          return (1 << 28);
        }
        if (s[i] == '*' && i >= lst) {
          return 0;
        }
        return 1 << 28;
      }
      if (i >= s.length) {
        return 1 << 28;
      }
      if (memo[i][j] != -1) {
        return memo[i][j];
      }
      int ans = (1 << 28);
      if (s[i] == t[j]) {
        ans = Math.min(ans, rec(i + 1, j + 1));
      } else {
        if (s[i] == '*') {
          ans = Math.min(rec(i + 1, j), ans);
          ans = Math.min(rec(i + 1, j + 1), ans);
          ans = Math.min(rec(i, j + 1), ans);
        }
      }
      return memo[i][j] = ans;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
      s = in.nextCharArray();
      int n = in.nextInt();
      int pt = s.length - 1;
      lst = pt;
      while (pt >= 0 && s[pt] == '*') {
        lst--;
        --pt;
      }
      while (n-- > 0) {
        t = in.nextCharArray();
        memo = new int[s.length][t.length];
        ArrayUtils.fillMatrix(memo, -1);
        int ans = rec(0, 0);
        if (ans == 0) {
          out.println(new String(t));
        }
      }
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

    public char[] nextCharArray() {
      return next().toCharArray();
    }

  }

  static class ArrayUtils {

    public static void fillMatrix(int[][] mat, int val) {
      for (int[] array : mat) {
        Arrays.fill(array, val);
      }
    }

  }
}
