/*
	Solution by Moncef MHASNI
*/
#include <bits/stdc++.h>
#define ll long long
using namespace std;
vector<int> primes;
const int lim =440;
class integer{
public :
    unordered_map<int,int> m;
    integer(){};
    integer(unordered_map<int,int> x){
        this->m = x;
    }
    integer(int x){
        m.clear();
        for(auto i:primes){
            if(i>x) break;
            int exp = 0;
            while(x%i==0)x/=i,exp++;
            if(exp)m[i]=exp;
        }
    }
    integer operator* (integer v){
        integer tmp(m);
        for(auto i:v.m)tmp.m[i.first]+= i.second;
        return tmp;
    }
    integer operator/ (integer v){
        integer tmp(m);
        for(auto i:v.m)tmp.m[i.first]-= i.second;
        return tmp;
    }
    ll cc(){
        ll ans = 1;
        for(auto i:m) ans*=(1+i.second);
        return ans;
    }
};
unordered_map<int,integer> id;
void gen(){
    primes.push_back(2);
    for(int i=3;i<lim;i++){
        bool ok  = 1;
        for(int j=2;j*j<=i;j++)
        if(i%j==0){ok=0;break;}
        if(ok)primes.push_back(i);
    }
    for(int i=1;i<lim;i++)
        id[i] = integer(i);


}
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);cout.tie(0);
    int n,k;
    gen();
    integer ans[lim][lim];
    ans[0][0]=integer(1);
    for( int i = 1; i < lim;i++){
        ans[i][1] = ans[i][i] = id[i];
        ans[i][i-1] = id[i];
        for( int j = 0; j < i-1  ;j++)  ans[i][j] = (ans[i-1][j] * id[i])/id[(i-j)];
    }
    while(cin>>n>>k){
        cout<<(n==k ? 1 : ans[n][k].cc())<<endl;
    }
}