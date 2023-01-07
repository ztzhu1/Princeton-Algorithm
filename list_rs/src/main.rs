mod list;

fn main() {
    let mut l = list::List::new();
    l.push(2);
    l.push(3);
    println!("##");
    println!("{}", l);
    println!("##");
}
