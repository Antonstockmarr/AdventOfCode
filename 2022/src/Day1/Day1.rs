use std::fs;

fn main() {
    // --snip--
    let file_path = "../../data/Day1.txt";
    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path)
        .expect("Should have been able to read the file");

    let mut lines = contents.lines().peekable();
    let mut sum = 0;
    let mut sums = Vec::new();
    while lines.peek().is_some() {
        let current = lines.next().unwrap();
        println!("current {}", current);
        if current == "" {
            sums.push(sum);
            sum = 0;
        }
        else {
            sum += current.parse::<i32>().unwrap();
        }
    }
    sums.sort_by(|a,b| b.cmp(a));
    println!("Sum {:?}", sums);
    println!("{}", sums[0]+sums[1]+sums[2]);

}