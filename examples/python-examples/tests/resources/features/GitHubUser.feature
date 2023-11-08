Feature: GitHub

GitHub user profile verification (UI/API).


Scenario: User Profile

Given the test resource "<user>"
Then the user profile is exist in GitHub
And the user profile has the login "<login>"
And the user profile has the name "<name>"
And the user profile has the id "<id>"
And the user profile has the type "<type>"

Examples:
| user                 | login              | id       | type | name           |
| incident-recipient   | incident-recipient | 62027792 | User | Nelson         |
| YuriiChukhrai        | YuriiChukhrai      | 21183380 | User | Yurii Chukhrai |