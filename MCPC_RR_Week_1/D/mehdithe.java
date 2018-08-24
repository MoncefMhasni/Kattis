import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
    PeriodicStrings solver = new PeriodicStrings();
    solver.solve(1, in, out);
    out.close();
  }

  static class PeriodicStrings {

    boolean isRightShit(String a, String b) {
      if (a.length() == 1) {
        return a.charAt(0) == b.charAt(0);
      }
      int n = b.length();
      int pt = n - 1;
      for (int i = 0; i < b.length(); i++) {
        if (a.charAt(pt) != b.charAt(i)) {
          return false;
        }
        pt = (pt + 1) % n;
      }
      return true;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
      String s = in.next();
      for (int k = 1; k < s.length(); ++k) {
        if (s.length() % k == 0) {
          if (check(s, k)) {
            out.println(k);
            return;
          }
        }
      }
      out.println(s.length());
    }

    private boolean check(String s, int k) {
      for (int i = k; i < s.length(); i += k) {
        if (!isRightShit(s.substring(i - k, i), s.substring(i, i + k))) {
          return false;
        }
      }
      return true;
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

  }
}
