#include <bits/stdc++.h>

using namespace std;

const int N = 60;

string s, t;
int n, m;
int dp[N][N];

/* Max common subsequence */
int f(int i, int j) {
    if(i >= n || j >= m) return 0;

    if(dp[i][j] != -1)
        return dp[i][j];

    int ans = 0;
    if(s[i] == t[j])
        ans = f(i + 1, j + 1) + 1;
    else {
        ans = max(f(i + 1, j), f(i, j + 1));
    }

    return dp[i][j] = ans;
}

int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> s;
    for(char a = 'a'; a <= 'z'; a++)
        t += a;

    n = s.size();
    m = t.size();

    memset(dp, -1, sizeof(dp));
    cout << (26 - f(0, 0)) << endl;

    return 0;
}
