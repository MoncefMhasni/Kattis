#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 1e5 + 5;

int t;
string alien, base, nxt;

int to_dec() {
  int ans = 0, pw = 1, baseSize = base.length();
  for(int i = alien.length() - 1; i >= 0; --i) {
      ans += base.find(alien[i]) * pw;
      pw *= baseSize;
  }
  return ans;
} 

int main() {
  cin >> t;
  for(int tc = 1; tc <= t; ++tc) {
    cin >> alien >> base >> nxt;

    string ans;
    int dec = to_dec();
    int nxt_sz = nxt.length();
    while(dec > 0) {
      ans += nxt[dec % nxt_sz];
      dec /= nxt_sz;
    }

    reverse(all(ans));
    cout << "Case #" << tc << ": " << ans << "\n";
  }
  return 0;
}


