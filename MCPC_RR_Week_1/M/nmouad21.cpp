#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 1e5 + 5;

char s[MAXN][30], u[30], v[30], vis[MAXN];
int n, e;
vector<int> a[MAXN];
map<string, int> m;

bool dfs(int u, int cl) {
  vis[u] = cl;
  for(int v : a[u]) {
    if(!vis[v]) {
      if(!dfs(v, cl ^ 1)) {
        return false;
      }
    } else if(vis[v] != (cl ^ 1)) {
      return false;
    }
  }

  return true;
}

int main() {
  scanf("%d", &n);
  for(int i = 1; i <= n; ++i) {
    scanf("%s", s[i]);
    m[s[i]] = i;
  }

  scanf("%d", &e);
  while(e--) {
    scanf("%s%s", u, v);
    int x = m[u], y = m[v];
    a[x].push_back(y);
    a[y].push_back(x);
  }
  
  for(int i = 1; i <= n; ++i) {
    if(!vis[i] && !dfs(i, 2)) {
      return 0 * puts("impossible");
    }
  }

  bool first_time_please_be_gentle = true;
  for(int i = 1; i <= n; ++i) {
    if(vis[i] == 2) {
      if(first_time_please_be_gentle) first_time_please_be_gentle = false;
      else putchar(' ');
      printf("%s", s[i]);
    }
  }

  puts("");
  first_time_please_be_gentle = true;
  for(int i = 1; i <= n; ++i) {
    if(vis[i] != 2) {
      if(first_time_please_be_gentle) first_time_please_be_gentle = false;
      else putchar(' ');
      printf("%s", s[i]);
    }
  }

  return 0;
}