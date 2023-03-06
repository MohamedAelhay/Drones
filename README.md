# README #

# - Introduction
There is a major new technology that is destined to be a disruptive force in the field of transportation: the
drone. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal
communication, the drone has the potential to leapfrog traditional transportation infrastructure.
Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult
access.


## - Docker

 - cd src/docker/
 - CMD 'docker-compose up'

## - End Points

 - GET /drones Retrieves all drones
 - POST /drones Register drone
 - PATCH /drones/{id}/medication Update drone with medication
 - GET /drones/{id}/battery Get drone battery level 
 - GET /drones/{id} Get drone with medication by droneId