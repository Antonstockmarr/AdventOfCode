use std::{iter::Peekable, str::Lines, fs};

fn parse_stacks(lines: &mut Peekable<Lines>, stacks: &mut [Vec<char>]) {
    while lines.peek().is_some() {
        let line = lines.next().unwrap();
        if line.chars().nth(1) == Some('1') {
            lines.next();
            return;
        }
        let chars = line.chars();
        for i in 0..9 {
            let element = chars.clone().nth(i*4+1).unwrap();
            if element != ' ' {
                stacks[i].insert(0, element);
            }
        }
    }
}

fn move_elements_part_one(lines: &mut Peekable<Lines>, stacks: &mut [Vec<char>]) {
    while lines.peek().is_some() {
        let line = lines.next().unwrap();
        let mut words = line.split(" ");

        let times = words.nth(1).unwrap().parse::<i32>().unwrap();
        let from = words.nth(1).unwrap().parse::<i32>().unwrap();
        let to = words.nth(1).unwrap().parse::<i32>().unwrap();
        for _i in 0..times {
            let element = stacks[from as usize - 1].pop().unwrap();
            stacks[to as usize - 1].push(element);
        }
    }
}

fn move_elements_part_two(lines: &mut Peekable<Lines>, stacks: &mut [Vec<char>]) {
    while lines.peek().is_some() {
        let line = lines.next().unwrap();
        let mut words = line.split(" ");

        let times = words.nth(1).unwrap().parse::<i32>().unwrap();
        let from = words.nth(1).unwrap().parse::<i32>().unwrap();
        let to = words.nth(1).unwrap().parse::<i32>().unwrap();
        let mut tmp = vec!();
        for _i in 0..times {
            let element = stacks[from as usize - 1].pop().unwrap();
            tmp.push(element);
        }
        for _i in 0..times {
            let element = tmp.pop().unwrap();
            stacks[to as usize - 1].push(element);
        }
    }
}
fn main() {
    let file_path: &str = "../../data/Day5.txt";
    let contents: String = fs::read_to_string(file_path)
        .expect("Should have been able to read the file");

    const COLUMNS: usize = 9;
    let mut lines = contents.lines().peekable();
    let mut stacks: [Vec<char>; COLUMNS] = Default::default();
    
    parse_stacks(&mut lines, &mut stacks);

    //move_elements_part_one(&mut lines, &mut stacks);
    move_elements_part_two(&mut lines, &mut stacks);

    for i in 0..COLUMNS {
        let element = stacks[i].pop().unwrap();
        println!("{}", element);
    }
}