create table if not exists users (
  user_id serial primary key,
  user_name text not null,
  user_email text not null,
  user_password text not null,
  user_phone text not null,
  user_hashid varchar ( 15 ),
  user_is_admin boolean,
  constraint uq_users_email unique(user_email)
);

create table if not exists signup_metadata (
  signup_md_id serial primary key,
  user_id int not null,
  signup_md_device_id text not null,
  signup_md_paste_count int not null,
  signup_md_start_date timestamp not null,
  signup_md_end_date timestamp not null,
  signup_md_timezone text not null,
  signup_md_ip text not null,
  signup_md_os text not null,
  signup_md_screen_width int not null,
  signup_md_screen_height int not null,
  signup_md_device_name text not null,
  signup_md_browser text not null,
  signup_md_cpu_cores smallint default null,
  signup_md_gpu_name text default null,
  signup_md_key_ups json default null,
  signup_md_key_downs json default null,
  constraint fk_user_id foreign key(user_id) references users(user_id) on delete cascade
);
