#include <bits/stdc++.h>

using namespace std;

const int N = 110;

string p, s;
int n;
int dp[N][N];

int f(int i, int j) {
    if(j == s.size()) {
        if(i == p.size()) return 1;
        return 0;
    }

    if(i == p.size()) {
        if(j == s.size()) return 1;
        return 0;
    }

    if(dp[i][j] != -1)
        return dp[i][j];

    int ans;

    if(p[i] != '*') {
        if(p[i] != s[j]) ans = 0;
        else ans = f(i + 1, j + 1);
    }
    else {
        ans = f(i, j + 1) | f(i + 1, j + 1) | f(i + 1, j);
    }

    return dp[i][j] = ans;
}

int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> p;
    cin >> n;
    while(n--) {
        cin >> s;
        memset(dp, -1, sizeof(dp));
        if(f(0, 0)) cout << s << endl;
    }

    return 0;
}
