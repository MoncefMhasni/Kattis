#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 1e2 + 5;

string s;
int n;

string mutate(string s) {
  char last = s.back();
  s.pop_back();
  return last + s;
}

bool check(int L) {
  int sz = n / L;
  for(int i = 1; i < sz; ++i) {
    if(mutate(s.substr((i - 1) * L, L)) != s.substr(i * L, L)) {
      return false;
    }
  }
  return true;
}

int main() {
  cin >> s;
  n = s.length();

  for(int i = 1; i < n; ++i) {
    if(n % i == 0 && check(i)) {
      return 0 * printf("%d\n", i);
    }
  }

  printf("%d\n", csize(s));
  return 0;
}