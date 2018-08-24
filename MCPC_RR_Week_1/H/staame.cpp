#include <bits/stdc++.h>

using namespace std;

const int N = 1000010;
int n, g;
int a[N];
int pos[N];
int mn[N];

int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n;
    for(int i = 0; i < n; i++) {
        cin >> a[i];
        a[i]--;
        pos[a[i]] = i;
    }

    mn[n - 1] = pos[n - 1];
    for(int i = n - 2; i >= 0; i--)
        mn[i] = min(pos[i], mn[i + 1]);

    vector<int> sol;
    g = a[0];
    do {
        sol.push_back(g + 1);
        if(g == n - 1) break;
        else {
            g = a[ mn[ g + 1 ] ];
        }
    } while(1);

    cout << sol.size() << endl;
    for(auto x: sol) cout << x << " ";

    return 0;
}
