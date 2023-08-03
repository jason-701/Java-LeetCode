class Solution(object):
    
    #   1 August 2023
    #   Combinations
    def combineBackTracking(self, n, k, index, result, temp):
        
        if len(temp)==k:
            result.append(temp[:])
            return
        
        for i in range(index, n+1):
            temp.append(i)
            self.combineBackTracking(n, k, i+1, result, temp)
            temp.pop()
    
    def combine(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: List[List[int]]
        """
        result=[]
        self.combineBackTracking(n, k, 1, result, [])
        return result
    
    #   2 August 2023
    #   Permuations
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        result = []
        self.permuteBacktracking(nums, result, [])
        return result
        
    def permuteBacktracking(self, nums, result, temp):
            
            if len(temp)==len(nums):
                result.append(temp[:])
                return
            
            for i in range(len(nums)):
                if nums[i] in temp:
                    continue
                temp.append(nums[i])
                self.permuteBacktracking(nums, result, temp)
                temp.pop()
        
    #   3 August 2023
    #   Letter combinations of a phone number
    def letterCombinations(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        if len(digits)==0:
            return []
        numberMapping = {
            2 : "abc",
            3 : "def",
            4 : "ghi",
            5 : "jkl",
            6 : "mno",
            7 : "pqrs",
            8 : "tuv",
            9 : "wxyz"
        }
        result = []
        self.letterCombinationsBacktracking(digits, numberMapping, 0, result, "")
        return result
        
    def letterCombinationsBacktracking(self, digits, numberMapping, index, result, temp):
        if len(temp)==len(digits):
            result.append(temp[:])
            return
        
        for i in range(index, len(digits)):
            for j in range(len(numberMapping[int(digits[i])])):
                temp += numberMapping[int(digits[i])][j]
                self.letterCombinationsBacktracking(digits, numberMapping, i+1, result, temp)
                temp = temp[:-1]
test = Solution()
print(test.letterCombinations("23"))