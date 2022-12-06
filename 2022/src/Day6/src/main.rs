use std::fs;
use itertools::Itertools;

fn different(chars: &[char]) -> bool {
    chars.into_iter().eq(chars.into_iter().unique())
}

fn main() {
    let file_path: &str = "../../data/Day6.txt";
    let contents: String = fs::read_to_string(file_path)
        .expect("Should have been able to read the file");

    const LENGTH: usize = 14; // changed from 4 for part 1
    let mut chars = contents.chars().peekable();
    let mut index = 0;
    let mut latest: [char; LENGTH] = Default::default();
    while !different(&latest) || index <= LENGTH {
        if !chars.peek().is_some() {
            println!("error: ran out of characters");
        }
        latest[index % LENGTH] = chars.next().unwrap();
        index = index + 1;
    }

    println!("{} different at index {}", LENGTH, index);
}