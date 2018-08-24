#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 450;

map<int, int> f[MAXN]; // f[i] = factors of "i!"
void factorize(int n, map<int, int> &f) {
  for(int i = 2; i * i <= n && n > 1; ++i) {
    while(n % i == 0) {
      ++f[i];
      n /= i;
    }
  }
  if(n > 1) ++f[n];
}

int main() {
  for(int i = 2; i < MAXN; ++i) {
    factorize(i, f[i]);
    for(auto &e : f[i - 1])
      f[i][e.first] += e.second;
  }

  int n, k;
  while(scanf("%d%d", &n, &k) == 2) {
    map<int, int> ans = f[n];
    for(auto &e : f[k]) ans[e.first] -= e.second;
    for(auto &e : f[n-k]) ans[e.first] -= e.second;

    Long divs = 1;
    for(auto &e : ans)
      divs *= (e.second + 1);

    printf("%lld\n", divs);
  }

  return 0;
}