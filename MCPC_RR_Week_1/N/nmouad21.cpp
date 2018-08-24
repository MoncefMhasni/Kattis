#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 1e3 + 5;
const int MOD = 1e9 + 7;

char fn[40], w[MAXN][40], used[MAXN];
int n, m[MAXN], len[MAXN], L;
Long dp[MAXN][MAXN];

int startsWith(int i, int fni) {
  for(int j = 0; w[i][j]; ++j)
    if(w[i][j] != fn[j + fni])
      return 0;
  return 1;
}

Long solve(int wi, int fni) {
  if(fni == L) return 1;
  if(dp[wi][fni] >= 0) return dp[wi][fni];
  if(fni + len[wi] > L) return 0;
  if(!startsWith(wi, fni)) return dp[wi][fni] = 0;
  if(fni + len[wi] == L) return m[wi];

  Long &ans = dp[wi][fni] = 0;

  fni += len[wi];
  for(int i = 0; i < n; ++i) {
    ans += m[wi] * solve(i, fni);
    ans %= MOD;
  }

  return ans;
}

int main() {
  memset(dp, -1, sizeof(dp));

  scanf("%d%s", &n, fn);
  L = strlen(fn);
  for(int i = 0; i < n; ++i) {
    scanf("%s%d", w[i], m + i);
    len[i] = strlen(w[i]);
  }

  Long ans = 0;
  for(int i = 0; i < n; ++i) {
    ans += solve(i, 0);
    ans %= MOD;
  }

  printf("%lld\n", ans);
  return 0;
}