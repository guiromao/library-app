# Library Application 
An application to manage a library's books - when they're lent, and when they're returned - and also some campaigns for promoting reading.

## API

GET /api/v1/library: retrieves all existing books

POST /api/v1/library: saves a new book onto the database (with a Book DTO)

GET /api/v1/library/{isbn}: retrieves a book by its ISBN

POST /api/v1/library/{isbn}/lend?memberId=X: lends a book to member with ID X

POST /api/v1/library/{isbn}/return?memberId=X: returns book from member with ID X

GET /api/v1/library/{isbn}/listlends: retrieves list of members who have lent the required book, and respective lending and returning dates

GET /api/v1/library/members: retrieves list of all members

GET /api/v1/library/members/{memberId}: retrieves Member of ID memberId

POST /api/v1/library/members: saves a member onto the database, receiving a Member DTO in the RequestBody

PUT /api/v1/library/members/{memberId}: switches state of activity of Member with ID memberId

GET /api/v1/library/campaigns: retrieves all existing Campaigns

POST /api/v1/library/campaigns: creates a new Campaign, saving it onto the database, and by receiving a Campaign DTO

GET /api/v1/library/campaigns/{campaignId}: retrieves Campaign with ID campaignId

PUT /api/v1/library/campaigns/{campaignId}?memberId=X: adds Member with ID X to the list of members who have joined Campaign with ID campaignId