#include <iostream>
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
};

int main(int argc, char *argv[]){
    Solution solution;
    string word1 = "ab";
    string word2 = "pqrs";
    string result = solution.mergeAlternately(word1, word2);
    cout << "Merged String: " << result << endl;
    return 0;
}