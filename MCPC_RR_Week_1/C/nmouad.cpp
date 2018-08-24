#include<bits/stdc++.h>
using namespace std;

#define csize(C) int(C.size())
#define all(C) C.begin(),C.end()
typedef long long int Long;
typedef long double Double;
const int MAXN = 1e6 + 6;

char cmd[5];
int n, q, L, R, B[MAXN], a[MAXN];

void update(int i, int v) {
  while(i <= n) {
    B[i] += v;
    i += i & -i;
  }
}
int get(int i) {
  int s = 0;
  while(i > 0) {
    s += B[i];
    i -= i & -i;
  }
  return s;
}
int query(int L, int R) {
  return get(R) - get(L - 1);
}

int main() {
  scanf("%d%d", &n, &q);
  while(q--) {
    scanf("%s%d", cmd, &L);
    if(cmd[0] == 'F') {
      update(L, a[L] ? -1 : 1);
      a[L] ^= 1;
    } else {
      scanf("%d", &R);
      printf("%d\n", query(L, R));
    }
  }
  
  return 0;
}

