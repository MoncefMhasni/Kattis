#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 1e5 + 5;

int main() {
  string s;
  cin >> s;

  set<char> lis;
  for(char ch : s) {
    auto p = lis.insert(ch);
    if(!p.second) continue;
    
    auto it = next(p.first);
    if(it != lis.end()) {
      lis.erase(it);
    }
  }

  cout <<  26 - csize(lis);
  return 0;
}