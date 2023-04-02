## Chatroom application
An simple Spring application using Rest APIs and Websockets.
You can perform CRUD operations, create, read, delete, and update 
the Channel's status. The main Channel will show all active channels
and if a channel goes online.

![ChatRoom Image](chatroom.png)

# What I have learned
- Lombok Annotations
- Use Websocket for Real time communication
- Rest Crud operations
- JPA and Hibernate
- Error handling for Rest APIs
- Working in a Testing API environment
- 
# How to run this application
- open terminal and enter: git clone https://github.com/rodercode/phone_duck.git
- open folder phone_duck/ in desired IDE
- run Spring application
- open a testing API environment, for example "Insomnia"
- Create HTTP requests
  - [GET]/api/channels <- Retrieves a list of all channels
  - [POST]/api/channels <- Create a channel
  - [PATCH]/api/channels/online/id <- Change online status for a channel
  - [DELETE]/api/channels/<id> <- Delete channel by id

- Create Websockets
   - [WS]/sub/chatRooms/ <- activate chat room
   - [WS]/sub/channels <- activate main chat
# Tools
* Java
* Intellij
* Mysql database
* Spring framework
* Insomnia
