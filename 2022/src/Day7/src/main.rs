use std::{fs, iter::Peekable, str::Lines};

#[derive(Eq, PartialEq)]
enum Operation {
    List,
    GoUp,
    GoTo
}

fn get_operation(line: &str) -> (Operation, Option<&str>) {
    if !line.starts_with('$') {
        panic!("Tried to determine the operation type of {}", line);
    }
    let mut words = line.split(" ");
    let command = words.nth(1).unwrap();
    if command == "ls" {
        return (Operation::List, None);
    }
    else {
        let argument = words.next().unwrap();
        if argument == ".." {
            return (Operation::GoUp, None);
        }
        else {
            return (Operation::GoTo, Some(argument));
        }
    }
}

fn add_list_sizes(lines: &mut Peekable<Lines>) -> i64 {
    let mut sum = 0;
    while lines.peek().is_some() {
        if lines.peek().unwrap().starts_with("$") {
            break;
        }
        let line = lines.next().unwrap();
        let file = line.split(" ").next().unwrap();
        if file != "dir" {
            sum += file.parse::<i64>().unwrap();
        }
    }
    sum
}

fn main() {
    let file_path: &str = "../../data/Day7.txt";
    let contents: String = fs::read_to_string(file_path)
        .expect("Should have been able to read the file");

    let mut lines = contents.lines().peekable();

    let mut sizes : Vec<i64> = Vec::new();
    let mut size_stack : Vec<i64> = Vec::new();

    while lines.peek().is_some() {
        let current_line = lines.next().unwrap();
        let (operation, _) = get_operation(&current_line);
        if operation == Operation::List {
            let mut current_size = size_stack.pop().unwrap();
            current_size += add_list_sizes(&mut lines);
            size_stack.push(current_size);
        }
        else if operation == Operation::GoUp {
            let current_size = size_stack.pop().unwrap();
            sizes.push(current_size);
            let mut parent_size = size_stack.pop().unwrap();
            parent_size += current_size;
            size_stack.push(parent_size);
        }
        else {
            size_stack.push(0);
        }
    }
    while !size_stack.is_empty() {
        let current_size = size_stack.pop().unwrap();
        sizes.push(current_size);
        let parent_size = size_stack.pop();
        if parent_size.is_some() {
            size_stack.push(parent_size.unwrap() + current_size);
        }
    }

    let mut under100k = 0;
    for i in sizes.clone() {
        if i <= 100_000 {
            under100k += i;
        }
    }
    println!("sum of directories under 100k: {}", under100k);

    const MAX_SPACE : i64 = 70_000_000;
    const MIN_FREE_SPACE : i64 = 30_000_000; 
    let free_space = MAX_SPACE - sizes.last().unwrap();

    let min_deletion = MIN_FREE_SPACE - free_space;
    let mut best_deletion = MAX_SPACE;

    for i in sizes.clone() {
        if i >= min_deletion && i <= best_deletion {
            best_deletion = i;
        }
    }
    println!("best deletion: {}", best_deletion);
}