class Solution(object):
    #   1 August 2023
    #   Combinations
    def combineBackTracking(self, n, k, index, result, temp):
        if len(temp) == k:
            result.append(temp[:])
            return

        for i in range(index, n + 1):
            temp.append(i)
            self.combineBackTracking(n, k, i + 1, result, temp)
            temp.pop()

    def combine(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: List[List[int]]
        """
        result = []
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
        if len(temp) == len(nums):
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
        if len(digits) == 0:
            return []
        numberMapping = {
            2: "abc",
            3: "def",
            4: "ghi",
            5: "jkl",
            6: "mno",
            7: "pqrs",
            8: "tuv",
            9: "wxyz",
        }
        result = []
        self.letterCombinationsBacktracking(digits, numberMapping, 0, result, "")
        return result

    def letterCombinationsBacktracking(
        self, digits, numberMapping, index, result, temp
    ):
        if len(temp) == len(digits):
            result.append(temp[:])
            return

        for i in range(index, len(digits)):
            for j in range(len(numberMapping[int(digits[i])])):
                temp += numberMapping[int(digits[i])][j]
                self.letterCombinationsBacktracking(
                    digits, numberMapping, i + 1, result, temp
                )
                temp = temp[:-1]

    #   4 August 2023
    #   Word break
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        #   Here is the intuition: from the beginning of the string to index i, and a certain index j that is between 0 ad i,
        #   if str[:j] is true and str[j:i] is in the wordDict, then dp[i] is True
        #   E.g. "leetcodedaily", wordDict = ["leet", "code", "daily"]
        #   suppose we're processing until the "e" in "code", i = 8, and when j = 4, dp[4] is True and "code" is in the wordDict, hence dp[i] is true
        strLen = len(s) + 1
        dp = [False] * strLen
        dp[0] = True
        for i in range(1, strLen):
            for j in range(i):
                if dp[j] and s[j:i] in wordDict:
                    dp[i] = True
        return dp[-1]

    #   5 August 2023
    #   Unique binary search trees II
    class TreeNode(object):
        def __init__(self, val=0, left=None, right=None):
            self.val = val
            self.left = left
            self.right = right

    def generateTrees(self, n):
        """
        :type n: int
        :rtype: List[TreeNode]
        """
        return self.generateTreesHelper(1, n)

    def generateTreesHelper(self, start, end):
        if start > end:
            return [None]
        result = []
        for i in range(start, end + 1):
            left = self.generateTreesHelper(start, i - 1)
            right = self.generateTreesHelper(i + 1, end)
            for l in left:
                for r in right:
                    root = self.TreeNode(i)
                    root.left = l
                    root.right = r
                    result.append(root)
        return result

    #   6 August 2023
    #   Number of music playlists
    def numMusicPlaylists(self, n, goal, k):
        """
        :type n: int
        :type goal: int
        :type k: int
        :rtype: int
        """
        #   dp[i][j] represents the number of playlists of length i that have exactly j unique songs
        #   Base case: dp[0][0] = 1
        #   All i < j = 0 since there's less slots than the number of songs
        dp = [[0] * (n + 1) for i in range(goal + 1)]
        dp[0][0] = 1
        for i in range(1, goal + 1):
            for j in range(1, n + 1):
                #   dp[i-1][j-1] used to calculate the number of choices with a new song, hence n-(j-1)
                #   dp[i-1][j] used to calculate the number of choices with an old song
                #   but the constraint mentions each song can only repeat every k other songs, hence max(j-k, 0)
                dp[i][j] += dp[i - 1][j - 1] * (n - (j - 1)) + dp[i - 1][j] * max(
                    j - k, 0
                )
        return dp[goal][n] % (10**9 + 7)

    #   7 August 2023
    #   Search a 2D matrix
    def searchMatrix(self, matrix, target):
        #   I copied the solution because I didn't have any time to do it
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        if not matrix:
            return False
        m, n = len(matrix), len(matrix[0])
        left, right = 0, m * n - 1
        while left <= right:
            mid = (left + right) // 2
            mid_row, mid_col = divmod(mid, n)
            if matrix[mid_row][mid_col] == target:
                return True
            elif matrix[mid_row][mid_col] < target:
                left = mid + 1
            else:
                right = mid - 1
        return False

    #   8 August 2023
    #   Search in rotated sorted array
    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        left = 0
        right = len(nums) - 1
        while left < right:
            mid = (left + right) // 2
            if nums[mid] == target:
                return mid
            if (
                nums[mid] >= nums[left]
            ):  # Left half is sorted, so we check if target is in left half
                if nums[left] <= target <= nums[mid]:
                    right = mid
                else:
                    left = mid + 1
            else:  # Right half is sorted, so we check if target is in right half
                if nums[mid] <= target <= nums[right]:
                    left = mid + 1
                else:
                    right = mid
        if nums[left] == target:
            return left
        else:
            return -1

    #   9 August 2023
    #   Minimize tha maximum difference of pairs
    def minimizeMax(self, nums, p):
        """
        :type nums: List[int]
        :type p: int
        :rtype: int
        """

        nums.sort()
        left = 0
        right = nums[-1]
        while left < right:
            mid = (left + right) // 2
            count = 0
            i = 0
            while i < len(nums) - 1:
                if abs(nums[i] - nums[i + 1]) <= mid:
                    count += 1
                    i += 2
                else:
                    i += 1
            if count >= p:
                right = mid
            else:
                left = mid + 1
        return left


test = Solution()
arr = [3, 3, 5, 1, 0, 5, 6, 6]
print(test.minimizeMax(arr, 4))
