import com.mehdi.lib.ArrayUtils;
import com.mehdi.lib.io.InputReader;
import java.io.PrintWriter;

import static com.mehdi.lib.Factories.*;

public class BingItOn {

  int[][] trie = new int[26][100001];
  int[][] count = new int[26][100001];
  int size = 1;

  void add(char[] s) {
    int root = 0;
    for (int i = 0; i < s.length; i++) {
      int cur = s[i] - 'a';
      if (trie[cur][root] == -1) {
        trie[cur][root] = size++;
      }
      count[cur][root]++;
      root = trie[cur][root];
    }
  }

  int lookup(char[] s) {
    int root = 0;
    int cur = 0;
    for (int i = 0; i < s.length; i++) {
      cur = s[i] - 'a';
      if (trie[cur][root] == -1) {
        return 0;
      }
      if (i == s.length - 1) {
        return count[cur][root];
      }
      root = trie[cur][root];
    }
    return 0;
  }

  public void solve(int testNumber, InputReader in, PrintWriter out) {
    int n = in.nextInt();
    ArrayUtils.fillMatrix(trie, -1);
    for (int i = 0; i < n; i++) {
      char[] s = in.nextCharArray();
      out.println(lookup(s));
      add(s);
    }
  }
}
