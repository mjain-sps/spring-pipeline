variable "flavor" { default = "m1.small" }
variable "image" { default = "Debian Buster 10.11.1 20211029" }
#variable "instance" { default = "ff_instance" }

variable "name" { default = "finalserver" }

variable "network" { default = "default" }   # you need to change this

variable "keypair" { default = "scmimc_os_keypair" } # you need to change this
variable "pool" { default = "cscloud_private_floating" }
variable "server_script" { default = "./serverJenkins.sh" }
variable "security_description" { default = "Terraform security group" }
variable "security_name" { default = "ff_security" }
