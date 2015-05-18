#include <iostream>
#include <cstdio>
#include <algorithm>
#include <map>
using namespace std;

typedef long long LL;
int N;
struct node
{
    LL x,y;
}Q[2048];

int Zure(const LL &x,const LL &y)
{
    if(x>=0){
        if(y>=0) return 0;
        return 3;
    }
    else{
        if(y>=0) return 1;
        return 2;
    }
}

bool cmp(const node &A,const node &B)
{
    int Za = Zure(A.x,A.y),Zb = Zure(B.x,B.y);
    if(Za==Zb)
    {
        return A.x*B.y-A.y*B.x>0;
    }
    return Za<Zb;
}

int main()
{
    int casT;
    scanf("%d",&casT);
    while(casT--)
    {
        scanf("%d",&N);
        for(int i=0;i<N;i++) scanf("%lld",&Q[i].x);
        for(int j=0;j<N;j++) scanf("%lld",&Q[j].y);
        sort(Q,Q+N,cmp);
        for(int i=0;i<N;i++) Q[i+N]=Q[i];
        LL ans = 0;
        for(int s=0;s<N;s++)
        {
            LL sx = 0;
            LL sy = 0;
            for(int e=s;e<s+N;e++)
            {
                if(Q[s].x*Q[e].y-Q[s].y*Q[e].x>=0)
                {
                    sx+=Q[e].x;
                    sy+=Q[e].y;
                    ans = max(ans,sx*sx+sy*sy);
                }
                else break;
            }
        }
        printf("%lld\n",ans);
    }
    return 0;
}
