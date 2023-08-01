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
    
    
test = Solution()
print(test.combine(4,3))