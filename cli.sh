export DATABASE_URL="postgresql://localhost:5430/uol"
start() {
    mvn spring-boot:run $@
}

upDB() {
    docker-compose up $@
}

downDB() {
    docker-compose down $@
}

resetDB() {
    downDB
    upDB $@
}

shellDB() {
    docker exec -it uolDB /bin/bash
}

psqlDB() {
    docker exec -it uolDB /usr/bin/psql -U postgres -d uol -W
}