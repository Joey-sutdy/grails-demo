databaseChangeLog = {

    changeSet(author: "mei.li", id: "create table demo for demo-limei") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT(20)") {
                constraints(primaryKey: "true")
            }
            column(name: "name", type: "varchar(20)") {
                constraints(nullable: "false")
            }
            column(name: "date_created", type: "DATETIME")
            column(name: "last_updated", type: "DATETIME")
            column(name: "description", type: "text")
        }
    }
}
