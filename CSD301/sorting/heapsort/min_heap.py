from ctypes import sizeof
import sys

class minHeap:
    def __init__(self, maxsize):
        self.maxsizse = maxsize
        self.size = 0
        self.heap = [0]*(self.maxsize + 1)
        self.heap[0] = -1 * sys.maxsize
        self.front = 1

    def parent(self, pos):
        """Return the parent of the node at pos

        Args:
            pos (int): position of the current node

        Returns:
            int: position of the parent of the node at pos
        """
        return pos // 2

    def leftChild(self, pos):
        """Return the position of the left child of the node at pos

        Args:
            pos (int): position of the node
        Returns:
            int: position of the left child
        """
        return 2*pos
    
    def rightChild(self, pos):
        """Return the position of the right child of the node at pos

        Args:
            pos (int): position of the node 

        Returns:
            int: position of the right child
        """
        return (2*pos)+1
    
    def isLeaf(self, pos):
        """Return true if the node is leaf

        Args:
            pos (int): position of the node
        Returns:
            boolean: true if the node is leaf
        """
        return pos*2 > self.size
    
    def swap(self, pos1, pos2):
        """Swap two node 

        Args:
            pos1 (int): first position
            pos2 (int): second position
        """
        self.heap[pos1], self.heap[pos2] = self.heap[pos2], self.heap[pos1]
        
    def minHeapify(self, pos):
        # if the node is non-leaf
        if not self.isLeaf(pos):
            # if the node greater than its childs
            if self.heap[pos] > self.heap[self.leftChild(pos)] or self.heap[pos] > self.heap[self.rightChild(pos)]:
                # if left child is smaller than right child then swap current node at pos with left child and heapify the left child
                if self.heap[self.leftChild(pos)] < self.heap[self.rightChild(pos)]:
                    self.swap(pos, self.leftChild(pos))
                    self.minHeapify(self.leftChild(pos))
                # otherwise swap the current node at pos with right chikld and heapify the right child
                else:
                    self.swap(pos, self.rightChild(pos))
                    self.minHeapify(self.rightChild(pos))
    
    def insert(self, value):
        if self.size >= self.maxsize:
            return
        self.size += 1
        self.heap[self.size] = element
        current = self.size