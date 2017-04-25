create table Menu (menu_id int(10) not null, name varchar(255), description varchar(255), PRIMARY KEY(menu_id));
-- Menu needs to have id as unique identifier and PRIMARY KEY because this is a strong entity, and name and description
create table Recipe (rec_id int(10) not null, menu_id int(10) not null, name varchar(255), description varchar(255), PRIMARY KEY(menu_id), FOREIGN KEY(menu_id) REFERENCES Menu(menu_id));
-- Recipe needs to have id as unique identifier and PRIMARY KEY because this is a strong entity, and it also has menu_id to see which menu the recipe belongs to, and other information as name, description
create table Instruction (rec_id int(10) not null, description varchar(255), PRIMARY KEY(rec_id), FOREIGN KEY(rec_id) REFERENCES Recipe(rec_id)););
-- Instruction needs to have recipe id as primary key as well as foreign key, because it is a weak entity and need recipe to know which recipe this Instruction belongs to
create table Ingredient (ing_id int(10) not null, name varchar(255), description varchar(255), PRIMARY KEY(ing_id));
-- Ingredient needs a unique identifier and PRIMARY KEY because this is a strong entity
create table Ing_Of_Rec (ing_id int(10) not null, rec_id int(10) not null, amount varchar(255), PRIMARY KEY(rec_id, ing_id), FOREIGN KEY(rec_id) REFERENCES Recipe(rec_id), FOREIGN KEY(ing_id) REFERENCES Ingredient(ing_id));
-- This is a many to many relationship between Ingredient and Recipe tables since many Ingredient can appear in many recipes
