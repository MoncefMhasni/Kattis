#include <iostream>
#include <algorithm>
#include <cmath>
#include <iomanip>
#include <cstring>

using namespace std;

const int N = 16;

const double inf = 1e18;

int n;
double m, dp[N][1 << N], v;
bool seen[N][1 << N];
int x[N], y[N], s[N];

const double eps = 1e-11;

inline long long sqr(int a) {
    return a * (long long) a;
}

double f(int mouse, int eaten) {
    if (eaten == (1 << n) - 1) return s[mouse]; // remaining time to move.
    if (seen[mouse][eaten]) return dp[mouse][eaten];

    double best = -inf;
    double multiplier = pow(m, __builtin_popcount(eaten));

    for (int target = 0; target < n; target++) {
        if ((1 << target) & eaten) continue;
        
        double t = sqrt(sqr(x[mouse] - x[target]) + sqr(y[mouse] - y[target])) / (v * multiplier);
        double blocker = min(f(target, eaten | (1 << target)) - t, (double) s[mouse]);
        best = max(best, blocker);
    }
    seen[mouse][eaten] = 1;
    return dp[mouse][eaten] = best;
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++)
        cin >> x[i] >> y[i] >> s[i];
    cin >> m;

    double lo = eps, hi = 1e12;
    int iterations = 70;
    double ret;
    while (iterations--) {
        memset(seen, 0, sizeof seen);
        v = (lo + hi) / 2;

        double best = -inf;
        for (int st = 0; st < n; st++) {
            double t = sqrt(sqr(x[st]) + sqr(y[st])) / v;
            double blocker = min(f(st, 1 << st) - t, (double) s[st]);
            best = max(best, blocker);
        }
        if (best >= 0.000) {
            hi = v;
            ret = v;
        } else lo = v;
    }

    cout << fixed << setprecision(9);
    cout << ret << endl;
    return 0;
}