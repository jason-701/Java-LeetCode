#include <iostream>
using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};
class Solution
{
public:
    //  13 Jan 2023
    ListNode *deleteMiddle(ListNode *head)
    {
        ListNode *dummyHead = new ListNode(-1, head);
        ListNode *slow = head;
        ListNode *fast = head;
        ListNode *prevSlow = dummyHead;

        while (fast != nullptr && fast->next != nullptr)
        {
            prevSlow = slow;
            slow = slow->next;
            fast = fast->next->next;
        }
        prevSlow->next = slow->next;
        delete slow;

        return dummyHead->next;
    }
};