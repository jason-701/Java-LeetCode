import heapq
import collections


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
        #   Key idea is that when we split the array into two halves, one half will always be sorted, so we check if target is in the sorted half
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

    #   10 August 2023
    #   Search in rotated sorted array II
    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: bool
        """
        #   similar to the search function on Aug 8th, except we have to check for duplicates
        if len(nums) == 1:
            return nums[0] == target

        left = 0
        right = len(nums) - 1
        while left < right:
            mid = (left + right) // 2
            if nums[mid] == target:
                return True
            if nums[mid] == nums[right] and nums[left] == nums[mid]:
                right -= 1
                left += 1
            elif nums[mid] >= nums[left]:
                if nums[left] <= target <= nums[mid]:
                    right = mid
                else:
                    left = mid + 1
            else:
                if nums[mid] <= target <= nums[right]:
                    left = mid + 1
                else:
                    right = mid
        return nums[left] == target

    #   11 August 2023
    #   Coin change II
    def change(self, amount, coins):
        """
        :type amount: int
        :type coins: List[int]
        :rtype: int
        """
        #   Sort coins array so that if coins[i] > amount, we can break out of the loop
        coins.sort()

        #   dp[i][j] where i represents what coin is in use and j represents the amount remaining
        dp = [[0] * (amount + 1) for i in range(len(coins))]
        for i in range(len(coins)):
            dp[i][0] = 1

        for i in range(len(coins)):
            if coins[i] > amount:
                return dp[i - 1][amount]
            for j in range(1, amount + 1):
                #   dp[coin][amount] = dp[prev coin][amount] + dp[coin][amount - coin]
                if i != 0:
                    if coins[i] <= j:
                        dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]]
                    else:
                        dp[i][j] = dp[i - 1][j]
                else:
                    dp[i][j] = dp[i][j - coins[i]]

        return dp[len(coins) - 1][amount]

    #   12 August 2023
    #   Unique paths II
    def uniquePathsWithObstacles(self, obstacleGrid):
        """
        :type obstacleGrid: List[List[int]]
        :rtype: int
        """
        rows = len(obstacleGrid)
        columns = len(obstacleGrid[0])
        dp = [[0] * columns for i in range(rows)]
        if obstacleGrid[0][0] != 1:
            dp[0][0] = 1
        #   dp[i][j] represents the number of ways to reach obstacleGrid[i][j], which is dp[i-1][j] + dp[i][j-1], but have to check for obstacles
        for i in range(rows):
            for j in range(columns):
                if i == 0 and j == 0:
                    continue
                if obstacleGrid[i][j] == 1:
                    dp[i][j] = 0
                elif i != 0 and j != 0:
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                elif (
                    i == 0
                ):  #  We're at row 0, so there is no moving up to a previous grid
                    dp[i][j] = dp[i][j - 1]
                else:  #   We're at column 0, so there is no moving left to a previous grid
                    dp[i][j] = dp[i - 1][j]
        return dp[-1][-1]

    #   13 August 2023
    #   Check if there is a valid partition for the array
    def validPartition(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        dp = [False] * len(nums)

        for i in range(1, len(dp)):
            if (
                i > 3
            ):  # Check for all possibilities of a valid partition. E.g [1,1,1,1,2,3], at the last element (i = 5), we see that [1,2,3] is valid, and dp[2] is True, so dp[i] is True
                dp[i] = (
                    (nums[i - 2] == nums[i - 1] == nums[i] and dp[i - 3])
                    or (nums[i - 1] == nums[i] and dp[i - 2])
                    or (
                        nums[i - 2] + 1 == nums[i - 1]
                        and nums[i - 1] + 1 == nums[i]
                        and dp[i - 3]
                    )
                )
            elif (
                i == 3
            ):  #  When there are 4 elements, only way of partitioning is divide it into 2 equal parts
                dp[i] = nums[i - 1] == nums[i] and dp[i - 2]
            elif (
                i == 2
            ):  #  When there are 3 elements, check whether they are increments of 1 or 3 duplicates, which will both be valid partitions
                dp[i] = (
                    nums[i - 2] == nums[i - 1] == nums[i]
                    or nums[i - 2] + 1 == nums[i - 1]
                    and nums[i - 1] + 1 == nums[i]
                )
            else:  # When there are 2 elements, check whether they are 2 duplicates, which will be a valid partition
                dp[i] = nums[i - 1] == nums[i]
        return dp[-1]

    #   14 Aug 2023
    #   Kth largest element in an array
    # def findKthLargest(self, nums, k):
    #     """
    #     :type nums: List[int]
    #     :type k: int
    #     :rtype: int
    #     """
    #     return self.quickSortModified(nums, 0, len(nums) - 1, len(nums) - k)

    # def quickSortModified(self, nums, low, high, targetIndex):
    #     index = self.partition(nums, low, high)
    #     if index == targetIndex:
    #         return nums[index]
    #     elif index < targetIndex:
    #         return self.quickSortModified(nums, index + 1, high, targetIndex)
    #     else:
    #         return self.quickSortModified(nums, low, index - 1, targetIndex)

    # def partition(self, nums, low, high):
    #     pivot = nums[high]
    #     i = low - 1
    #     for j in range(low, high):
    #         if nums[j] <= pivot:
    #             i += 1
    #             (nums[i], nums[j]) = (nums[j], nums[i])
    #     (nums[i + 1], nums[high]) = (nums[high], nums[i + 1])
    #     return i + 1

    #   Turns out you can just use the built in min heap
    #   Keep adding elements into the heap and put when heap size exceed the target
    def findKthLargest(self, nums, k):
        heap = []
        for num in nums:
            heapq.heappush(heap, num)
            if len(heap) > k:
                heapq.heappop(heap)

        return heap[0]

    #   15 Aug 2023
    #   Partition list
    class ListNode(object):
        def __init__(self, val=0, next=None):
            self.val = val
            self.next = next

    def partition(self, head, x):
        """
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """

        resultList = self.ListNode()
        resultListHead = resultList

        curr = head
        prev = None

        while curr:
            if curr.val < x:
                resultList.next = curr
                resultList = resultList.next

                #   Remove the node from the original list
                if prev:
                    prev.next = curr.next
                else:
                    head = head.next
                curr = curr.next
            else:
                prev = curr
                curr = curr.next

        resultList.next = head
        return resultListHead.next

    #   16 Aug 2023
    #   Sliding window maximum
    def maxSlidingWindow(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[int]
        """

        #   Use a double ended queue to store the INDEX, not the actual value
        queue = collections.deque()
        result = []

        for i in range(len(nums)):
            #   remove indices of of range
            while queue and queue[0] < i - k + 1:
                queue.popleft()

            #   removes indices with value less than the newly added number
            while queue and nums[queue[-1]] < nums[i]:
                queue.pop()

            queue.append(i)

            #   The max number will always be at the left end of the queue
            if i >= k - 1:
                result.append(nums[queue[0]])

        return result

    #   17 Aug 2023
    #   01 matrix
    def updateMatrix(self, mat):
        """
        :type mat: List[List[int]]
        :rtype: List[List[int]]
        """
        rows = len(mat)
        columns = len(mat[0])
        visited = [[0] * columns for _ in range(rows)]
        dp = [[10000] * columns for _ in range(rows)]
        queue = []
        for i in range(rows):
            for j in range(columns):
                if mat[i][j] == 0:
                    dp[i][j] = 0
                    queue.append([i, j])
        while len(queue) != 0:
            curr = queue.pop(0)
            currRow = curr[0]
            currColumn = curr[1]
            if visited[currRow][currColumn] == 1:
                continue
            else:
                visited[currRow][currColumn] = 1
                #   Up one row
                if currRow > 0 and visited[currRow - 1][currColumn] != 1:
                    dp[currRow - 1][currColumn] = min(
                        dp[currRow][currColumn] + 1, dp[currRow - 1][currColumn]
                    )
                    queue.append([currRow - 1, currColumn])

                #   Down one row
                if currRow < rows - 1 and visited[currRow + 1][currColumn] != 1:
                    dp[currRow + 1][currColumn] = min(
                        dp[currRow][currColumn] + 1, dp[currRow + 1][currColumn]
                    )
                    queue.append([currRow + 1, currColumn])

                #   Left column
                if currColumn > 0 and visited[currRow][currColumn - 1] != 1:
                    dp[currRow][currColumn - 1] = min(
                        dp[currRow][currColumn] + 1, dp[currRow][currColumn - 1]
                    )
                    queue.append([currRow, currColumn - 1])

                #   Right column
                if currColumn < columns - 1 and visited[currRow][currColumn + 1] != 1:
                    dp[currRow][currColumn + 1] = min(
                        dp[currRow][currColumn] + 1, dp[currRow][currColumn + 1]
                    )

                    queue.append([currRow, currColumn + 1])
        return dp

    #   18 Aug 2023
    #   Maximal network rank
    def maximalNetworkRank(self, n: int, roads: list[list[int]]) -> int:
        adjMatrix = [[0] * n for _ in range(n)]

        for x, y in roads:
            adjMatrix[x][y] = 1
            adjMatrix[y][x] = 1

        degree = []
        for i in range(n):
            count = 0
            for j in range(n):
                count += adjMatrix[i][j]
            degree.append(count)

        maxCount = 0
        for i in range(n):
            for j in range(i + 1, n):
                totalDegree = degree[i] + degree[j]
                if [i, j] in roads or [j, i] in roads:
                    totalDegree -= 1
                maxCount = max(maxCount, totalDegree)
        return maxCount

    #   19 Aug 2023
    #   Find critical and pseudo-critical edges in minimum spanning tree
    #   This code is mostly copilot and referring to online solutions
    def findCriticalAndPseudoCriticalEdges(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: List[List[int]]
        """
        original_edges = [(u, v, w, i) for i, (u, v, w) in enumerate(edges)]
        original_edges.sort(key=lambda x: x[2])
        result = self.kruskalMST(n, original_edges)
        weight = 0
        for edge in result:
            weight += edge[2]
        critical = []
        pseudoCritical = []

        for i in range(len(original_edges)):
            # Check if edge is critical
            tempEdges = original_edges[:i] + original_edges[i + 1 :]
            newMST = self.kruskalMST(n, tempEdges)
            if self.getGraphWeight(newMST) != weight:
                critical.append(original_edges[i][3])
            else:
                # Check if edge is pseudo-critical
                newMSTWithEdge = self.kruskalIncludingEdge(
                    n, original_edges, original_edges[i]
                )
                if self.getGraphWeight(newMSTWithEdge) == weight:
                    pseudoCritical.append(original_edges[i][3])

        return [critical, pseudoCritical]

    def getGraphWeight(self, edges):
        weight = 0
        for edge in edges:
            weight += edge[2]
        return weight

    def kruskalMST(self, n, edges):
        edges.sort(key=lambda x: x[2])
        parent = [i for i in range(n)]
        rank = [0] * n
        result = []
        for edge in edges:
            if self.union(edge[0], edge[1], parent, rank):
                result.append(edge)
        return result

    def kruskalIncludingEdge(self, n, edges, givenEdge):
        #   Generate a MST that has to include the given edge
        edges.sort(key=lambda x: x[2])
        parent = [i for i in range(n)]
        rank = [0] * n
        result = [givenEdge]
        self.union(givenEdge[0], givenEdge[1], parent, rank)
        for edge in edges:
            if edge == givenEdge:
                continue
            if self.union(edge[0], edge[1], parent, rank):
                result.append(edge)
        return result

    def union(self, x, y, parent, rank):
        xRoot = self.find(x, parent)
        yRoot = self.find(y, parent)
        if xRoot == yRoot:
            return False
        if rank[xRoot] < rank[yRoot]:
            parent[xRoot] = yRoot
        elif rank[yRoot] < rank[xRoot]:
            parent[yRoot] = xRoot
        else:
            parent[yRoot] = xRoot
            rank[xRoot] += 1
        return True

    def find(self, x, parent):
        if parent[x] != x:
            parent[x] = self.find(parent[x], parent)
        return parent[x]


test = Solution()
arr = [[0, 1, 1], [1, 2, 1], [2, 3, 2], [0, 3, 2], [0, 4, 3], [3, 4, 3], [1, 4, 6]]
print(test.findCriticalAndPseudoCriticalEdges(5, arr))
