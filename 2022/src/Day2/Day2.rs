use std::fs;

fn points(input: &str) -> i32 {
    match input {
        "A X" => 4,
        "A Y" => 8,
        "A Z" => 3,
        "B X" => 1,
        "B Y" => 5,
        "B Z" => 9,
        "C X" => 7,
        "C Y" => 2,
        "C Z" => 6,
        &_ => -10000
    }
}

fn points_revised(input: &str) -> i32 {
    match input {
        "A X" => 3,
        "A Y" => 4,
        "A Z" => 8,
        "B X" => 1,
        "B Y" => 5,
        "B Z" => 9,
        "C X" => 2,
        "C Y" => 6,
        "C Z" => 7,
        &_ => -10000
    }
}

fn main() {
    // --snip--
    let file_path = "../../data/Day2.txt";

    let contents = fs::read_to_string(file_path)
        .expect("Should have been able to read the file");

    let mut lines = contents.lines().peekable();
    let mut sum = 0;
    while lines.peek().is_some() {
        let current = lines.next().unwrap();
        sum += points_revised(&current);
    }
    println!("Sum {:?}", sum);

}