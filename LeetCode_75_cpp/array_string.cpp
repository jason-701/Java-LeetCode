#include <iostream>
#include <numeric>
using namespace std;

class Solution {
public:

// 16 Dec 2023
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
};

int main(int argc, char *argv[]){
    Solution solution;
    string str1 = "ABCABC";
    string str2 = "ABC";
    string res = solution.gcdOfStrings(str1,str2);
    cout << "result: " << res << endl;
    return 0;
}