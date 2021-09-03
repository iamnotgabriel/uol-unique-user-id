# Data Dictionary

## **users**

Table that contains the data entered by the user at the time of registration

| Column Name   | Type    | Example               | Description                                  |
| ------------- | ------- | --------------------- | -------------------------------------------- |
| user_id (PK)  | SERIAL  | 1                     | Sequence of integers used as primary key     |
| user_name     | TEXT    | Your Name             | User name                                    |
| user_email    | TEXT    | example@email.com     | User email                                   |
| user_password | TEXT    | \$2a\$05\$bvIG6Nmi... | User hashed password by bcrypt method        |
| user_phone    | TEXT    | 12988112233           | User phone number                            |
| user_is_admin | BOOLEAN | 1                     | Controls whether the user is an admin or not |

## **signup_metadata**

Table that contains all aditional data we collect at the time of registration, like device name, screen resolution, number of times the key `CTRL+V` was pressed etc.

| Column Name             | Type      | Example             | Description                                                       |
| ----------------------- | --------- | ------------------- | ----------------------------------------------------------------- |
| signup_md_id (PK)       | SERIAL    | 1                   | Sequence of integers used as primary key                          |
| signup_md_user_id       | INT       | 1                   | Foreign key for `users` table                                     |
| signup_md_ctrlv_count   | INT       | 1                   | Number of times that the user pressed `CTRL+V` in the signup page |
| signup_md_start_date    | TIMESTAMP | 2021-01-01 23:59:59 | When the user enters the sign up page                             |
| signup_md_end_date      | TIMESTAMP | 2021-01-01 23:59:59 | When the user completes the registration                          |
| signup_md_timezone      | TEXT      | America/Sao_Paulo   | User timezone                                                     |
| signup_md_ip            | TEXT      | 172.217.22.14       | User IP                                                           |
| signup_md_os            | TEXT      | Mac OS X            | User operating system                                             |
| signup_md_screen_width  | INT       | 1920                | Number of pixels contained on horizontal axis                     |
| signup_md_screen_height | INT       | 1080                | Number of pixels contained on vertical axis                       |
| signup_md_device_name   | TEXT      |                     | User device name                                                  |
| signup_md_browser       | TEXT      | Chrome              | Browser used to sign up                                           |
