# Location #

dbs4.cpsc.ucalgary.ca
user: meier
pwd: 992347

# Tables and Fields #

btzones
  * zoneID: unique ID, is primary key
  * zoneName: a name for the particular zone
  * zoneAddress: mac address or something similar

btlocations
  * locationID: unique ID, is primary key
  * zoneID: foreign key into btzones, designates zone that location is in
  * locationName: name for location (ie. store name, kiosk, etc)

btclients
  * clientMac: mac address for individual clients, is primary key
  * currentLocation: current zone that client is in
  * lastLocation1-9: last 9 locations client was in, in descending order