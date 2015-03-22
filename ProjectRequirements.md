#Project Requirements for Bluetooth Mapping Application.

# Introduction #

Our project is a distributed bluetooth client mapping system. The system will have bluetooth access points throughout a building or area. Depending which access point the client is in, the server will calculate their location and send the information back to the client. Since client and be masters or slaves, clients will also be able to connect between each other to fill up dead zone.

This mapping concept can be useful in places that do not have wireless reception.

Bluetooth Mapping Application diagram: http://btmappingapp.googlecode.com/files/btMappingDiagram.pdf

# Details #

**Requirements**

> _Must Have_
    * Communication between client and access points
    * Communication between client and client
      * Transfer of information from client to client to access point
    * Server must calculate clients location
      * Movement between access points
      * Client will keep track of access point unique Id
      * Server must tell client which access point they are at (text for the time being)
    * Server will keep track of users and locations visited in a database

> _Could Have_
    * Gui for map
    * file pushing

**Scope**
  * Load is limited to one server
  * Data limited to MySQL database requirements.

**Constraints**
  * Application must be installed on mobile
  * Requires multiple access points to be effective
  * Mapping is hardcoded on server side
  * Keeping user location on database. Useful for data mining.


Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages