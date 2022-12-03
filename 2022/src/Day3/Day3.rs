use std::fs;
use std::iter::Peekable;

fn priority(letter : char) -> i32 {
    let ascii = letter as i32;
    if ascii < 91 {
        return ascii - 64 + 26;
    }
    else {
        return ascii - 96
    }
}

fn calculate_part_one_sum(lines: &mut Peekable<std::str::Lines<'_>>) {
    let mut sum = 0;
    while lines.peek().is_some() {
        let current = lines.next().unwrap();
        
        let letters : Vec<char> = current.chars().collect();
        let n = letters.len();
        'outer: for i in 0..(n/2) {
            for j in (n/2)..n {
                if letters[i] == letters[j] {
                    sum += priority(letters[i]);
                    break 'outer;
                }
            }
        }
    }
    println!("Sum {:?}", sum);
}

fn calculate_part_two_sum(lines: &mut Peekable<std::str::Lines<'_>>) {
    let mut sum = 0;
    while lines.peek().is_some() {
        let first_line = lines.next().unwrap();
        let second_line = lines.next().unwrap();
        let third_line = lines.next().unwrap();
        
        let first_letters : Vec<char> = first_line.chars().collect();
        let second_letters : Vec<char> = second_line.chars().collect();
        let third_letters : Vec<char> = third_line.chars().collect();
        'outer: for a in first_letters.iter() {
            for b in second_letters.iter() {
                if *a == *b {
                    for c in third_letters.iter() {
                        if *a == *c {
                            sum += priority(*a);
                            break 'outer;
                        }
                    }
                }
            }
        }
    }
    println!("Sum {:?}", sum);
}

fn main() {
    let file_path = "../../data/Day3.txt";
    let contents = fs::read_to_string(file_path)
        .expect("Should have been able to read the file");


    let mut lines = contents.lines().peekable();
    //calculate_part_one_sum(&mut lines);
    calculate_part_two_sum(&mut lines);
}