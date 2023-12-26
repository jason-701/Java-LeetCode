#include <iostream>
#include <numeric>
#include <vector>
#include <cmath>
#include <set>
#include <limits.h>
using namespace std;

class Solution {
public:

//  26 Dec 2023
    void moveZeroes(vector<int>& nums) {
        int index = 0;
        for (int i : nums)
        {
            if (i != 0)
            {
                nums[index++] = i;
            }
        }
        while (index < nums.size())
        {
            nums[index++] = 0;
        }
    }   
};

int main(int argc, char *argv[]){
    Solution solution;
    vector<int> test ={1,0,0};
    solution.moveZeroes(test);
    for (int i : test)
    {
        cout << i << ' ';
    }
    // cout << "result: " << res << endl;
    return 0;
}