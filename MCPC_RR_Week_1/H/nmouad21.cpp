#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 1e6 + 5;

int n, last;
vector<int> ans;

int main() {
  scanf("%d", &n);
  for(int i = 1, a; i <= n; ++i) {
    scanf("%d", &a);
    if(i == 1 || a > last) {
      ans.push_back(a);
      last = a;
    }
  }

  printf("%d\n", csize(ans));
  for(int i = 0; i < csize(ans); ++i) {
    printf("%d%c", ans[i],  " \n"[i + 1 == csize(ans)]);
  }
  return 0;
}