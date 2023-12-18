#include <iostream>
#include <numeric>
#include <vector>
#include <cmath>
using namespace std;

class Solution {
public:

//  16 Dec 2023
    string mergeAlternately(string word1, string word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        string res;
        int index = 0;
        while (index < len1 && index < len2)
        {
            res += word1[index];
            res += word2[index];
            index++;
        }

        if (index < len1)
        {
            res += word1.substr(index,len1-index);
        }
        if (index < len2)
        {
            res += word2.substr(index,len2-index);
        }

        return res;
    }

//  16 Dec 2023
    string gcdOfStrings(string str1, string str2) {
        //  The length of the greatest common divisor has to the same as the gcd between the lengths of the 2 strings
        int len = gcd(str1.length(),str2.length());
        string res;
        for (int i = 0; i < len; i++)
        {
            if (str1[i] != str2[i])
            {
                return "";
            }
            res += str1[i];
        }

        string test = str1 + str2;
        for (int i = 0; i < test.length(); i += len)
        {
            if (test.substr(i,len) != res)
            {
                return "";
            }
        }
        return res;

    }

//  18 Dec 2023
    vector<bool> kidsWithCandies(vector<int>& candies, int extraCandies) {
        vector<bool> res;

        int max = 0;
        for (int &candy : candies)
        {
            if (candy > max)
            {
                max = candy;
            }
        }

        for (int &candy : candies)
        {
            if (candy+extraCandies >= max)
            {
                res.push_back(true);
            }
            else {
                res.push_back(false);
            }
        }
        return res;
    }

//  18 Dec 2023
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        int count = 0;
        if (flowerbed.size()==1 && flowerbed[0] == 0){
            return (n<=1) ? true : false;
        }

        if (n > ceil(flowerbed.size()/2.0))
        {
            return false;
        }

        for (size_t i = 0; i < flowerbed.size(); i++)
        {
            // current location is occupied
            if (flowerbed[i]==1)
            {
                continue;
            }

            // the previous location is occupied
            if (i!=0 && flowerbed[i-1]==1)
            {
                continue;
            }

            // the next location is occupied
            if (i<flowerbed.size()-1 && flowerbed[i+1]==1)
            {
                continue;
            }

            // at location 0, and the next location is empty
            if (i==0 && flowerbed[i+1]==0)
            {
                count++;
                continue;
            }

            // at location 1, and the previous location is empty, this means we would have planted the flower at the previous location
            if (i==1 && flowerbed[0]==0)
            {
                continue;
            }

            if (i>3 && flowerbed[i-2]==0 && flowerbed[i-3]==0 && flowerbed[i-4]==1)
            {
                count++;
                continue;
            }

            if (i>4 && flowerbed[i-2]==0 && flowerbed[i-3]==0 && flowerbed[i-4]==0 && flowerbed[i-5]==0)
            {
                count++;
                continue;
            }

            if (i==3 && flowerbed[i-2]==0 && flowerbed[i-3]==0)
            {
                count++;
                continue;
            }

            if (i==2 && flowerbed[0]==0)
            {
                count++;
                continue;
            }

            // 2 plots before is occupied and the previous plot isn't, this means you can plant the flower at the current plot
            if (i>1 && flowerbed[i-2]==1)
            {
                count++;
                continue;
            }
        }
        if (count >= n)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
};

int main(int argc, char *argv[]){
    Solution solution;
    vector<int> flowerbed = {1,0,0,0,0,0,0,1};
    int res = solution.canPlaceFlowers(flowerbed,2);
    cout << "result: " << res << endl;
    return 0;
}