pub struct List {
    head: OptNode
}

struct Node {
    elem: i32,
    next: OptNode
}

impl Node {
    pub fn new(elem: i32) -> Self {
        Self{elem, next: None}
    }
}

type OptNode = Option<Box<Node>>;

impl List {
    pub fn new() -> Self {
        Self{head: None}
    }

    pub fn push(&mut self, elem: i32) {
        let new_node = Some(Box::new(Node::new(elem)));
        let mut node = &mut self.head;
        if let None = node {
            *node = new_node;
        } else {
            while let Some(n) = node {
                if let None = n.next {
                    n.next = new_node;
                    break;
                }
                node = &mut n.next;
            }
        }
    }
}

#[allow(unused_must_use)]
impl std::fmt::Display for List {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        if let Some(_) = self.head {
            let mut node = self.head.as_ref();
            while let Some(n) = node {
                write!(f, "{}->", n.elem);
                node = n.next.as_ref();
            }
        }
        write!(f, "None")
    }
    
}
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_list() {
        let mut l = List::new();
        l.push(2);
        l.push(3);
        println!("##");
        println!("{}", l);
        println!("##");
    }
}