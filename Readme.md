How to run with Docker:
1. Create a file "docker.env.dev" and set the following variables:
   + SERVER_PORT
   + POSTGRES_URL
   + POSTGRES_USER
   + POSTGRES_PASS
   + MAIL_HOST
   + MAIL_PORT
   + MAIL_USERNAME
   + MAIL_PASSWORD
2. Run "*docker-compose up*" for start postgres.
3. Run "*docker build -t thigorapim/kanban-board .*" to build kanban board docker image.
4. Run "*docker run --env-file ./docker.env.dev -p 8080:8080 thigorapim/kanban-board:latest*" 
to run kanban board container.
5. Have fun.