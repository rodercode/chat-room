## Chatroom application
This is a simple Spring application using Rest APIs and websockets.
you can perform crud operations create, read, delete and also update channel's status.
also all active channels will be displayed in the Main channel.

![ChatRoom Image](chatroom.png)

# What I have learned
* Lombok Annotations
* Use Websocket for Real time communication
* Rest Crud operations
* JPA and Hibernate
* Error handling for Rest APIs
* Working in a Testing API environment


# How to run this application
* open terminal and enter: git clone https://github.com/rodercode/phone_duck.git
* open folder phone_duck/ in desired IDE
* run Spring application
* open a testing API environment, for example "Insomnia"
* Create HTTP requests 
  * [GET]/api/channels <- Retrieves a list of all channels
  * [POST]/api/channels <- Create a channel
  * [PATCH]/api/channels/online/id <- Change online status for a channel
  * [DELETE]/api/channels/<id> <- Delete channel by id

* Create Websockets
   * [WS]/sub/chatRooms/ <- activate chat room
   * [WS]/sub/channels <- activate main chat

# Spring dependencies used on this project
* Data JPA
* Web
* Websocket
* Devtools
* Mysql connector
* Lombok

# Tools used on this project
* Java
* Mysql database
* Spring framework
* Insomnia
