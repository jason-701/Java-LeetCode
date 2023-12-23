#include <iostream>
#include <numeric>
#include <vector>
#include <cmath>
#include <set>
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

//  22 Dec 2023
    string reverseVowels(string s) {
        if (s.length()==1)
        {
            return s;
        }
        set<char> vowels = {'a','e','i','o','u','A','E','I','O','U'};
        int left = 0;
        int right = s.length()-1;
        char temp;
        while (left<right)
        {
            if (vowels.count(s[left])==0)
            {
                left++;
                continue;
            }
            if (vowels.count(s[right])==0)
            {
                right--;
                continue;
            }
            temp = s[left];
            s[left]=s[right];
            s[right]=temp;
            left++;
            right--;
        }
        return s;
    }

//  22 Dec 2023
    string reverseWords(string s) {
        if (s.length() == 1) {
            return s;
        }

        std::string res;
        int end = static_cast<int>(s.length()) - 1;

        // Skip trailing spaces
        while (end >= 0 && s[static_cast<size_t>(end)] == ' ') {
            end--;
        }

        int start = end;

        while (start >= 0) {
            // Find the start of the word
            while (start >= 0 && s[static_cast<size_t>(start)] != ' ') {
                start--;
            }

            // Check for underflow before using start + 1
            if (start >= 0) {
                // Append the word to the result
                res += s.substr(static_cast<size_t>(start + 1), static_cast<size_t>(end - start));
                // Append a space
                res += ' ';
            }
            if (start == -1)
            {
                cout << end << endl;
                res += s.substr(0,end+2);
            }

            // Move to the next word
            end = start;

            // Skip trailing spaces
            while (end >= 0 && s[static_cast<size_t>(end)] == ' ') {
                end--;
            }

            start = end;
        }

        // Remove the trailing space before returning
        while (res[res.length()-1]==' ')
        {
            res = res.substr(0,res.length()-1);
        }
        return res;
    }

//  23 Dec 2023
    vector<int> productExceptSelf(vector<int>& nums) {
        int size = nums.size();

        //  Array of product of numbers to the left of current index
        int leftProduct[size];
        leftProduct[0] = 1;
        
        //  Array of product of numbers to the right of current index
        int rightProduct[size];
        rightProduct[size-1] = 1;
        

        for (int i = 1; i < nums.size(); i++)
        {
            leftProduct[i] = leftProduct[i-1]*nums[i-1];
        }

        for (int i = size-2; i >= 0; i--)
        {
            rightProduct[i] = rightProduct[i+1]*nums[i+1];
        }
        
        vector<int> res;
        for (int i = 0; i < size; i++)
        {
            res.push_back(leftProduct[i]*rightProduct[i]);
        }
        return res;

    }
};

int main(int argc, char *argv[]){
    Solution solution;
    vector<int> test ={1,2,3,4,5};
    vector<int> res = solution.productExceptSelf(test);
    // cout << "result: " << res << endl;
    for (int &num : res)
    {
        cout << num << "   ";
    }
    return 0;
}