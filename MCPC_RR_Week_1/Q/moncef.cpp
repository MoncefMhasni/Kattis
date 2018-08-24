#include<bits/stdc++.h>
#define xx 2000003
char a[xx];
using namespace std;
bool equals(int s,int e,int x){
    for(int i=0;i<e;i++)if(a[s+i]!=a[x+i])return false;
    return true;
}
int solve(){
    int n=strlen(a);int ans=1;
    for(int i=2;i<=n;i++){
            if(n%i)continue;
        int j=1;
       while(j<i && equals(0,n/i,j*(n/i))){
            j++;
        }
        if(j==i)ans = i;
    }
    return ans;
}
int main(){
   while(scanf("%s",a)){
        if(a[0]=='.')break;
    cout<<solve()<<endl;
    }

}