# Developer

Corrsponding sample database (populated) is attached as "DevelopersInfo.sql".

For simplicity, I assumed that CRUD of developers will be operated on only 7 programming languages which I indicated on the search page (php, ruby, javascript, kotlin, swift, puython, scala ) . No new programming language other than those will be inserted into DB .

I also assumed that no crud operation will be operated on developer who does not exist ( trying so will give internal server error )

Replacing a developer's programming language with another one ( in my API, I Implemented that in name of "Update" operation ) requires the language being replaced to be known to the corresponding developer. For example, If a developer does not know scala, but someone wants to replace "scala" with "swift" under his email, no operation will be done ( same for delete ).

For time shortage, I could not implement pagination in interview interface. If someone tries to edit score/comment of an interview , the pop up field for updating that row will appear in the beginning of the webpage ( right above the text fields for inserting new record ) . So we have to scroll up to the start of the page after clicking "edit" button on a row to edit that record.

The last and major part of README is that I started implementing this project keeping the thought that only one instance will be running at a time to work on the DB server. So, i created a local map of the records at the start of the app & operated search on just that local record so that i do not have to eshtablish connection with DB server frequently for each search request.However, create, update, insert , delete will be reflected on the DB server and local record instantly after each operation in the app. To make things easier , I put a special button "Reload data from server" which will update the local records from the DB server, thus enabling to merge the changes made to the DB server by others with local records. Thanks for reading .
