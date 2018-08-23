import com.mehdi.lib.io.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;

import static com.mehdi.lib.Factories.*;

public class VirtualFriends {

  int[] parent;
  int[] rank;
  int[] comp;
  HashMap<String, Integer> map;

  public void init(int size) {
    parent = new int[size];
    comp = new int[size];
    for (int i = 0; i < size; i++) {
      parent[i] = i;
      comp[i] = 1;
    }
    rank = new int[size];
    map = new HashMap<>();

  }

  public int find(int x) {
    return x == parent[x] ? x : (parent[x] = find(parent[x]));
  }

  public void merge(int a, int b) {
    a = find(a);
    b = find(b);
    if (a == b) {
      return;
    }
    if (rank[a] < rank[b]) {
      parent[a] = b;
    } else {
      parent[b] = a;
      if (rank[a] == rank[b]) {
        ++rank[a];
      }
    }
  }

  private void merge(String a, String b) {
    int indA = map.get(a);
    int indB = map.get(b);
    merge(indA, indB);
  }

  private int find(String a) {
    int indA = map.get(a);
    return find(indA);
  }

  public void solve(int testNumber, InputReader in, PrintWriter out) {
    int n = in.nextInt();
    init(100010);
    int val = 0;
    for (int i = 0; i < n; i++) {
      String a = in.next();
      String b = in.next();
      if (!map.containsKey(a)) {
        map.put(a, val++);
      }
      if (!map.containsKey(b)) {
        map.put(b, val++);
      }
      int parentA = find(a);
      int parentB = find(b);
      if (parentA != parentB) {
        merge(a, b);
        int newParent = find(a);
        if (newParent != parentA) {
          comp[newParent] += comp[parentA];
          comp[parentA] = 0;
        }
        if (newParent != parentB) {
          comp[newParent] += comp[parentB];
          comp[parentB] = 0;
        }
        out.println(comp[newParent]);
      } else {
        out.println(comp[parentA]);
      }
    }
  }
}
