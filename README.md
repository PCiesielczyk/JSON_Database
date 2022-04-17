# JSON_Database
### Simple data exchange between client and server, based on [Hyperskill Project](https://hyperskill.org/projects/65) 
## Usage
- Start ServerMain.java
- Start ClientMain.java **with passed CLI arguments**

Arguments are type, key, value type of request:
- type: -t is the type of the request, valid values are: "set", "get" and "delete"
- key: -k is the position of value we want to set or retrieve, valid values are any positive integer number
- value: -v is the value to save in the database: you only need it in case of a set request, valid value is any string
Arguments might be passed through json file by -in <<file name>> command.
Examples of valid inputs are shown below.
 
![get_without_key_in](https://github.com/PCiesielczyk/JSON_Database/blob/main/JSON_Database/examples/ss_1_1.jpg)
![get_without_key_out](https://github.com/PCiesielczyk/JSON_Database/blob/main/JSON_Database/examples/ss_1_2.jpg)
![set_in](https://github.com/PCiesielczyk/JSON_Database/blob/main/JSON_Database/examples/ss_2_1.jpg)
![set_out](https://github.com/PCiesielczyk/JSON_Database/blob/main/JSON_Database/examples/ss_2_2.jpg)
![get_in](https://github.com/PCiesielczyk/JSON_Database/blob/main/JSON_Database/examples/ss_1_1.jpg)
![get_out](https://github.com/PCiesielczyk/JSON_Database/blob/main/JSON_Database/examples/ss_3_2.jpg)

