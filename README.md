# The Grapevine

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Schema](#Schema)

## Overview
### Description
Allows members of Greek organizations on college campuses to easily communicate and plan events with other chapters via chat and calendar features. 

### App Evaluation
- **Category:** Social Networking
- **Mobile:** This app will be developed for mobile but would perhaps be just as viable on a computer, such as GroupMe or WhatsApp. Functionality wouldn't be limited to mobile devices, however mobile version could potentially have more features.
- **Story:** Allows members of the Greek Life community on a college campus to connect with other chapters on their campus.
- **Market:** This app would target undergraduate college students who are part of a Greek organizations. 
- **Habit:** This app could be used as often or unoften as the user wanted depending on how deep their social life is, and what exactly they're looking for.
- **Scope:** First we would start with one region of the United States with known Greek Life presence, but would expand to include chapters nationwide. Large potential for use on college campuses in the South.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Users can make a new account and login to an existing account
* Users can edit their profile page
* User can view another user's profile page containing their previous posts and events
* Users can create calendar events and view calendar of community events
* Users can chat with other app users
* Users can view a feed of posts and events from other users
* Users can post to shared feed
* Users can switch between screens with Bottom Navigation Bar
* Settings (Accesibility, Notification, General, etc.)

**Optional Nice-to-have Stories**

* The feed view has expandable event threads
* Users can comment on individual posts
* Users can like other individual posts
* Users can view other campus calendars and users

**Sprint 1/4

- [X] Create Account FE
- [X] Edit user profile FE
- [X] Chatlist FE
- [X] Calendar View FE
- [X] Viewing Profile FE
- [ ] Feed FE
- [ ] View Post FE

**Sprint 2/4
- [x] Feed FE
- [x] View Post FE
- [x] Create Server
- [x] Log In Functionality
- [ ] Creating Event Functionality
- [ ] Viewing Events on Calendar Functionality
- [x] Viewing Events on Feed Functionality
- [ ] Loading User Profiles
- [x] Loading Current Logged in Profile

<img src="https://media1.giphy.com/media/1QPe3bja6fMmMVBVhm/giphy.gif?cid=790b76114957709da286b172d00f67b47a9ca9f4c0b14f5e&rid=giphy.gif&ct=g" width=250><br>


**Sprint 3/4
- [x] Creating Event Functionality
- [x] Creating Post Functionality
- [x] Viewing events on Calendar Functionality
- [x] Loading User Profiles
- [ ] Chat Functionality
- [ ] Settings

<img src="https://media1.giphy.com/media/1QPe3bja6fMmMVBVhm/giphy.gif?cid=790b76114957709da286b172d00f67b47a9ca9f4c0b14f5e&rid=giphy.gif&ct=g" width=250><br>

**Sprint 4/4
- [X] Chat Functionality
- [X] Logout Functionality
- [X] Like an Event/Post Functionality
- [X] Search and View Another Users Profile
- [X] User Experience (UX) Improvements & Branding
- [X] General Cleanup + Bugfixes
- [ ] Settings

<img src="https://github.com/Bohbah/AndroidProject/blob/master/demodemoo.gif" width=250><br>

**NOTE
The chat feature was reimagined to be a campus-wide chat, instead of individualized messaging, to promote Greek Unity and foster a sense of community - not individuaity. 

### 2. Screen Archetypes

* Login - allows user signs into their account
* Sign Up - allows user signs up for an account
* Profile Screen - shows user information, logout button, and search other users feature
* Edit Profile Screen - allows user to upload a photo and fill in relevant personal information
* Chat Screen - shows the chat between all Grapevine users at a school
* Add Event/Post Screen - allows the user to create a new event to include on the community calendar
* Calendar Screen - shows all of the submitted events by users in a calendar view
* Feed Screen - shows all newly created user posts from the same school

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home (feed)
* Calendar
* Add Event/Post
* Chat
* Profile

**Flow Navigation** (Screen to Screen)
* Create New Account -> Feed screen
* Successful Log-in -> Feed screen
* Profile -> Edit profile if button clicked


## Wireframes
<img src="https://i.imgur.com/8i4G8LQ.png" width=1000><br>

### [BONUS] Digital Wireframes & Mockups
<img src="https://i.imgur.com/Tvkc6c2.png" height=850>

### [BONUS] Interactive Prototype
<img src="https://i.imgur.com/0fGSl7R.gif" width=200>

## Schema
<img src="https://i.imgur.com/9CLEm9r.png" width=500>

#### Models
#### Organization

   | Property      | Type             | Description |
   | ------------- | --------         | ------------|
   | ID            | Int              | unique id for the organization (default value and auto increment) |
   | Org_Name      | String           | Name of the organization |
   | Org_Desc      | String           | Description of the organization |
   
#### User

   | Property      | Type             | Description |
   | ------------- | --------         | ------------|
   | ID            | Int              | unique id for the user (default value and auto increment) |
   | username      | String           | unique username |
   | password      | String           | password |
   | name          | String           | user's name |
   | position      | String           | user's position in their org |
   | description   | String           | user's organization name |
   | calendar      | N/A              | pointer for their google calendar authentication |

#### Event/Post

   | Property      | Type             | Description |
   | ------------- | --------         | ------------|
   | ID            | Int              | unique id for events (default value and auto increment) |
   | Location      | String           | Location for where the event is being held |
   | Date Time     | Date Time        | Time and Date for the event |
   | Description   | String           | Event description for what is happening |
   | Name          | String           | Name of the event |
   | isEvent       | Booleana         | To signify is event or post |

   
#### Messages

   | Property      | Type             | Description |
   | ------------- | --------         | ------------|
   | Content       | String           | The message being sent/recieved by users |
   | Sender        | User             | User who sent the message |
   | Reciever      | User             | User who recieves the message |
   | Date Time     | Date Time        | A timestamp for when the message was sent|

### Networking
#### List of network requests by screen
   - Home Feed Screen
      - (Read/GET) Query all posts of app users
         ```swift
         let query = PFQuery(className:"Post")
         query.order(byDescending: "createdAt")
         query.findObjectsInBackground { (posts: [PFObject]?, error: Error?) in
            if let error = error { 
               print(error.localizedDescription)
            } else if let posts = posts {
               print("Successfully retrieved \(posts.count) posts.")
           // TODO: Do something with posts...
            }
         }
         ```
   - Create Post Screen
      - (Create/POST) Create a new post object
   - Profile Screen
      - (Read/GET) Query logged in user object
   - Edit Profile Screen  
      - (Update/PUT) Update user profile image
      - (Update/PUT) Update user organization image
      - (Update/PUT) Update username
      - (Update/PUT) Update password
      - (Update/PUT) Update user school
      - (Update/PUT) Update user organization
      - (Update/PUT) Update user position
      - (Update/PUT) Update user joined in date
   - Create Event
      - (Create/POST) Use API to create event
   - View Calendar
      - (Read/GET) Use API to view calendar of events
   - View All Chats
      - (Read/GET) Query all messages of current app user
   

#### [OPTIONAL:] Existing API Endpoints
##### Google Calendar API
- Base URL - [https://developers.google.com/calendar/api/v3/reference]

   Method | Endpoint | Description
   ----------|----------|------------
    `GET`    | /calendars/calendarId/events/eventId | Returns an event based on its Google Calendar ID. To retrieve an event using its iCalendar ID, call the events.list method using the iCalUID parameter.
    `GET`    | /calendars/calendarId/events | Returns events on the specified calendar.
    `POST`   | /calendars/calendarId/events | Creates an event.
