 echo ("i am being called")
 sudo yum update -y

    # install git

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