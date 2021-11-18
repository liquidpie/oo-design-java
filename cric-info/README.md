# cric-info

### Requirements

* Keep track of all formats: ODI, Test, T20
* Keep track of all teams and their matches
* Show ball by ball commentary of the match
* Team playing tournament should announce playing squad
* Team playing match should announce playing eleven
* Should record stats of Players, Teams, Matches and tournaments
* Should show the stats based on the query

### Actors

* Admin
    - Administration activity like adding a team, adding a tournament, adding an inning, adding a match etc

* Commentator
    - adding a commentary ball by ball on the system

* Viewer
    - user of the service

### Usecases

* Admin
    - Add/Modify Player
    - Add/Modify Team
    - Add/Modify Tournament
    - Add/Modify Match
    - Add/Modify Innings
    - Add/Modify Over
    - Add/Modify Ball (update run/wicket)
    - Add/Modify Umpire
    - Add/Modify Stadium
    - Add/Modify Stats (Player, Team, Tournament)

* Commentator
    - Add commentary
    - View commentary

* Viewer
    - View commentary

### Entities

* Player
* Team
* Tournament
* Match (ODI, Test, T20)
* Stats (Player, Team, Tournament)
* Umpire/Referee
* Inning
* Over
* Ball
* Commentator
* Commentary
* News (extra entity)

### Reference
https://www.youtube.com/watch?v=VDqwCo6lhkY&list=PLAC2AM9O1C5KioUMeH9qIjbAV_RMmX8rd&index=7

