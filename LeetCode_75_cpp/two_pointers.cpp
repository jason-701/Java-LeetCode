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

//  27 Dec 2023
    bool isSubsequence(string s, string t) {
       //   Whether s is a subsequence of t
       
       if (s.length() > t.length())
       {
        return false;
       }

       int sPointer, tPointer;
       sPointer = tPointer = 0;

       while (sPointer < s.length())
       {
            if (s[sPointer] ==t[tPointer])
            {
                sPointer++;
            }

            tPointer++;

            if (tPointer > t.length())
            {
                return false;
            }
       }
       return true;
    }

//  27 Dec 2023
    int maxArea(vector<int>& height) {
        int leftPointer = 0;
        int rightPointer = height.size()-1;
        int curMax = 0;
        int temp = 0;
    
        while (leftPointer < rightPointer)
        {
            temp = (rightPointer - leftPointer) * min(height[leftPointer],height[rightPointer]);
            if (temp > curMax)
            {
                curMax = temp;
            }
            if (height[leftPointer] < height[rightPointer])
            {
                leftPointer++;
            }
            else
            {
                rightPointer--;
            }
        }
        return curMax;
    }
};

int main(int argc, char *argv[]){
    Solution solution;
    vector<int> height = {1,1};
    int res = solution.maxArea(height);
    cout << "result: " << res << endl;
    return 0;
}