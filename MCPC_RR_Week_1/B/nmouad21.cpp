#include<bits/stdc++.h>
using namespace std;

typedef long long int Long;
typedef long double Double;
const int MAXN = 1e6 + 3;

char dp[MAXN];
int n, m, a[12];

int main() {
  while(scanf("%d", &n) == 1) {
    for(int i = 0; i <= n; ++i)
      dp[i] = 0;
    
    scanf("%d", &m);
    for(int i = 0; i < m; ++i) {
      scanf("%d", a + i);
      dp[a[i]] = 1;
    }

    for(int i = 0; i <= n; ++i)
      for(int j = 0; j < m; ++j)
        if(i + a[j] <= n)
          dp[i + a[j]] = dp[i + a[j]] || !dp[i];

    puts(dp[n] ? "Stan wins" : "Ollie wins");
  }

  return 0;
}

