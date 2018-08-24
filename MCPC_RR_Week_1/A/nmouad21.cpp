#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 1e6 + 5;

int n;
double dp[MAXN];

int main() {
  for(int i = 1; i < MAXN; ++i) {
    dp[i] = dp[i - 1] + log10(i);
  }
  
  while(scanf("%d", &n) == 1) {
    printf("%d\n", int(dp[n]) + 1);
  }

  return 0;
}