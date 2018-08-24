import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.util.InputMismatchException;
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
    Alphabet solver = new Alphabet();
    solver.solve(1, in, out);
    out.close();
  }

  static class Alphabet {

    char[] al = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    char[] s;
    int[][] memo;

    int f(int i, int j) {
      if (j >= al.length) {
        return 0;
      }
      if (i >= s.length) {
        return al.length - j;
      }
      if (memo[i][j] != -1) {
        return memo[i][j];
      }
      int ans = 1 << 30;
      if (s[i] == al[j]) {
        ans = Math.min(ans, f(i + 1, j + 1));
      }
      ans = Math.min(ans, f(i, j + 1) + 1);
      ans = Math.min(ans, f(i + 1, j));
      return memo[i][j] = ans;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
      s = in.nextCharArray();
      memo = new int[s.length + 1][30];
      ArrayUtils.fillMatrix(memo, -1);
      out.println(f(0, 0));
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
