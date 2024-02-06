echo "laura" | sudo -S mysql -u root -laura -e "SET GLOBAL
time_zone = '+3:00';"
echo "laura" | sudo -S mysql -u root -laura -e "create database
biblioteca;"
echo "laura" | sudo -S mysql -u root -laura -e "create user
bibliotecario identified by 'bibliotecario';"
echo "laura" | sudo -S mysql -u root -laura -e "grant all
privileges on biblioteca.* to 'bibliotecario';"
echo "laura" | sudo -S mysql -u root -laura -D biblioteca -e
"create table libros (isbn INT(13) primary key, titulo varchar(30) not
null, autor varchar(30) not null);"
