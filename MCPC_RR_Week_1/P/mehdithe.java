import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.AbstractCollection;
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
    ProblemPBumped solver = new ProblemPBumped();
    solver.solve(1, in, out);
    out.close();
  }

  static class ProblemPBumped {

    List<Edge>[] g;
    HashSet<Tuple> removed;
    int n;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
      n = in.nextInt();
      int m = in.nextInt();
      int f = in.nextInt();
      int s = in.nextInt();
      int t = in.nextInt();
      g = new ArrayList[n];
      removed = new HashSet<>();
      for (int i = 0; i < n; i++) {
        g[i] = new ArrayList<>();
      }
      for (int i = 0; i < m; i++) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        g[a].add(new Edge(b, c, true));
        g[b].add(new Edge(a, c, true));
      }
      long best = getBest(s, t);
      for (int i = 0; i < f; i++) {
        int a = in.nextInt();
        int b = in.nextInt();
        g[a].add(new Edge(b, 0, false));
        best = Math.min(best, getBest(s, t));
        removed.add(new Tuple<>(a, b, -1000));
      }
      out.println(best);
    }

    private long getBest(int s, int t) {
      long[] dist = new long[n];
      Arrays.fill(dist, 1L << 50);
      PriorityQueue<LongPair> pq = new PriorityQueue<>();
      dist[s] = 0;
      pq.add(Factories.makeLongPair(s, 0));
      while (!pq.isEmpty()) {
        LongPair currentEdge = pq.poll();
        int node = (int) currentEdge.first;
        for (Edge edge : g[node]) {
          if (removed.contains(new Tuple(node, edge.to, -1000)) && !edge.type) {
            continue;
          }
          long cost = edge.cost;
          int to = edge.to;
          if (dist[node] + cost < dist[to]) {
            dist[to] = dist[node] + cost;
            pq.add(Factories.makeLongPair(to, dist[to]));
          }
        }
      }
      return dist[t];
    }

    class Edge implements Comparable<Edge> {

      int to;
      long cost;
      boolean type;

      public Edge(int to, long cost, boolean type) {
        this.to = to;
        this.cost = cost;
        this.type = type;
      }

      public int compareTo(Edge o) {
        return Long.compare(cost, o.cost);
      }

      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }

        Edge edge = (Edge) o;

        if (to != edge.to) {
          return false;
        }
        if (cost != edge.cost) {
          return false;
        }
        return type == edge.type;
      }

      public int hashCode() {
        int result = to;
        result = 31 * result + (int) (cost ^ (cost >>> 32));
        result = 31 * result + (type ? 1 : 0);
        return result;
      }

      public String toString() {
        return "Edge{" + ", to=" + to + ", cost=" + cost + ", type=" + type + '}';
      }

    }

  }

  static class Tuple<T> implements Cloneable, Serializable, Comparable<Tuple<T>> {

    private T[] elements;

    public Tuple(T... elements) {
      int n = elements.length;
      this.elements = elements;
    }

    public String toString() {
      return "Tuple{" + "elements=" + Arrays.toString(elements) + '}';
    }

    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Tuple<?> tuple = (Tuple<?>) o;

      // Probably incorrect - comparing Object[] arrays with Arrays.equals
      return Arrays.equals(elements, tuple.elements);
    }

    public int hashCode() {
      return Arrays.hashCode(elements);
    }

    public int compareTo(Tuple<T> o) {
      if (elements.length != o.elements.length) {
        throw new RuntimeException("comparing tuples with different element sizes");
      }

      for (int i = elements.length - 1; i >= 0; i--) {
        Comparable<T> my = (Comparable<T>) elements[i];
        if (my.compareTo(o.elements[i]) != 0) {
          return my.compareTo(o.elements[i]);
        }
      }
      return 0;
    }

  }

  static final class Factories {

    private Factories() {
    }

    public static LongPair makeLongPair(long first, long second) {
      return new LongPair(first, second);
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

  static class LongPair implements Comparable<LongPair> {

    public long first;
    public long second;

    public LongPair(long first, long second) {
      this.first = first;
      this.second = second;
    }

    public int compareTo(LongPair a) {
      if (second == a.second) {
        return Long.compare(first, a.first);
      }
      return Long.compare(second, a.second);
    }

    public String toString() {
      return "<" + first + ", " + second + ">";
    }

    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      LongPair longPair = (LongPair) o;

      if (first != longPair.first) {
        return false;
      }
      return second == longPair.second;
    }

    public int hashCode() {
      long result = (int) (first ^ (first >>> 32));
      result = 31L * result + (second ^ (second >>> 32));
      return (int) (result >>> 10);
    }

  }
}
