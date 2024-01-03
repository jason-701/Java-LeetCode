#include <iostream>
#include <queue>
using namespace std;

//  3 Jan 2024
class RecentCounter {
public:
    queue<int> counter;
    RecentCounter() {
    }
    
    int ping(int t) {
        counter.push(t);
        if (counter.size()==1)
        {
            return 1;
        }
        else
        {
            while (counter.front() < (t-3000) && !counter.empty())
            {
                counter.pop();
            }
            return counter.size();
        }
    }
};

//  3 Jan 2024
class Dota{
public:
    string predictPartyVictory(string senate) {
        queue<int> rad;
        queue<int> dir;
        for (int i = 0; i < senate.length(); i++){
            if (senate[i]=='R'){
                rad.push(i);
            }
            else{
                dir.push(i);
            }
        }
        int curIndex = senate.length();
        while (!rad.empty() && !dir.empty())
        {
            //  Whoever that comes first has the ability to vote of the other person. After voting, it can vote again in the next round, hence the increased index
            if (rad.front() > dir.front())
            {
                dir.push(dir.front()+curIndex);
                dir.pop();
                rad.pop();
            }
            else{
                rad.push(rad.front()+curIndex);
                rad.pop();
                dir.pop();
            }
            curIndex++;
        }
        
        return rad.empty() ? "Dire" : "Radiant";
    }
}
