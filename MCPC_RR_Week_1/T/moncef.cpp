/*
Solution by Moncef MHASNI
*/
#include <bits/stdc++.h>
using namespace std;
const int NMAX = 15;
const double inf = 1e20;
class pos{
public :
    double x,y;
    pos(){x= 0,y= 0;}
    pos(double a,double b){x= a,y=b;}
    double dist(const pos a){
        return sqrt((a.x-x)*(a.x-x) + (a.y-y)*(a.y-y));
    }
};
double m;
vector<pos> M;
vector<double> s;
int n;
bool ok(double v){
    vector<vector<double>>dp(1<<NMAX,vector<double>(NMAX,inf));
    for(int state=1;state<(1<<n);state++){
        if(__builtin_popcount(state)==1)
            for(int j=0;(state>>j);j++) if(1&(state>>j)){
                dp[state][j] = M[j].dist(pos(0,0))/v;
                if(dp[state][j]>s[j])return false;
                break;
                }
        for(int j=0;j<n;j++){
            if(dp[state][j]>=inf)continue;
            for(int k=0;k<n;k++){
                if(k==j || (state>>k)&1 )continue;
                double dist = dp[state][j] + M[j].dist(M[k])/(v*pow(m,__builtin_popcount(state)));
                if(dist>s[k])continue;
                int newstate = state|(1<<k);
                dp[newstate][k] = min(dist,dp[newstate][k] );
            }
        }
    }
    for(int i = 0;i<n;i++){
        if(dp[(1<<n)-1][i]<s[i])return true;
    }
    return false;
}
int main(){
    cin>>n;
    M.resize(n);s.resize(n);
    for(int i=0;i<n;i++)cin>>M[i].x>>M[i].y>>s[i];
    cin>>m;
    double sum = 0,VM=0,Vm=0,v=0;
    pos last(0,0);
    for(int i = 0;i<n;i++){
            sum += last.dist(M[i])/pow(m,i);
            last = M[i];
            VM = max(VM,sum/s[i]);
    } 
    while(fabs(VM-Vm)>1e-3){
        v = 0.5*(VM+Vm);
        if(ok(v))VM=v;
        else Vm = v;
    }
    cout.precision(10);
    cout<<fixed<<VM<<endl;
}
