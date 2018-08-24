#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 1e5 + 5;

int t, n, id, par[MAXN << 1], sz[MAXN << 1];
map<string, int> m;
char u[MAXN][10], v[MAXN][10];

void clear() {
  id = 0;
  m.clear();
}

int find(int x) { return x == par[x] ? x : (par[x] = find(par[x])); }
int join(int x, int y) {
  x = find(x); y = find(y);
  if(x == y) return sz[x];

  if((rand()&1)^(x&1)) swap(x, y);
  par[x] = y;
  sz[y] += sz[x];

  return sz[y];
}

int main() {
  scanf("%d", &t);
  while(t--) {
    clear();

    scanf("%d", &n);
    for(int i = 0; i < n; ++i) {
      scanf("%s%s", u[i], v[i]);
      m[u[i]]; m[v[i]];
    }

    for(auto &e : m) {
      e.second = ++id;
      par[id] = id;
      sz[id] = 1;
    }

    for(int i = 0; i < n; ++i) {
      printf("%d\n", join(m[u[i]], m[v[i]]));
    }
  }

  
  return 0;
}