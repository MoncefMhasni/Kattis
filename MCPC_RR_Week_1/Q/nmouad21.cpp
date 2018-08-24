#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 2e6 + 6;

char s[MAXN];
int n, F[MAXN];

int main() {
  while(scanf("%s", s) && s[0] != '.') {
    n = strlen(s);

    for(int i = 1, border = 0; i < n; ++i) {
      while(border > 0 && s[border] != s[i])
        border = F[border - 1];
      if(s[i] == s[border]) ++border;
      F[i] = border;
    }

    printf("%d\n", n / (n - F[n - 1]));
  }
  
  return 0;
} 