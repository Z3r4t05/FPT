from multiprocessing import parent_process
import sys
from xml.dom import NoModificationAllowedErr

class Node():
    def __init__(self,item):
        self.item = item
        self.parent = None
        self.left = None
        self.right = None
        self.color = 1

class RedBlackTree():
    def __init__(self):
        self.TNULL = Node(0)
        self.TNULL.color = 0
        self.TNULL.left = None
        self.TNULL.right = None
        self.root = self.TNULL
        
    # Preorder
    def pre_order_helper:
        if node != TNULL:
            sys.stdout.write(node.item + " ")
            elf
    