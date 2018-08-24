/*
	Solution by Moncef MHASNI
*/
#include<bits/stdc++.h>
#define pb push_back
#define w first
#define ll long long
#define node second
const ll inf = LLONG_MAX/2;
const int NMAX = 5e4+5;
using namespace std;
class edge{
public :
    ll d;int node;bool fly;
    edge(){
        d = inf;fly =0;
    }
    edge(ll dist,int u,bool f){
        d = dist;
        node = u;
        fly = f;
    }
};
int main(){
    int n,m,f,s,t;
    scanf("%d %d %d %d %d",&n,&m,&f,&s,&t);
    vector<vector<pair<ll,int>>> g(n);
    vector<vector<int>>h(n);
    for(int i=0;i<m;i++){
        int u,v,w;
        scanf("%d %d %d",&u,&v,&w);
        g[u].pb({w,v});
        g[v].pb({w,u});
    }
    for(int i=0;i<f;i++){
        int u,v;
        scanf("%d %d",&u,&v);
        h[u].pb(v);
    }
    auto cmp = [](edge a,edge b){return a.d>b.d;};
    priority_queue<edge,vector<edge>,decltype(cmp)> pq(cmp);
    pq.push(edge(0,s,0));
    ll dist[NMAX];
    for(int i=0;i<NMAX;i++)dist[i]=inf;
    dist[s]=0;
    while(!pq.empty()){
        edge e = pq.top();pq.pop();
        if(dist[e.node]>=inf)break;
        for(auto i:g[e.node]){
            ll nd = dist[e.node] + i.w;
            if(nd < dist[i.node]){
                dist[i.node] = nd;
                pq.push(edge(nd,i.node,e.fly));
            }
        }
        if(e.fly)continue;
        for(auto i:h[e.node]){
            if(dist[e.node]<dist[i]){
                dist[i]  = dist[e.node];
                pq.push(edge(dist[e.node],i,1));
            }
        }
    }

    printf("%lld\n",dist[t]);
    return 0;
}