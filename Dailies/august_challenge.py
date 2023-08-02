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
        
    
test = Solution()
print(test.permute([1,2,3]))