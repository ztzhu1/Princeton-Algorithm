use crate::bst::Node;

mod bst;

fn main() {
    let mut b = bst::BST::new();
    b.insert(Some(Box::new(Node::new(1))));
    b.insert(Some(Box::new(Node::new(2))));
    b.insert(Some(Box::new(Node::new(0))));
    b.insert(Some(Box::new(Node::new(-1))));
    b.insert(Some(Box::new(Node::new(4))));
    b.insert(Some(Box::new(Node::new(5))));
    b.insert(Some(Box::new(Node::new(3))));
    b.insert(Some(Box::new(Node::new(-2))));
    println!("depth: {}", b.depth());
    if let Some(key) = b.get(1) {
        println!("found {}", key);
    } else {
        println!("Can not find key!");
    }
    println!("{}", b);
}
