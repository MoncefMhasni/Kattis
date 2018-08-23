import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;
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
    FastReader in = new FastReader(inputStream);
    PrintWriter out = new PrintWriter(outputStream);
    BreakingBad solver = new BreakingBad();
    solver.solve(1, in, out);
    out.close();
  }

  static class BreakingBad {
    int[] color;
    Map<String, Integer> toInt;
    Map<Integer, String> toString;
    ArrayList<Integer>[] g;

    public void solve(int testNumber, FastReader in, PrintWriter out) {
      int n = in.nextInt();
      color = new int[n];
      toInt = new HashMap<>();
      toString = new HashMap<>();
      g = new ArrayList[n];
      for (int i = 0; i < n; i++) {
        String cur = in.next();
        toInt.put(cur, i);
        toString.put(i, cur);
        g[i] = new ArrayList<>();
      }

      Arrays.fill(color, -1);

      int m = in.nextInt();
      for (int i = 0; i < m; i++) {
        String from = in.next();
        String to = in.next();
        int fromI = toInt.get(from);
        int toI = toInt.get(to);
        g[fromI].add(toI);
        g[toI].add(fromI);
      }

      boolean ans = true;

      for (int i = 0; i < n; i++) {
        if (color[i] == -1)
          ans &= dfs(i, 0);
      }

      if (ans) {
        StringBuilder walter = new StringBuilder();
        StringBuilder jesse = new StringBuilder();
        for (int i = 0; i < n; i++) {
          if (color[i] == 1) {
            walter.append(toString.get(i) + " ");
          } else {
            jesse.append(toString.get(i) + " ");
          }
        }
        out.println(walter.toString().trim());
        out.println(jesse.toString().trim());
      } else {
        out.println("impossible");
      }
    }

    private boolean dfs(int src, int c) {
      color[src] = c;
      for (int child : g[src]) {
        if (color[child] == -1) {
          dfs(child, 1 - c);
        } else if (color[child] == color[src]) {
          return false;
        }
      }
      return true;
    }

  }

  static class FastReader {
    BufferedReader reader;
    StringTokenizer st;

    public FastReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream));
      st = null;
    }

    public String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          String line = reader.readLine();
          if (line == null) {
            return null;
          }
          st = new StringTokenizer(line);
        } catch (Exception e) {
          throw new RuntimeException();
        }
      }
      return st.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }

  }
}
