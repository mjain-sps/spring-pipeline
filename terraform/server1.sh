echo in directory $PWD

sudo apt update
sudo apt install wget -y
sudo apt install unzip -y
sudo apt install git -y
sudo apt install curl -y

#installing java
sudo apt update
sudo apt install openjdk-11-jre -y
java -version

#installing mysql
sudo apt-get install mariadb-server -y
sudo systemctl start mariadb
sudo systemctl enable mariadb
sudo mysql -u root -e "USE mysql; CREATE database client;"
sudo mysql -u root -e -p comsc "USE mysql; UPDATE user SET password=PASSWORD('comsc') WHERE User='root' AND Host = 'localhost'; FLUSH PRIVILEGES;"
sudo mysql -u root -e "ALTER USER 'root'@'localhost' IDENTIFIED VIA mysql_native_password;"
sudo mysql -u root -e "ALTER USER 'root'@'localhost' IDENTIFIED BY 'comsc';"
