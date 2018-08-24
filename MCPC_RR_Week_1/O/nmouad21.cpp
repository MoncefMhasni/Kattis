#include<bits/stdc++.h>
using namespace std;

#define all(C) C.begin(),C.end()
#define csize(C) int(C.size())
typedef long long int Long;
typedef long double Double;
const int MAXN = 1e5 + 5;

struct TNode {
  int cnt, nxt[26];
  TNode() { memset(nxt, -1, sizeof(nxt)); cnt = 0; }
};

int n;
char s[MAXN];
vector<TNode> trie(1);

int insert() {
  int cur = 0;
  for(int i = 0; s[i]; ++i) {
    int idx = s[i] - 'a';

    if(trie[cur].nxt[idx] == -1) {
      trie[cur].nxt[idx] = csize(trie);
      trie.emplace_back();
    }

    ++trie[cur].cnt;
    cur = trie[cur].nxt[idx];
  }

  return trie[cur].cnt++;
}

int main() {
  scanf("%d", &n);
  while(n--) {
    scanf("%s", s);
    printf("%d\n", insert());
  }
  return 0;
}