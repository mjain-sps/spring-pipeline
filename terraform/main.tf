

terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.27"
    }
  }

  required_version = ">= 0.14.9"
}

provider "aws" {
  profile = "default"
  region     = var.region
  access_key = var.access_key
  secret_key = var.secret_key
}


resource "aws_security_group" "web-sg" {
  name = "spring-sg"
}

resource "aws_security_group_rule" "public_out" {
  type        = "egress"
  from_port   = 0
  to_port     = 0
  protocol    = "-1"
  cidr_blocks = ["0.0.0.0/0"]
 
  security_group_id = aws_security_group.web-sg.id
}


resource "aws_security_group_rule" "public_in_ssh" {
  type              = "ingress"
  from_port         = 22
  to_port           = 22
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.web-sg.id
}

resource "aws_security_group_rule" "public_in_http" {
  type              = "ingress"
  from_port         = 80
  to_port           = 80
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.web-sg.id
}
 

resource "aws_security_group_rule" "public_in_jenkins" {
  type              = "ingress"
  from_port         = 8080
  to_port           = 8080
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.web-sg.id
}
resource "aws_security_group_rule" "public_in_spring_project" {
  type              = "ingress"
  from_port         = 8081
  to_port           = 8081
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.web-sg.id
}
 
resource "aws_instance" "app_server" {
  ami           = "ami-0ad8ecac8af5fc52b"
  instance_type = "t2.large"
  key_name      = "spring-app"
  user_data     = <<EOF
#!/bin/bash
sudo yum update -y

    # install git
  sudo yum install -y git
    # Install my wget
sudo yum install -y wget

    # Install curl
sudo yum -y install curl

echo "installing java and then jenkins"
sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat/jenkins.io.key
sudo yum upgrade
# Add required dependencies for the jenkins package
sudo yum install java-11-openjdk -y
sudo yum install jenkins -y
sudo systemctl enable jenkins
sudo systemctl start jenkins

    sudo yum update
    echo "installing MariaDB..."
    sudo yum install mariadb-server -y
    sudo systemctl start mariadb
    sudo systemctl status mariadb
    sudo systemctl enable mariadb

    mysql -u root -e "USE mysql; CREATE database client;"
    mysql -u root -e "USE mysql; UPDATE user SET password=PASSWORD('comsc') WHERE User='root' AND Host = 'localhost'; FLUSH PRIVILEGES;"

EOF
  vpc_security_group_ids=[aws_security_group.web-sg.id]

  tags = {
    Name = "awsredhat8"
  }
}



