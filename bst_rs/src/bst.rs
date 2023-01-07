extern crate queues;
use queues::*;

type OptNode = Option<Box<Node>>;

pub struct Node {
    key  : i32,
    left : OptNode,
    right: OptNode
}

impl Node {
    pub fn new(key: i32) -> Self {
        Self{key, left: None, right: None}
    }
}

pub struct BST {
    root: OptNode
}

impl BST {
    pub fn new() -> Self {
        BST{root: None}
    }

    pub fn get(&self, key: i32) -> Option<i32> {
        Self::get_helper(&self.root, key)
    }

    fn get_helper(root: &OptNode, key: i32) -> Option<i32> {
        if let Some(n) = root {
            if key == n.key {
                return Some(n.key);
            } else if key < n.key {
                return Self::get_helper(&root.as_ref().unwrap().left, key);
            } else {
                return Self::get_helper(&root.as_ref().unwrap().right, key);
            }
        }
        None
    }

    pub fn insert(&mut self, node: OptNode) {
        Self::insert_helper(&mut self.root, node)
    }

    fn insert_helper(root: &mut OptNode, node: OptNode) {
        if let None = *root {
            *root = node;
        } else {
            assert_ne!(root.as_ref().unwrap().key, node.as_ref().unwrap().key);

            if node.as_ref().unwrap().key < root.as_ref().unwrap().key {
                Self::insert_helper(&mut root.as_mut().unwrap().left, node);
            } else {
                Self::insert_helper(&mut root.as_mut().unwrap().right, node);
            }
        }
    }

    pub fn depth(&self) -> i32 {
        BST::depth_from(&self.root)
    }

    fn depth_from(root: &OptNode) -> i32 {
        if let None = *root {
            0
        } else {
            1 + std::cmp::max(BST::depth_from(&root.as_ref().unwrap().left), BST::depth_from(&root.as_ref().unwrap().right))
        }
    }
}

impl std::fmt::Display for BST {
    #[allow(unused_must_use)]
    #[allow(unused_assignments)]
    fn fmt(&self, f: &mut std::fmt::Formatter) -> std::fmt::Result {
        if let None = self.root {
            return writeln!(f, "None");
        }
        let mut q: Queue<&OptNode> = queue![];
        q.add(&self.root);
        q.add(&None);
        while q.size() != 0 {
            if let Some(n) = q.remove().unwrap() {
                let mut append_none = false;
                if let None = q.peek().unwrap() {
                    append_none = true;
                }
                write!(f, "{: <+2} ", n.key);
                if let Some(_) = &n.left {
                    q.add(&n.left);
                }
                if let Some(_) = &n.right {
                    q.add(&n.right);
                }
                if append_none {
                    q.add(&None);
                }
            } else {
                if q.size() != 0 {
                    write!(f, "\n");
                }
            }
        }
        write!(f, "")
    }
}