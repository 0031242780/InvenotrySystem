-- create database tech_market;

use tech_market;

create table category(
category_id int auto_increment primary key,
category_name varchar(64),
descrption text
);

create table supplier(
supplier_id int auto_increment primary key,
company_name varchar(64),
contact_person varchar(32),
email varchar(64),
is_active boolean
);
create table product(
product_id int auto_increment primary key,
product_name varchar(64),
barcode varchar(64) unique,
descrption text,
category_id int not null,
supplier_id int not null,
foreign key (supplier_id) references supplier(supplier_id),
foreign key(category_id) references category(category_id)
);
create table product_photo(
product_id int ,
photo_path varchar(128),
primary key (product_id,photo_path),
foreign key (product_id) references product(product_id) on delete cascade on update cascade
);

create table product_pricing(
product_id int,
cost numeric(8,2),
wholesale_price numeric(8,2),
retail_price numeric(8,2),
primary key (product_id),
 foreign key (product_id) references product(product_id) on delete cascade on update
 cascade
);

create table discount(
product_id int,
start_date datetime,
end_date datetime,
discounted_price numeric(6,2),
primary key (product_id,start_date),
foreign key(product_id) references product(product_id) on delete cascade
on update cascade 
);

create  table inventory(
product_id int ,
quantity_in_stock int not null,
primary key(product_id),
foreign key (product_id) references product(product_id) on delete  cascade on update cascade
);


create table supplier_phone_number(
phone_number VARCHAR(20),
supplier_id int,
PRIMARY KEY (supplier_id, phone_number),    
FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id) 
ON DELETE CASCADE 
ON UPDATE CASCADE
);

create table delivery_company(
company_id int auto_increment primary key,
company_name varchar(32) ,
contact_person varchar(32) ,
support_phone_number varchar(20),
is_active boolean
);

create table order_statuses(
status_id int auto_increment primary key,
status_name varchar(32)
);

create table roles(
role_id int auto_increment primary key,
role_name varchar(32),
permission_level varchar(16) not null
);

create table accounts(
account_id int auto_increment primary key,
email varchar(64) unique not null,
password_ varchar(64) not null,
created_at date,
first_name varchar(32),
last_name varchar(32),
city varchar(32),
street varchar(16),
phone_number varchar(20) not null,
role_id int not null,
foreign key (role_id) references roles(role_id) 
);

create table orders(
order_id int auto_increment primary key,
total_price numeric(8,2),
status_id int not null,
company_id int ,
account_id int not null,
FOREIGN KEY (company_id) REFERENCES delivery_company(company_id) ,
foreign key (status_id) references order_statuses(status_id),
foreign key (account_id) references accounts(account_id) 
);


create table delivery_company_phone_number(
phone_number VARCHAR(20),
company_id int,
PRIMARY KEY (company_id, phone_number),    
FOREIGN KEY (company_id) REFERENCES delivery_company(company_id) 
ON DELETE CASCADE 
ON UPDATE CASCADE
);

create table order_item(
order_id int ,
quantity int , 
product_id int ,
price_at_purchase numeric(8,2),
primary key(order_id,product_id),
foreign key (order_id) references orders(order_id) on delete cascade on update cascade,
foreign key (product_id) references product(product_id) 
);

create table shopping_session(
session_id int auto_increment primary key,
create_at datetime,
updated_at datetime,
account_id int not null,
foreign key (account_id) references accounts(account_id) 
);

create table cart_item(
session_id int ,
product_id int,
quantity int ,
primary key (session_id,product_id),
foreign key (session_id) references shopping_session(session_id) on update cascade on delete cascade,
foreign key (product_id) references product(product_id) 
);

create table movement_type(
type_id int auto_increment primary key,
type_name varchar(32)
);

create table stock_movement(
movement_id int auto_increment primary key,
quantity_changed int,
notes text,
create_at datetime,
type_id int not null,
account_id int not null,
product_id int not null,
supplier_id int,
order_id int,
foreign key (order_id) references orders(order_id) ,
foreign key (supplier_id) references supplier(supplier_id) ,
foreign key (product_id) references product(product_id) ,
foreign key (account_id) references accounts (account_id) ,
foreign key (type_id) references movement_type(type_id)  
);

USE tech_market;

-- ================= INSERT (PC MARKET CATEGORIES) =================
INSERT INTO category (category_name, descrption)
VALUES ('Laptops', 'Gaming and business laptops');

INSERT INTO category (category_name, descrption)
VALUES ('Desktops', 'Prebuilt and custom PCs');

INSERT INTO category (category_name, descrption)
VALUES ('PC Components', 'CPU, GPU, RAM, Motherboards');

INSERT INTO category (category_name, descrption)
VALUES ('Peripherals', 'Keyboards, mice, monitors');

INSERT INTO category (category_name, descrption)
VALUES ('Storage', 'SSD and HDD storage devices');

INSERT INTO category (category_name, descrption)
VALUES ('Networking', 'Routers, switches, WiFi devices');


-- ================= SELECT =================
SELECT * FROM category;

SELECT * FROM category
WHERE category_id = 1;


-- ================= UPDATE =================
UPDATE category
SET category_name = 'Gaming Laptops',
    descrption = 'High performance gaming laptops'
WHERE category_id = 2;


-- ================= DELETE =================
DELETE FROM category
WHERE category_id = 6;


-- ================= RESET AUTO INCREMENT (OPTIONAL) =================
ALTER TABLE category AUTO_INCREMENT = 1;