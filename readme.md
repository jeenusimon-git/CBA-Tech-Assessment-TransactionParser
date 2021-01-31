
# CBA Transaction Parsing 

## The Tech Stack used is as below
1. Java
2. supercsv

## Problem
Create a program in Java to parse the file q1.test_data and write as key/value pairs in Excel/CSV file.

## Structure of test data (as from problem statement https://github.com/cba-fdp-pub/test_assessment#note)
- SZ  [ represents the start of a transaction item (ex: Line1)  or you can take smh [record as the start of a transaction
]SZ à represents the end of a transaction (ex: Line29)
With this you should be able to identify how many transactions in this sample input file
You will have to create separate csv/excel file for each transaction (Note: All the transactions might not have the same keys.)

- You can ignore any line which contains [record as this line doesn’t hold key or value
With this while you parse the sample file you should get the below keys and values (listed only few for your understanding)
```
smh_seg_id 000000
smh_seg_version 00
smh_msg_version 08.08.88
smh_tran_type ABC
```
## Notes
- The test data file provided had to be modified slightly to align to the test data structure/grammar provided above.
- The program loads the test data file from the resources folder
- The program takes the csv output folder as an argument.
  
## How to execute the Test
  - Clone the repo on to the local machine
  - Setup MavenPath and Java Path
  - Go into the repo folder and execute the command 
  ```
  mvn clean install
  ```
  - Execute the App class (Ideally from Intellij) with the csv output folder as an argument
  - Two csv files, out1.csv and out2.csv, are created in the output folder
  
## Output
The output folder has the outputs generated from this program.
