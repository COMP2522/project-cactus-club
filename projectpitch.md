# Project Pitch

## Summary

📄 Player-controlled paddle, bouncing a ball to destroy slabs until there are none left. 

🤼 Members: Nick, Trevor, Kate, Justin, Cyrus


## Discussion Items

📱 Main communication through Discord
	🤳 Reply to Discord @ within 24 hours

😡 Disputes dealt with a 2/3 majority vote

🗯 Meeting time and format: Tuesdays, 1:30pm - 3pm

📺 Previous game design experience: Justin, Kate, Cyrus
👨‍💻 Adept Procedural Programmers: Nick, Trevor

👩‍🏫 Group expectations:
	🎓 At least 90% for our final evaluation
	⌛ 5 hours per week, per person


## Roles

💅 Justin: lead architect (overall UML and collisions)

🛹 Kate: UI/UX lead (draw methods and animations)

🎵 Trevor: debugger (powerups)

🕹 Nick: lead backend (saving state and loading levels)

🎮 Cyrus: test maker and level design


## Requirements

1⃣ Visuals using processing.org

2⃣ Concurrent / asynchronous processing happening at regular intervals
	- Calculations for balls, paddle movement, and collisions
	- Constantly saving game state
	- Potential multiplayer feature
	
3⃣ Non-trivial persistent data state (JSON, CSV, ...)
	- Full saved game state
	
4⃣ Custom iterable data structure, e.g. adding and deleting enemies
	- Slabs and balls added and deleted through collisions
	
5⃣ Well documented and runs well
	- Include java docs, sensible variable names and UML diagrams for consideration
	- A well thought out polymorphic design to ensure smooth operations