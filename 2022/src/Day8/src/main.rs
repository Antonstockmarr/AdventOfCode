use std::{fs, str::Lines, iter::Peekable};

fn read_2d_number_array(lines: &mut Peekable<Lines>, columns: usize, rows: usize) -> Box<[Box<[u8]>]> {
    let mut data = vec![vec![0u8; columns].into_boxed_slice(); rows].into_boxed_slice();
    let mut i = 0;
    while lines.peek().is_some() && i<rows {
        let line = lines.next().unwrap();
        let mut chars = line.chars();
        for j in 0..line.len() {
            data[i][j] = chars.next().unwrap().to_digit(10).unwrap() as u8;
        }
        i += 1;
    }
    data    
}

fn main() {
    let file_path: &str = "../../data/Day8.txt";
    let contents: String = fs::read_to_string(file_path)
        .expect("Should have been able to read the file");

    let mut lines = contents.lines().peekable();

    let columns = lines.peek().unwrap().len();
    let rows = lines.clone().count();
    let data = read_2d_number_array(&mut lines, columns, rows);
    let mut visible = vec![vec![false; columns].into_boxed_slice(); rows].into_boxed_slice();
    
    let mut highest_from_right;
    let mut highest_from_left;
    let mut highest_from_top;
    let mut highest_from_bottom;

    for i in 0..columns {
        highest_from_top = 0;
        highest_from_bottom = 0;
        for j in 0..rows {
            if data[i][j] > highest_from_top || j == 0 {
                visible[i][j] = true;
                highest_from_top = data[i][j];
            }
            if data[i][rows - j - 1] > highest_from_bottom || j == 0 {
                visible[i][rows - j - 1] = true;
                highest_from_bottom = data[i][rows - j - 1];
            }
        }
    }

    for j in 0..rows {
        highest_from_right = 0;
        highest_from_left = 0;
        for i in 0..columns {
            if data[i][j] > highest_from_right || i == 0 {
                visible[i][j] = true;
                highest_from_right = data[i][j];
            }
            if data[columns - i - 1][j] > highest_from_left || i == 0 {
                visible[columns - i - 1][j] = true;
                highest_from_left = data[columns - i - 1][j];
            }
        }
    }

    let mut sum_of_peaks = 0;
    for i in 0..columns {
        for j in 0..rows {
            print!("{}", if visible[i][j] {"T"} else {"F"});
            if visible[i][j] {
                sum_of_peaks += 1;
            }
            
        }
        print!("\n");
    }
    println!("{}", sum_of_peaks);
}