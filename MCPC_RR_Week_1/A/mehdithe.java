import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
    HowManyDigits solver = new HowManyDigits();
    try {
      int testNumber = 1;
      while (true) {
        solver.solve(testNumber++, in, out);
      }
    } catch (UnknownError e) {
      out.close();
    }
  }

  static class HowManyDigits {

    final int MAX = 1000001;
    double[] val = new double[MAX];

    {
      pre();
    }

    void pre() {
      for (int i = 1; i < MAX; ++i) {
        val[i] = Math.log10(i);
        val[i] += val[i - 1];
      }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
      int n = in.nextInt();
      out.println((long) Math.floor(val[n]) + 1);
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

    public static boolean isSpaceChar(int c) {
      return c == 32 || c == 10 || c == 13 || c == 9 || c == -1;
    }

  }
}
