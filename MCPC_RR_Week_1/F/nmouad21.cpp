#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 8e2 + 5;

int t, n, a[MAXN], b[MAXN];

int main() {
  scanf("%d", &t);
  for(int tc = 1; tc <= t; ++tc) {
    printf("Case #%d: ", tc);

    scanf("%d", &n);
    for(int i = 0; i < n; ++i) {
      scanf("%d", a + i);
    }
    for(int i = 0; i < n; ++i) {
      scanf("%d", b + i);
    }

    sort(a, a + n);
    sort(b, b + n);
    reverse(b, b + n);

    Long ans = 0;
    for(int i = 0; i < n; ++i) {
      ans += a[i] * Long(b[i]);
    }

    printf("%lld\n", ans);
  }
  return 0;
}