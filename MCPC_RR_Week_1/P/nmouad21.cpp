#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 5e4 + 5;


int n, m, f, s, t;
Long D[MAXN][2];
vector<pair<int, int>> a[MAXN];
set<pair<Long, pair<bool, int>>> S; // { cost, { used_flight, u } }

Long solve() {
  memset(D, 0x3f, sizeof(D));
  
  D[s][0] = 0;
  S.insert({ 0LL, { false, s } });
  while(S.size()) {
    Long cst = S.begin()->first;
    bool used_flight = S.begin()->second.first;
    int u = S.begin()->second.second;
    S.erase(S.begin());

    for(auto &e : a[u]) {
      int v = e.first;
      int w = e.second;

      if(w > 0) {
        if(D[v][used_flight] > cst + w) {
          S.erase({ D[v][used_flight], { used_flight, v } });
          D[v][used_flight] = cst + w;
          S.insert({ D[v][used_flight], { used_flight, v } });
        }
      } else if(!used_flight) { // w == 0
        if(D[v][1] > cst) {
          S.erase({ D[v][1], { 1, v } });
          D[v][1] = cst;
          S.insert({ D[v][1], { 1, v } });
        }
      }
    }
  }

  return min(D[t][0], D[t][1]);
}

int main() {
  scanf("%d%d%d%d%d", &n, &m, &f, &s, &t);
  for(int i = 0, u, v, w; i < m; ++i) {
    scanf("%d%d%d", &u, &v, &w);
    a[u].emplace_back(v, w);
    a[v].emplace_back(u, w);
  }
  for(int i = 0, u, v; i < f; ++i) {
    scanf("%d%d", &u, &v);
    a[u].emplace_back(v, 0);
  }
  
  printf("%lld\n", solve());
  return 0;
}