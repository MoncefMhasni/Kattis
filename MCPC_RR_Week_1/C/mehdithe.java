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
    ProblemCSupercomputer solver = new ProblemCSupercomputer();
    solver.solve(1, in, out);
    out.close();
  }

  static class ProblemCSupercomputer {

    int n;
    int m;
    int[] seg;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
      n = in.nextInt();
      m = in.nextInt();
      seg = new int[4 * n];
      SegmentTree tree = new SegmentTree(n) {

        public long getNeutral() {
          return 0;
        }

        public long operation(long ansLeft, long ansRight) {
          return ansLeft + ansRight;
        }
      };

      for (int i = 0; i < m; ++i) {
        char op = in.next().charAt(0);
        if (op == 'F') {
          int k = in.nextInt() - 1;
          int cur = (int) tree.query(k, k);
          tree.update(k, k, 1 - cur);
        } else {
          int l = in.nextInt() - 1;
          int r = in.nextInt() - 1;
          out.println(tree.query(l, r));
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

  }

  static abstract class SegmentTree {

    long[] tree;
    long[] delta;
    long[] hi;
    long[] lo;
    int n;

    public SegmentTree(int n) {
      this.n = n;
      tree = new long[4 * n + 1];
      delta = new long[4 * n + 1];
      hi = new long[4 * n + 1];
      lo = new long[4 * n + 1];
      build(1, 0, n - 1);
    }

    public SegmentTree(int n, int[] arr) {
      this.n = n;
      tree = new long[4 * n + 1];
      delta = new long[4 * n + 1];
      hi = new long[4 * n + 1];
      lo = new long[4 * n + 1];
      build(1, 0, n - 1, arr);
    }

    private void build(int root, int left, int right, int[] arr) {
      if (left == right) {
        tree[root] = arr[left];
        lo[root] = left;
        hi[root] = right;
        return;
      }
      int mid = (left + right) >>> 1;
      build(root * 2, left, mid);
      build(root * 2 + 1, mid + 1, right);
      makeUpdate(root);
    }

    private void build(int i, int left, int right) {
      lo[i] = left;
      hi[i] = right;
      int mid = (left + right) >> 1;
      if (left == right) {
        return;
      }
      build(2 * i, left, mid);
      build(2 * i + 1, mid + 1, right);
    }

    public void update(int left, int right, long val) {
      update(1, left, right, val);
    }

    private void propagate(int root) {
      delta[root * 2] += delta[root];
      delta[root * 2 + 1] += delta[root];
      delta[root] = 0;
    }

    private void makeUpdate(int root) {
      tree[root] = operation(tree[root * 2] + delta[root * 2], tree[root * 2 + 1] + delta[root * 2 + 1]);
    }

    private void update(int root, int left, int right, long val) {
      if (right < lo[root] || hi[root] < left) {
        return;
      }
      if (left <= lo[root] && hi[root] <= right) {
        delta[root] = val;
        return;

      }
      propagate(root);
      update(2 * root, left, right, val);
      update(2 * root + 1, left, right, val);
      makeUpdate(root);
    }

    public long query(int left, int right) {
      return query(1, left, right);
    }

    private long query(int root, int left, int right) {
      if (right < lo[root] || hi[root] < left) {
        return getNeutral() / 2;
      }
      if (left <= lo[root] && hi[root] <= right) {
        return tree[root] + delta[root];
      }
      propagate(root);
      long ansLeft = query(root * 2, left, right);
      long ansRight = query(root * 2 + 1, left, right);

      makeUpdate(root);

      return operation(ansLeft, ansRight);
    }

    public abstract long getNeutral();

    public abstract long operation(long ansLeft, long ansRight);

  }
}
