# Vision: #
Our project is a distributed Bluetooth client tracking system. The system will track where the user travels as they pass through various Bluetooth access point setup throughout a building or location. The server will record a user's current location and then tell the surrounding access points to wait for the user to travel into their location. If a connection can be made with the user, files, such as promotions and maps, can be sent to the user.

**Please view our presentation for a visual representation of this page** http://btmappingapp.googlecode.com/files/Blue%20Tears%20Project-1.ppt

  * Since clients can be masters or slaves, in an ideal solution and implementation we would have the clients use ad hoc connectivity to fill up dead zones that are not covered by access points. This was not implemented though.

# Practical Applications #
Mapping of Bluetooth zones is setup by the administrator. It can be used to map out a floor or a building if needed. But it must be setup manually. Some other practical applications are:
  * Tracking in malls
  * Distribution of man power
  * Locating children (such as in amusment parks/zoos/etc)
  * Data mining, commonly traveled routes
  * Clocking in/out
  * Location medical devices/patients/doctors

# Assumptions #
We assumed that:
  * The client would not be sprinting across Bluetooth access point because there is some latency in pinging users.
  * Clients are within the range of an access point at all times
  * Bluetooth address attributes are not changed during tracking. If the permissions or protocol change we will not be able to connect to the client anymore.
  * Access Points mapping for neighbouring access points is setup manually by an administrator

# Constraints #
For the demo we are limited to 3 access points and 1 client due to the hardware limitations of our Bluetooth adapters. Ideally, we would like to have 10s - 100s of access points and multiple clients. Unfortunately, we do not have the resources to recreate this. Client software SDKs are unique to every phone company. Due to time constraints we did not have the time to create a reliable client side application for multiple phones.


# Limitations #
> Approxiate tracking
  * Due to the level of abstraction in Java and the API used, distance from access points is not exposed. In order to utilize this functionality, we would need to use a lower level programming language like C or C++. The next release of the bluecove API will contain this functionality but it was not released in time for the development of our project.
  * Therefore, we were only able to track clients by Bluetooth access point area and made sure access points did not overlap.

> Bluetooth Hardware Limitations
  * Our bluetooth adapters can only make 1 connection at a time. Typically,
    * Bluetooth devices can make up to 7 connections
    * Industrial Bluetooth devices exist that can make many more (One device we found was able to make up to 46 connections).

> Bluecove Java Library
  * http://www.bluecove.org/
  * Implements JSR-82 - The Java Bluetooth API
  * It provides handling for different protocols, such as:
    * RFCOMM
    * SDAP
    * OBEX
    * ...


# Our Solution: #
(_example code from bluecove was used in this project_.)
We created a three-part information distributed system that consists of a centralized server, access points, and clients. The server and access points communicate through an RMI connection (which is currently using a WiFi network) using remote calls and callbacks. The access points will ping the clients to see if the clients are at a particular location.

The user will have to register his Bluetooth address with the system when the user enters the mapped area (_Code has been developed but has not be integrated with the main program yet **but we do simulate the registration of an address at a chosen access point**_).

Client software to receive location information was developed but was very unstable and flaky. Discovery, connection and text based communication would work occasionally. In the end, it was not integrated into the system.

# Closest Neighbour Algorithm #
This is an algorithm we created to satisfy our needs. An access point will locate a Bluetooth device. Once found, the access point will notify all of its neighbours to wait for the device to travel into their area. When the device is located in a neighbouring zone, the original access point will tell all of its neighbours to stop looking and the new "owner" of the device address will start the process again at the new access point.

  * Similar project: The WITS system http://btmappingapp.googlecode.com/files/wits.pdf
Uses 3 algorithms to improve the accuracy of location determination. They use a nearest neighbour algorithm, bayesian algorithm and history based algorithm. Since we do not have distance, or a lengthy history we were unable to use these algorithms. Hence why we created our own algorithm. They also use Wifi to improve location determination which we did not have.

  * Layout
In the WITS project access points are laid out in a grid format. We don't have the resources to create such an elaborate grid for more precise location determination.



# 4 Major Components #

## Access Point Initialization ##
Clients would register their Bluetooth devices with a particular access point. Devices must be set to discoverable until pairing is complete. The access point then searches for the appropriate services. This process can take anywhere from 30-90 secs. Our current version is not configured to run the discovery continuously. Ideally you should only need to register your address once, even for future use. We can perform multiple, single device, registrations but the Database will only store one instance of that devices information (unless the user changes configuration data that would otherwise alter the devices URL).

http://btmappingapp.googlecode.com/files/bt_v4.zip




## Access Points ##
Access points will ping users instead of using active discovery because discovery takes from 30-90 seconds. Continuous pinging is quick and only requires one ping. Once found the manager is notified. Currently there is no limit to the number of devices to ping through the use of a queue.  The device's url is pushed into the url queue and pulled out of the queue when ready to ping.

http://btmappingapp.googlecode.com/files/bt559proj_working_v8.1.2.zip

## Central Server ##
The server side is made up of three parts: Server, Manager and Database. The server initializes all services.  The manager records and delegates Bluetooth address to desired access points. It also extracts and updates information from the database such as mapping access point adjacency zones and Bluetooth client device history.

## Database ##

The database is a mySQL implementation. It is currently used to save mapping of access point zones and Bluetooth device url and location history.

# Synchronization #
In the case of two access points overlapping, we were going to implement versioning for all Bluetooth addresses but we did not have the time to implement it fully. The basic idea was that for every request sent out to neighbouring access points, the first to return with a response would update the version number for that access point and essentially block out the other request.  This could evolve into timestamps for more meaningful information for data mining but this would require clock synchronization and that was out of the scope of our project.


# Crashes #
  * There is no failsafe for a failed Access Point.
A possible **solution** is that addresses at the failed access point will be delegated to its neighbours. If the software fails the thread can be recreated and the access point restarted.
  * Server Fails
A possible **solution** would be the leader algorithm. We would have to decentralize the server and access points would fight for leadership.

# Conclusion #
In conclusion, we believe that the concept for our system is sound, but did not get implemented fully due to time constraints. Bluetooth is still a viable solution for client tracking and file pushing. It can be useful for both client and company. It would be more useful in a grid format that uses the nearest neighbour, Bayesian, and history tracking algorithm. Pinging is quick and easy. With better hardware, we believe this could become a real solution to indoor tracking. Failsafe for crashes still requires more thought.