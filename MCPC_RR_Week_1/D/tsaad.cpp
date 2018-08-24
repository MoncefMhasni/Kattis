#include <bits/stdc++.h>
 
using namespace std;
 
int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
 
    string s;
    cin >> s;
    int n = (int)s.size();
 
    for(int i = 1; i <= n; i++) {
        if((n % i) != 0) continue;
 
        int ok = 1;
        deque<char> Q;
        for(int j = 0; j < i; j++)
            Q.push_back(s[j]);
 
        for(int j = i; j < n; j += i) {
            char last = Q.back();
            Q.pop_back();
            Q.push_front(last);
 
            deque<char> T;
            for(int k = 0; k < i; k++)
                T.push_back(s[k + j]);
 
            if(Q != T)
                ok = 0;
        }
 
        if(ok) {
            cout << i << endl;
            exit(0);
        }
    }
 
    return 0;
}