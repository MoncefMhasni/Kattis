import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.io.IOException;
import java.util.InputMismatchException;
import java.io.InputStream;
import java.util.ArrayList;

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
    PowerStrings solver = new PowerStrings();
    try {
      int testNumber = 1;
      while (true) {
        solver.solve(testNumber++, in, out);
      }
    } catch (UnknownError e) {
      out.close();
    }
  }

  static class PowerStrings {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
      char[] s = in.nextCharArray();
      if (s[0] == '.') {
        return;
      }
      int n = s.length;
      int factor = n;
      List<Integer> primeFactors = IntMath.generatePrimeFactors(n);
      for (int i : primeFactors) {
        int j = 0;
        for (; j + factor / i < factor; j++) {
          if (s[j] != s[j + factor / i]) {
            break;
          }
        }
        int end = factor - (factor / i);
        if (j == end) {
          factor /= i;
        }
      }
      out.println(s.length / factor);
    }

  }

  static class IntMath {

    public static List<Integer> generatePrimeFactors(int n) {
      List<Integer> ret = new ArrayList<>();
      for (int i = 2; i <= n; i++) {
        while (n % i == 0) {
          ret.add(i);
          n /= i;
        }
      }
      if (n > 1) {
        ret.add(n);
      }
      return ret;
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
}
