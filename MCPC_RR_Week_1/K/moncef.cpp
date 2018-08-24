/*
Solution by Moncef MHASNI
*/
#include<bits/stdc++.h>
#define ll unsigned long long
using namespace std;
ll gcd(ll a,ll b){
    if(b==0)return a;
    return gcd(b,a%b);
}
ll pwr(ll a,ll b){
    return b==0 ? 1 : a*pwr(a,b-1);
}
ll nine[]={0,9,99,999,9999,99999,999999,9999999,99999999,999999999LL};
int main(){
    string in;
    while(cin >>in){
        if(in=="0")return 0;
        int m = 0;
        ll u=0;
        for(int i=2;in[i]!='.';i++)m++,u=u*10+(in[i]-'0');
        ll A = u,B = LLONG_MAX;
        for(int i=1;i<=m;i++){
            ll rep = u%pwr(10,i), a = (u-rep)/pwr(10,i), b = pwr(10,m-i)*nine[i];
            a = a*nine[i] + rep;
            int d = gcd(a,b);
            a /=d;b /=d;
            if(b<B)A=a,B=b;
        }
        printf("%lld/%lld\n",A,B);
    }
}
