use std::fs;

fn is_contained(first_elf: &str, second_elf: &str) -> bool {
    let mut first_boundaries = (*first_elf).split("-");
    let mut second_boundaries = (*second_elf).split("-");
    let first_lower = first_boundaries.next().unwrap().parse::<i32>().unwrap();
    let first_upper = first_boundaries.next().unwrap().parse::<i32>().unwrap();
    let second_lower = second_boundaries.next().unwrap().parse::<i32>().unwrap();
    let second_upper = second_boundaries.next().unwrap().parse::<i32>().unwrap();
    return (first_lower <= second_upper && first_upper >= second_lower) ||
    (second_lower <= first_upper && second_upper >= first_lower);
}


fn main() {
    let file_path = "../../data/Day4.txt";
    let contents = fs::read_to_string(file_path)
        .expect("Should have been able to read the file");


    let mut lines = contents.lines().peekable();
    let mut contained = 0;
    while lines.peek().is_some() {
        let line = lines.next().unwrap();
        let mut parts = line.split(",");
        let first_elf = parts.next().unwrap();
        let second_elf = parts.next().unwrap();
        if is_contained(&first_elf, &second_elf) {
            contained += 1;
        }
    }

    println!("{}", contained);
}